package org.journey.android.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemCourseCatalogBinding

class CourseCatalogAdapter : RecyclerView.Adapter<CourseCatalogAdapter.CourseCatalogViewHolder>() {
    var courseCatalog = mutableListOf<CourseCatalogEntity>()
    class CourseCatalogViewHolder(val binding : ItemCourseCatalogBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseCatalogViewHolder {
        val binding = ItemCourseCatalogBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CourseCatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseCatalogViewHolder, position: Int) {
        val courseList = courseCatalog[position]
        holder.binding.setVariable(BR.data,courseList)

    }

    override fun getItemCount() = courseCatalog.size
}