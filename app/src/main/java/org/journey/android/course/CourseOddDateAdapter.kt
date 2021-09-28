package org.journey.android.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemCourseStampBinding

class CourseOddDateAdapter : RecyclerView.Adapter<CourseOddDateAdapter.CourseOddDateViewHolder>() {
    var oddDate = mutableListOf<CourseOddDateEntity>()
    class CourseOddDateViewHolder(val binding : ItemCourseStampBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseOddDateViewHolder {
        val binding = ItemCourseStampBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CourseOddDateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseOddDateViewHolder, position: Int) {
        val odd = oddDate[position]
        holder.binding.setVariable(BR.data,odd)
    }
    override fun getItemCount() = oddDate.size

}