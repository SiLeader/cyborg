package net.sileader.cyborg.core

import net.sileader.cyborg.basys.Basys

object Basys {
    var uid: Int
        get() = Basys.INSTANCE.uid
        set(value) {
            Basys.INSTANCE.uid=value
        }

    fun changeRootDirectory(path: String): Int = Basys.INSTANCE.changeRootDirectory(path)

    fun forkProcess(): Int = Basys.INSTANCE.forkProcess()
    fun waitProcess(): Int = Basys.INSTANCE.waitProcess()
    fun waitPidProcess(pid: Int): Int = Basys.INSTANCE.waitPidProcess(pid)
}