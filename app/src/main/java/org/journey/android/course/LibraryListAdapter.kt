package org.journey.android.course

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.databinding.ItemLibraryBinding
import com.bumptech.glide.Glide
import org.journey.android.R
import org.journey.android.databinding.CourseCustomDialogBinding

lateinit var ctxt : Context
var libraryState: Boolean = true

class LibraryListAdapter :RecyclerView.Adapter<LibraryListAdapter.LibraryListViewHolder>() {
    val libraryList = mutableListOf<LibraryListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryListViewHolder {
        val binding = ItemLibraryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        // 컨텍스트 저장
        ctxt = parent.context
        // 현재 코스를 진행중인지 아닌지 저장
        // false : 진행중 X
        libraryState= false

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
            // libraryComplete가 false면 완료하지 않은 코스
            // false면 완료한 코스
            if(!LibraryListInfo.libraryComplete){
                binding.constraintlayoutLibrary.setBackgroundResource(R.drawable.library_round_blue)
                binding.buttonLibraryChoice.setBackgroundResource(R.drawable.library_button_blue)
            }
            else{
                binding.constraintlayoutLibrary.setBackgroundResource(R.drawable.library_round_gray)
                binding.buttonLibraryChoice.setBackgroundResource(R.drawable.library_button_gray)
            }

            // 현재 진행중이라면
            if(libraryState){
                binding.buttonLibraryChoice.text = "코스 변경하기"
            }
            else{
                binding.buttonLibraryChoice.text = "코스 시작하기"
            }

            binding.textviewLibraryTitle.text = LibraryListInfo.libraryTitle
            binding.textviewLibraryTerm.text = LibraryListInfo.libraryTerm
            binding.textviewLibraryContent.text = LibraryListInfo.libraryContent
            Glide.with(itemView)
                .load(LibraryListInfo.libraryImg)
                .into(binding.imageviewLibrary)

            // click 이벤트
            // 만약에 현재 진행중인 코스가 있다면 변경할래 팝업, 아니라면 코스시작 팝업
            // 완료한 코스라면 이미 완료했다는 팝업
            binding.buttonLibraryChoice.setOnClickListener {

                // Dialog만들기
                val mDialogView = LayoutInflater.from(ctxt).inflate(R.layout.course_custom_dialog, null)
                val mBuilder = AlertDialog.Builder(ctxt)
                    .setView(mDialogView)
                val alertDialog = mBuilder.create()

                mDialogView.setBackgroundColor(Color.TRANSPARENT)

                //val  mAlertDialog = mBuilder.show()
                val window = alertDialog.window
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                // 완료한 코스가 아니라면
                if(!LibraryListInfo.libraryComplete){
                    val dialogButtons = mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
                    dialogButtons.visibility = View.VISIBLE

                    val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
                    okButton.visibility = View.GONE

                    // 현재 진행중인 코스가 없다면
                    if(!libraryState){
                        val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
                        dialogTitle.text = "나와 여정을 떠나보겠어?"
                        alertDialog.show()


                        val noButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_no)
                        noButton.text = "좋아!"
                        noButton.setOnClickListener {
                            alertDialog.dismiss()
                            Toast.makeText(ctxt, "좋아! 클릭", Toast.LENGTH_SHORT).show()
                        }

                        val changeButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_change)
                        changeButton.text = "다시 생각해볼게"
                        changeButton.setOnClickListener {
                            alertDialog.dismiss()
                        }
                    }
                    else{
                        val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
                        dialogTitle.text = "쟈기, 지금 포기하는거야?"
                        alertDialog.show()

                        val noButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_no)
                        noButton.setOnClickListener {
                            alertDialog.dismiss()
                            Toast.makeText(ctxt, "포기 안 해! 클릭", Toast.LENGTH_SHORT).show()
                        }

                        val changeButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_change)
                        changeButton.setOnClickListener {
                            alertDialog.dismiss()
                            Toast.makeText(ctxt, "변경할래 클릭", Toast.LENGTH_SHORT).show()
                        }
                    }


                }
                // 완료한 코스라면
//                else{
//                    val dialogButtons = mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
//                    dialogButtons.visibility = View.GONE
//
//                    val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
//                    okButton.visibility = View.VISIBLE
//
//                    alertDialog.show()
//                    val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
//                    dialogTitle.text = "(이미 완료한 코스)"
//
//                    okButton.text = "닫기!"
//                    okButton.setOnClickListener {
//                        alertDialog.dismiss()
//                        Toast.makeText(ctxt, "닫기! 클릭", Toast.LENGTH_SHORT).show()
//                    }
//                }
            }
        }
    }


}