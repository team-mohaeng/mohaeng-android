package org.journey.android.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemCourseSituationBinding

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {
    var courseSituation = mutableListOf<CourseEntity>()
    class CourseViewHolder(val binding : ItemCourseSituationBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseSituationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseSituation[position]
        holder.binding.setVariable(BR.data,course)
    }

    override fun getItemCount() = courseSituation.size
}