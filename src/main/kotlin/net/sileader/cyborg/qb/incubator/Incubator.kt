package net.sileader.cyborg.qb.incubator

import net.sileader.cyborg.appman.ApplicationManager
import net.sileader.cyborg.core.Basys
import net.sileader.cyborg.qb.core.exists
import net.sileader.cyborg.support.annotation.MainThread
import net.sileader.cyborg.support.annotation.WorkerThread
import net.sileader.cyborg.util.Log
import org.json.JSONObject
import org.newsclub.net.unix.AFUNIXServerSocket
import org.newsclub.net.unix.AFUNIXSocketAddress
import java.io.BufferedInputStream
import java.io.File
import java.nio.charset.Charset
import java.util.concurrent.Executors

class Incubator(val applicationManager: ApplicationManager) {
    companion object {
        const val UNIX_DOMAIN_SOCKET_PATH="/tmp/qb_incubator.sock"

        private const val TAG="QB::Incubator"
        private const val SETTING_APP="applications"
    }

    constructor(json: JSONObject) : this(ApplicationManager(json.getJSONArray(SETTING_APP)))
    constructor(setting: String) : this(JSONObject(setting))

    private val mThreadPool= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()+1)
    private val mRunning= mutableListOf<Int>()

    @MainThread
    fun entry() {
        Log.i(TAG, "Launch QB process as incubator")

        val udsock= AFUNIXServerSocket.newInstance()
        udsock.bind(AFUNIXSocketAddress(File(UNIX_DOMAIN_SOCKET_PATH)))

        while(true) {
            val sock=udsock.accept()

            val os= BufferedInputStream(sock.getInputStream())
            val bytes=ByteArray(sock.receiveBufferSize)
            os.read(bytes)

            val commands=bytes.toString(Charset.forName("UTF-8"))
            if(commands=="sys --exit") {
                break
            }

            mThreadPool.submit({ runner(commands.split(" "))})

            os.close()
            sock.close()
        }
        for(pid in mRunning) {
            Basys.waitPidProcess(pid)
        }
    }

    @WorkerThread
    private fun runner(args: List<String>) {
        if(args.exists("incubate")) {
            val application=(args.find {elem -> elem.startsWith("--application=")}?:return).split("=")[1]

            val app=applicationManager[application]

            if(app==null) {
                Log.e(TAG, "Application not found: $application")
            }else {
                val pid=Basys.forkProcess()
                if(pid==-1) {
                    Log.e(TAG, "Process cannot create")
                    return
                }
                if(pid==0) {
                    Incubate.incubate(app, args.toTypedArray())
                    return
                }
                mRunning.add(pid)
            }
        }
    }
}
