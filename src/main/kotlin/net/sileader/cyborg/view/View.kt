package net.sileader.cyborg.view

import net.sileader.cyborg.Context
import javax.swing.JComponent

open class View(context: Context) {
    companion object {
        const val WRAP_CONTENT=0
        const val MATCH_PARENT=-1
    }

    open internal fun getInternalValue(): JComponent {
        throw NotImplementedError("View#internalType must be implemented in sub class of View")
    }
}
