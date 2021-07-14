package org.journey.android.course.view

import android.R
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*


class CourseItemDecoration(context: Context) : ItemDecoration() {
    var context: Context? = null
    var mDivider: Drawable? = null

    fun MyItemDecoration(context: Context) {
        this.context = context
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = -30
    }
}