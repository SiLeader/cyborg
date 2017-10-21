package net.sileader.cyborg.core

import net.sileader.cyborg.Context

open class ContextWrapper : Context() {
    override fun getPackageName(): String {
        return ""
    }
}