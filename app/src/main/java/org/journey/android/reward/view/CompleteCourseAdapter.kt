package org.journey.android.reward.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.R
import org.journey.android.databinding.ItemRewardCourseBinding
import org.journey.android.reward.dto.CompleteCourseData
import org.journey.android.reward.util.CourseDiffCallback

class CompleteCourseAdapter :
    ListAdapter<CompleteCourseData, CompleteCourseAdapter.CompleteCourseViewHolder>(
        CourseDiffCallback
    ) {
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

            when(completeCourseData.property){
                0 -> {
                    binding.circleimageReward.setImageResource(R.drawable.course_health)
                    binding.constraintlayoutRewardCourse.setBackgroundResource(R.drawable.library_round_health)
                }
                1 -> {
                    binding.circleimageReward.setImageResource(R.drawable.course_mamoey)
                    binding.constraintlayoutRewardCourse.setBackgroundResource(R.drawable.library_round_memory)
                }
                2 -> {
                    binding.circleimageReward.setImageResource(R.drawable.course_detect)
                    binding.constraintlayoutRewardCourse.setBackgroundResource(R.drawable.library_round_detect)
                }
                3 -> {
                    binding.circleimageReward.setImageResource(R.drawable.course_challenge)
                    binding.constraintlayoutRewardCourse.setBackgroundResource(R.drawable.library_round_challenge)
                }
            }
        }
    }


}