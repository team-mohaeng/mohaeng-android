package org.journey.android.reward

import androidx.recyclerview.widget.DiffUtil

object CourseDiffCallback : DiffUtil.ItemCallback<CompleteCourseData>() {
    override fun areItemsTheSame(
        oldItem: CompleteCourseData,
        newItem: CompleteCourseData
    ): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(
        oldItem: CompleteCourseData,
        newItem: CompleteCourseData
    ): Boolean {
        return oldItem == newItem
    }
}