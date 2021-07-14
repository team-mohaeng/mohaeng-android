package org.journey.android.reward.util

import androidx.recyclerview.widget.DiffUtil
import org.journey.android.reward.dto.CompleteCourseData

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