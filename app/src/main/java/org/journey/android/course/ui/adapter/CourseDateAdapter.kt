package org.journey.android.course.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.course.data.entity.CourseDateEntity
import org.journey.android.databinding.ItemCourseStampBinding
import org.journey.android.databinding.ItemCourseStampReverseBinding
import java.lang.RuntimeException

class CourseDateAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var date = mutableListOf<CourseDateEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_ODD -> {
                val binding = ItemCourseStampBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CourseOddDateViewHolder(binding)
            }
            VIEW_TYPE_EVEN -> {
                val binding = ItemCourseStampReverseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CourseEvenDateViewHolder(binding)
            }
            else -> throw RuntimeException("error to find view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val challengeDate = position + 1
        return if (challengeDate % 2 == 0) VIEW_TYPE_EVEN else VIEW_TYPE_ODD
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = date[position]

        when (holder.itemViewType) {
            VIEW_TYPE_ODD -> (holder as CourseOddDateViewHolder).binding.setVariable(BR.data, data)
            VIEW_TYPE_EVEN -> (holder as CourseEvenDateViewHolder).binding.setVariable(BR.data, data)
            else -> throw RuntimeException("error to bind view holder")
        }
    }

    override fun getItemCount() = date.size

    companion object {
        private const val VIEW_TYPE_ODD = 1
        private const val VIEW_TYPE_EVEN = 2
    }

    class CourseOddDateViewHolder(val binding: ItemCourseStampBinding) : RecyclerView.ViewHolder(binding.root)
    class CourseEvenDateViewHolder(val binding: ItemCourseStampReverseBinding) : RecyclerView.ViewHolder(binding.root)
}
