package org.journey.android.presentation.main.course.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.domain.entity.CourseEntity
import org.journey.android.databinding.*
import java.lang.RuntimeException

class CourseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var courseSituation = listOf<CourseEntity>()

    class OddCourseViewHolder(val binding : ItemCourseSituationBinding) : RecyclerView.ViewHolder(binding.root)
    class EvenCourseViewHolder(val binding: ItemCourseSituationReverseBinding): RecyclerView.ViewHolder(binding.root)
    class StartCourseViewHolder(val binding: ItemCourseSituationStartBinding): RecyclerView.ViewHolder(binding.root)
    class EndCourseViewHolder(val binding: ItemCourseSituationEndBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ODD -> {
                val binding = ItemCourseSituationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OddCourseViewHolder(binding)
            }
            VIEW_TYPE_EVEN -> {
                val binding = ItemCourseSituationReverseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EvenCourseViewHolder(binding)
            }
            VIEW_TYPE_START -> {
                val binding = ItemCourseSituationStartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                StartCourseViewHolder(binding)
            }
            VIEW_TYPE_END -> {
                val binding = ItemCourseSituationEndBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EndCourseViewHolder(binding)
            }
            else -> throw RuntimeException("error to find view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = courseSituation[position]
        when(holder.itemViewType) {
            VIEW_TYPE_ODD -> (holder as OddCourseViewHolder).binding.setVariable(BR.data, data)
            VIEW_TYPE_EVEN -> (holder as EvenCourseViewHolder).binding.setVariable(BR.data, data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) {
            VIEW_TYPE_START
        } else if (position == courseSituation.lastIndex) {
            VIEW_TYPE_END
        } else if (position % 2 == 0) {
            VIEW_TYPE_EVEN
        } else VIEW_TYPE_ODD
    }

    companion object {
        private const val VIEW_TYPE_ODD = 1
        private const val VIEW_TYPE_EVEN = 2
        private const val VIEW_TYPE_START = 3
        private const val VIEW_TYPE_END = 4
    }

    override fun getItemCount() = courseSituation.size
}