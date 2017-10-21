package net.sileader.cyborg.util

import net.sileader.cyborg.core.application
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*

object Log {
    const val ASSERT=7
    const val ERROR=6
    const val WARN=5
    const val INFO=4
    const val DEBUG=3
    const val VERBOSE=2

    fun println(priority: Int, tag: String, msg: String, tr: Throwable?=null) {
        val calendar=Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val day=calendar.get(Calendar.DAY_OF_MONTH)
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val minute=calendar.get(Calendar.MINUTE)
        val second=calendar.get(Calendar.SECOND)
        val milliSec=calendar.get(Calendar.MILLISECOND)

        val level=when(priority) {
            ASSERT->"A"
            DEBUG->"D"
            ERROR->"E"
            VERBOSE->"V"
            WARN->"W"
            else->"I" // INFO
        }

        val pkg=application.getPackageName()
        System.err.println("UTC $year/$month/$day $hour:$minute:$second.$milliSec/$pkg $level/$tag: $msg")
        if(tr!=null) {
            System.err.println(getStackTraceString(tr))
        }
    }

    fun getStackTraceString(tr: Throwable): String {
        val os=ByteArrayOutputStream()
        val ps=PrintStream(os)
        tr.printStackTrace(ps)
        val ststr=os.toString()

        ps.close()
        os.close()

        return ststr
    }

    fun a(tag: String, msg: String, tr: Throwable?=null) {
        println(ASSERT, tag, msg, tr)
    }

    fun e(tag: String, msg: String, tr: Throwable?=null) {
        println(ERROR, tag, msg, tr)
    }

    fun w(tag: String, msg: String, tr: Throwable?=null) {
        println(WARN, tag, msg, tr)
    }

    fun i(tag: String, msg: String, tr: Throwable?=null) {
        println(INFO, tag, msg, tr)
    }

    fun d(tag: String, msg: String, tr: Throwable?=null) {
        println(DEBUG, tag, msg, tr)
    }

    fun v(tag: String, msg: String, tr: Throwable?=null) {
        println(VERBOSE, tag, msg, tr)
    }
}