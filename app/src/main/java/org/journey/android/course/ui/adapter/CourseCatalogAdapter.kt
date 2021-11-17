package org.journey.android.course.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.community.ui.adapter.CommunityPostAdapter
import org.journey.android.course.data.entity.CourseCatalogEntity
import org.journey.android.databinding.ItemCourseCatalogBinding

class CourseCatalogAdapter(val listener : OnItemClickListener) : RecyclerView.Adapter<CourseCatalogAdapter.CourseCatalogViewHolder>() {
    var courseCatalog = mutableListOf<CourseCatalogEntity>()
    interface OnItemClickListener {
        fun selectCourse()
    }
    class CourseCatalogViewHolder(val binding : ItemCourseCatalogBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCatalogViewHolder {
        val binding = ItemCourseCatalogBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CourseCatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseCatalogViewHolder, position: Int) {
        val courseList = courseCatalog[position]
        holder.binding.setVariable(BR.data,courseList)
        holder.binding.root.setOnClickListener { listener.selectCourse() }
    }

    override fun getItemCount() = courseCatalog.size
}