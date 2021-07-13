package org.journey.android.reward

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.databinding.ItemRewardCourseBinding
import org.journey.android.util.DiffCallback

class CompleteCourseAdapter : ListAdapter<CompleteCourseData, CompleteCourseAdapter.CompleteCourseViewHolder>(CourseDiffCallback) {
    val completeCourseList = mutableListOf<CompleteCourseData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteCourseViewHolder {
        val binding = ItemRewardCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CompleteCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompleteCourseViewHolder, position: Int) {
        holder.onBind(completeCourseList[position])
        holder.itemView.isSelected = true
    }

    override fun getItemCount(): Int = completeCourseList.size

    class CompleteCourseViewHolder(val binding : ItemRewardCourseBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(completeCourseData: CompleteCourseData){
            binding.textviewCourseTerm.text = completeCourseData.courseDate
            binding.textviewCourseSubject.text = completeCourseData.courseName
            binding.textviewCourseDate.text = completeCourseData.courseComplete
        }
    }


}