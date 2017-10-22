package net.sileader.cyborg.qb.incubator

import net.sileader.cyborg.appman.Application
import net.sileader.cyborg.appman.Permission
import net.sileader.cyborg.core.Basys
import net.sileader.cyborg.support.annotation.MainThread
import net.sileader.cyborg.util.Log
import java.io.File
import java.net.URLClassLoader

object Incubate {
    private const val TAG="QB::Incubate"

    @MainThread
    fun incubate(app: Application, args: Array<String>) {
        Log.i(TAG, "Launch QB process as incubate other system")
        val className=app.className
        val libName=app.library

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

        if(!app.permission.has(Permission.OUTER_DIRECTORY_ACCESS)) {
            Log.i(TAG, "${app.name}: chroot to ${app.homeDirectory}")
            Basys.changeRootDirectory(app.homeDirectory)
        }
        if(!app.permission.has(Permission.RUN_AS_ROOT)) {
            Basys.uid=100
        }

        method.invoke(null, newProcessArgs)
    }
}
