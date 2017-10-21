package net.sileader.cyborg.view

import net.sileader.cyborg.core.ContextWrapper
import java.awt.Rectangle
import javax.swing.JFrame

class Activity(title_: String, width_: Int, height_: Int) : ContextWrapper() {

    private val mFrame:JFrame=JFrame(title_)

    init {
        mFrame.bounds=Rectangle(width_, height_)
        mFrame.defaultCloseOperation=JFrame.EXIT_ON_CLOSE
    }

    var width: Int
        get()=mFrame.width
        set(value) {
            mFrame.bounds=Rectangle(value, height)
        }

    var height: Int
        get()=mFrame.height
        set(value) {
            mFrame.bounds=Rectangle(width, value)
        }

    var title: String
        get()=mFrame.title
        set(value) {
            mFrame.title=value
        }

    fun addView(view: View) {
        mFrame.contentPane.add(view.getInternalValue())
    }
}
