package org.journey.android.reward.util

import androidx.recyclerview.widget.DiffUtil
import org.journey.android.reward.dto.CompleteCourseEntity

object CourseDiffCallback : DiffUtil.ItemCallback<CompleteCourseEntity>() {
    override fun areItemsTheSame(
        oldItem: CompleteCourseEntity,
        newItem: CompleteCourseEntity
    ): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(
        oldItem: CompleteCourseEntity,
        newItem: CompleteCourseEntity
    ): Boolean {
        return oldItem == newItem
    }
}