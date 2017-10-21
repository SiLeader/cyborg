package net.sileader.cyborg.view

import net.sileader.cyborg.Context

open class ViewGroup(context: Context) : View(context) {
    private val mViews=mutableListOf<View>()

    open fun addView(view: View) {
        mViews+=view
    }
}