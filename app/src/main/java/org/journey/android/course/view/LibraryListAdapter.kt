package org.journey.android.course.view

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.journey.android.databinding.ItemLibraryBinding
import org.journey.android.R
import org.journey.android.course.api.ServiceCreator
import org.journey.android.course.data.ResponseChooseData
import org.journey.android.course.data.ResponseLibraryData
import org.journey.android.databinding.CourseCustomDialogBinding
import org.journey.android.login.view.userJwt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.journey.android.course.data.LibraryListInfo

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
            if(LibraryListInfo.libraryComplete != 2){
                binding.constraintlayoutLibrary.setBackgroundResource(R.drawable.library_round_blue)
                binding.buttonLibraryChoice.setBackgroundResource(R.drawable.library_button_blue)
                when(LibraryListInfo.property){
                    0 -> binding.imageviewLibrary.setImageResource(R.drawable.stamp_health)
                    1 -> binding.imageviewLibrary.setImageResource(R.drawable.stamp_challenge)
                    2 -> binding.imageviewLibrary.setImageResource(R.drawable.stamp_detect)
                    3 -> binding.imageviewLibrary.setImageResource(R.drawable.stamp_memory)
                }
            }
            else{
                binding.constraintlayoutLibrary.setBackgroundResource(R.drawable.library_round_gray)
                binding.buttonLibraryChoice.setBackgroundResource(R.drawable.library_button_gray)
                binding.textviewLibraryTitle.setTextColor(ContextCompat.getColor(ctxt,R.color.journey_gray_h))
                binding.textviewLibraryContent.setTextColor(ContextCompat.getColor(ctxt,R.color.journey_gray_h))
                binding.textviewLibraryTerm.setTextColor(ContextCompat.getColor(ctxt, R.color.journey_gray_f))
                binding.buttonLibraryChoice.setTextColor(ContextCompat.getColor(ctxt, R.color.journey_gray_f))
            }

            // 현재 진행중이라면
            if(LibraryListInfo.libraryComplete == 1){
                binding.buttonLibraryChoice.text = "코스 변경하기"
            }
            else{
                binding.buttonLibraryChoice.text = "코스 시작하기"
            }

            binding.textviewLibraryTitle.text = LibraryListInfo.libraryTitle
            binding.textviewLibraryTerm.text = LibraryListInfo.libraryTerm
            binding.textviewLibraryContent.text = LibraryListInfo.libraryContent
//            Glide.with(itemView)
//                .load(LibraryListInfo.libraryImg)
//                .into(binding.imageviewLibrary)

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
                if(LibraryListInfo.libraryComplete != 2){
                    val dialogButtons = mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
                    dialogButtons.visibility = View.VISIBLE

                    val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
                    okButton.visibility = View.GONE

                    // 현재 진행중인 코스가 없다면
                    if(!libraryState){
                        val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
                        dialogTitle.text = "나와 여정을 떠나보겠어?"
                        val dialogImage = mDialogView.findViewById<ImageView>(R.id.imageview_dialog_image)
                        dialogImage.setImageResource(R.drawable.library_course_start)

                        alertDialog.show()


                        val noButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_no)
                        noButton.text = "좋아!"
                        noButton.setOnClickListener {
                            selectCourse(LibraryListInfo.courseId)
                            alertDialog.dismiss()
                            //Toast.makeText(ctxt, "좋아! 클릭", Toast.LENGTH_SHORT).show()
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
                        val dialogImage = mDialogView.findViewById<ImageView>(R.id.imageview_dialog_image)
                        dialogImage.setImageResource(R.drawable.library_course_stop)
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

        // 서버 연결
        private fun selectCourse(id: Int) {
            ServiceCreator.courseChooseService.putCourseData(
                userJwt,id
            ).enqueue(object : Callback<ResponseChooseData> {
                override fun onFailure(call: Call<ResponseChooseData>, t: Throwable) {
                    Log.d("통신 실패", "${t}")
                }

                override fun onResponse(
                    call: Call<ResponseChooseData>,
                    response: Response<ResponseChooseData>
                ) {
                    // 통신 성공
                    if (response.isSuccessful) {   // statusCode가 200-300 사이일 때, 응답 body 이용 가능
                        if (true) {
                            Log.d("서버 성공", "Library 성공")
                            Log.d(
                                "서버", response.body()!!.data.toString()
                            )
                            Toast.makeText(ctxt, "코스 선택 완료", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d("서버 실패", "${response.body()}")
                        }
                    }
                }
            })
        }
    }

}