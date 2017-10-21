package net.sileader.cyborg.qb.incubator

import net.sileader.cyborg.support.annotation.MainThread
import net.sileader.cyborg.util.Log
import java.io.File
import java.net.URLClassLoader

object Incubate {
    private const val TAG="QB::Incubate"

    @MainThread
    fun incubate(args: Array<String>) {
        Log.i(TAG, "Launch QB process as incubate other system")
        val className=(args.find {elem -> elem.startsWith("--class-name=")}?:return).split("=")[1]
        val libName=(args.find {elem -> elem.startsWith("--library-name=")}?:return).split("=")[1]

        Log.i(TAG, "Process class name: $className, Library: $libName")

        val argsSeparatorIndex=args.indexOf("--")

        val newProcessArgs=if(argsSeparatorIndex<0) {
            arrayOf()
        }else{
            args.sliceArray(argsSeparatorIndex+1..args.size)
        }

        val loader=URLClassLoader.newInstance(arrayOf(File(libName).toURI().toURL()))
        val clazz=loader.loadClass(className)

        val method=clazz.getMethod("main", Array<String>::class.java)

        method.invoke(null, newProcessArgs)
    }
}
