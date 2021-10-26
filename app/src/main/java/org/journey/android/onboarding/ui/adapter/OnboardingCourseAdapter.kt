package org.journey.android.onboarding.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.BR
import org.journey.android.databinding.ItemOnboardingCourseBinding
import org.journey.android.onboarding.data.OnboardingCourseEntity

class OnboardingCourseAdapter :
    RecyclerView.Adapter<OnboardingCourseAdapter.OnboardingCourseViewHolder>(){
    var selectCourse = mutableListOf<OnboardingCourseEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingCourseViewHolder {
        val binding = ItemOnboardingCourseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OnboardingCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingCourseViewHolder, position: Int) {
        val course = selectCourse[position]
        holder.binding.setVariable(BR.data, course)
    }

    override fun getItemCount(): Int = selectCourse.size

    class OnboardingCourseViewHolder(val binding : ItemOnboardingCourseBinding) : RecyclerView.ViewHolder(binding.root)

}
