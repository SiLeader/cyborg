package net.sileader.cyborg.appman

import org.json.JSONArray

class Permission(permissions_: List<Int>) {
    companion object {
        const val INTERNET_ACCESS=0
        const val FORK_PROCESS=1
        const val RUN_AS_ROOT=2
        const val INSTALL_SOFTWARE=3
        const val OUTER_DIRECTORY_ACCESS=4
        const val IPC=5
        const val INTER_PROCESS_COMMUNICATION= IPC
        const val GRANT_PERMISSION=6

    }

    constructor(json: JSONArray) : this((0..(json.length()-1)).map { json.getInt(it) })

    private val mPermissions=permissions_.toMutableSet()

    fun grant(p: Int): Boolean {
        if(this has GRANT_PERMISSION) {
            when(p) {
                INTERNET_ACCESS, FORK_PROCESS, RUN_AS_ROOT, INSTALL_SOFTWARE, OUTER_DIRECTORY_ACCESS, IPC
                -> mPermissions.add(p)
                else -> return false
            }
            return true
        }
        return false
    }

    fun remove(p: Int) {
        mPermissions.remove(p)
    }

    infix fun has(p: Int) = mPermissions.contains(p)

    fun toJson(): JSONArray {
        val json=JSONArray()
        for(p in mPermissions) {
            json.put(p)
        }
        return json
    }
}
