package org.journey.android.course

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.R
import org.journey.android.databinding.ItemCourseFirstBinding
import org.journey.android.databinding.ItemCourseLeftBinding
import org.journey.android.databinding.ItemCourseRightBinding

class CourseListAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val courseList = mutableListOf<CourseListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> {
                val binding = ItemCourseFirstBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CourseFirstViewHolder(binding)
            }
            1 -> {
                val binding = ItemCourseRightBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CourseRightViewHolder(binding)
            }
            else -> {
                val binding = ItemCourseLeftBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CourseLeftViewHolder(binding)
            }
        }
    }
    override fun getItemCount(): Int = courseList.size

    override fun getItemViewType(position: Int): Int {
        return courseList[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // holder.onBind(repoList[position])

        when(courseList[position].type) {
            0 -> {
                (holder as CourseFirstViewHolder).onBind(courseList[position])
                holder.setIsRecyclable(false)
            }
            1 -> {
                (holder as CourseRightViewHolder).onBind(courseList[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as CourseLeftViewHolder).onBind(courseList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    class CourseFirstViewHolder(
        private val binding: ItemCourseFirstBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(CourseListInfo: CourseListInfo){
            binding.textviewProcessDay.text = CourseListInfo.courseDay
            binding.textviewProcessContent.text = CourseListInfo.courseContent

            if(CourseListInfo.courseComplete.isNotEmpty()){
                binding.textviewProcessComplete.visibility = View.VISIBLE
                binding.textviewProcessComplete.text = CourseListInfo.courseComplete
            }
            else
                binding.textviewProcessComplete.visibility = View.INVISIBLE

            if(CourseListInfo.courseCurrent)
                binding.imageviewProcessFirst.setImageResource(R.drawable.ic_course_first)
            else
                binding.imageviewProcessFirst.setImageResource(R.drawable.ic_course_first)
        }
    }

    class CourseRightViewHolder(
        private val binding: ItemCourseRightBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(CourseListInfo: CourseListInfo){
            binding.textviewRightDay.text = CourseListInfo.courseDay
            binding.textviewRightContent.text = CourseListInfo.courseContent

            if(CourseListInfo.courseComplete.isNotEmpty()){
                binding.textviewRightComplete.visibility = View.VISIBLE
                binding.textviewRightComplete.text = CourseListInfo.courseComplete
            }
            else
                binding.textviewRightComplete.visibility = View.INVISIBLE

            if(CourseListInfo.courseCurrent)
                binding.imageviewRightRoad.setImageResource(R.drawable.ic_course_pink_right)
            else
                binding.imageviewRightRoad.setImageResource(R.drawable.ic_course_gray_right)
        }
    }

    class CourseLeftViewHolder(
        private val binding: ItemCourseLeftBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(CourseListInfo: CourseListInfo){
            binding.textviewLeftDay.text = CourseListInfo.courseDay
            binding.textviewLeftContent.text = CourseListInfo.courseContent

            if(CourseListInfo.courseComplete.isNotEmpty()){
                binding.textviewLeftComplete.visibility = View.VISIBLE
                binding.textviewLeftComplete.text = CourseListInfo.courseComplete
            }
            else
                binding.textviewLeftComplete.visibility = View.INVISIBLE



            if(CourseListInfo.courseCurrent)
                binding.imageviewLeftRoad.setImageResource(R.drawable.ic_course_pink_left)
            else
                binding.imageviewLeftRoad.setImageResource(R.drawable.ic_course_gray_left)
        }
    }
}