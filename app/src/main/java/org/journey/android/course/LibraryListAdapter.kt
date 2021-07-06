package org.journey.android.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.databinding.ItemLibraryBinding
import com.bumptech.glide.Glide
import org.journey.android.R

class LibraryListAdapter :RecyclerView.Adapter<LibraryListAdapter.LibraryListViewHolder>() {
    val libraryList = mutableListOf<LibraryListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryListViewHolder {
        val binding = ItemLibraryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LibraryListViewHolder(binding)
    }

    override fun getItemCount(): Int = libraryList.size

    override fun onBindViewHolder(holder: LibraryListViewHolder, position: Int) {
        holder.onBind(libraryList[position])
    }

    class LibraryListViewHolder(
        private val binding: ItemLibraryBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(LibraryListInfo: LibraryListInfo){
            // libraryState가 true면 현재 진행중인 코스
            // false면 아직 시작하지 않은 코스
            if(LibraryListInfo.libraryState){
                binding.constraintlayoutLibrary.setBackgroundResource(R.drawable.library_round_blue)
                binding.buttonLibraryChoice.setBackgroundResource(R.drawable.library_button_blue)
            }
            else{
                binding.constraintlayoutLibrary.setBackgroundResource(R.drawable.library_round_gray)
                binding.buttonLibraryChoice.setBackgroundResource(R.drawable.library_button_gray)
            }

            binding.textviewLibraryTitle.text = LibraryListInfo.libraryTitle
            binding.textviewLibraryTerm.text = LibraryListInfo.libraryTerm
            binding.textviewLibraryContent.text = LibraryListInfo.libraryContent
            Glide.with(itemView)
                .load(LibraryListInfo.libraryImg)
                .into(binding.imageviewLibrary)
        }
    }

}