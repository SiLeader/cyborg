package net.sileader.cyborg.qb.core

import net.sileader.cyborg.qb.incubator.Incubate
import net.sileader.cyborg.qb.incubator.Incubator
import net.sileader.cyborg.util.Log
import net.sileader.cyborg.util.read
import kotlin.system.exitProcess


private const val TAG="QB"

fun<T> Array<T>.exists(target: T): Boolean {
    return find {elem -> elem==target}!=null
}
fun<T> List<T>.exists(target: T): Boolean {
    return find {elem -> elem==target}!=null
}

fun main(args: Array<String>) {
    println("Cyborg QB Application Incubator")
    println("(C) 2017 Itoshiki Umi from SiLeader.")

    when {
        args.exists("incubator") -> {
            val setting=(args.find {elem -> elem.startsWith("--setting=")}?:return).split("=")[1]

            val incubator=Incubator(read(setting))
            incubator.entry()
        }
        args.exists("incubate") -> Incubate.incubate(args)
        else -> {
            Log.e(TAG, "QB called without system model")
            exitProcess(1)
        }
    }
}
