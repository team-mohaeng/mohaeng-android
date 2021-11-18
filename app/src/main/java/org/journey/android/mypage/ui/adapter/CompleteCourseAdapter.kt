package org.journey.android.mypage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemCourseCompleteBinding
import org.journey.android.mypage.data.entity.CompleteCourseEntity
import org.journey.android.util.DiffCallback

class CompleteCourseAdapter(): ListAdapter<CompleteCourseEntity, CompleteCourseAdapter.CompleteCourseViewHolder>(
    DiffCallback<CompleteCourseEntity>()) {
    class CompleteCourseViewHolder(val binding : ItemCourseCompleteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteCourseViewHolder {
        val binding = ItemCourseCompleteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompleteCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompleteCourseViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.run {
            setVariable(BR.data, item)
        }
    }
}