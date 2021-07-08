package org.journey.android.challenge

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import org.journey.android.R
import org.journey.android.course.ctxt
import org.journey.android.databinding.FragmentChallengeBinding



class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallengeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // 챌린지 시작 여부 알려주는 변수
    private var hasCourse = true
    // 인증 개수 저장하는 변수
    private var stampNumber = 2
    // 완료한 미션 개수를 저장하는 변수
    private var stampComplete = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChallengeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectChallengeView()
        setButtonEvent()
    }

    // 챌린지 시작 여부에 따라 다른 뷰를 보여줌
    fun selectChallengeView(){
        if(hasCourse){
            binding.constraintlayoutChallengeGone.visibility = View.GONE
            binding.constraintlayoutChallengeOngoing.visibility = View.VISIBLE

            var height: Int = 0
            var width: Int = 0

            // Dialog만들기
            val mDialogView = LayoutInflater.from(this.context).inflate(R.layout.course_custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this.context)
                .setView(mDialogView)
            val alertDialog = mBuilder.create()

            mDialogView.setBackgroundColor(Color.TRANSPARENT)

            //val  mAlertDialog = mBuilder.show()
            val window = alertDialog.window
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val dialogTitle = mDialogView.findViewById<TextView>(R.id.textview_dialog_title)
            val dialogButtons = mDialogView.findViewById<ConstraintLayout>(R.id.constraintlayout_dialog_buttons)
            val okButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_ok)
            val noButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_no)
            val changeButton = mDialogView.findViewById<AppCompatButton>(R.id.button_dialog_change)

            // 인증하기 버튼 클릭 이벤트
            fun checkMission(btnStamp: ImageButton){
                dialogTitle.text = "인증하기"
                okButton.text = "완료"

                dialogButtons.visibility = View.GONE
                okButton.visibility = View.VISIBLE

                alertDialog.show()

                okButton.setOnClickListener {
                    alertDialog.dismiss()
                    Toast.makeText(this.context, "완료 클릭", Toast.LENGTH_SHORT).show()
                    btnStamp.setImageResource(R.drawable.stamp_selected)
                    stampComplete = stampComplete + 1
                    Log.d("stamp 확인", stampComplete.toString())
                    if(stampComplete == stampNumber){

                        dialogTitle.text = "오늘의 챌린지 완료"
                        noButton.text = "작성할게!"
                        changeButton.text = "나중에"

                        noButton.setOnClickListener {
                            alertDialog.dismiss()
                            Toast.makeText(this.context, "작성할게! 클릭", Toast.LENGTH_SHORT).show()
                        }
                        changeButton.setOnClickListener {
                            alertDialog.dismiss()
                            Toast.makeText(this.context, "나중에 클릭", Toast.LENGTH_SHORT).show()
                        }

                        dialogButtons.visibility = View.VISIBLE
                        okButton.visibility = View.GONE

                        alertDialog.show()
                    }
                }
                btnStamp.isEnabled = false
            }

            // 미션 개수에 따라 다르게 보여줌
            if(stampNumber == 1){
                binding.constraintlayoutSubFirst.visibility = View.VISIBLE
                binding.constraintlayoutSubSecond.visibility = View.GONE
                binding.constraintlayoutSubThird.visibility = View.GONE

                height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140F, getResources().getDisplayMetrics()).toInt()
                width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120F, getResources().getDisplayMetrics()).toInt()

                // 스탬프 누르면 인증 팝업 등장
                binding.imagebuttonFirstFirst.setOnClickListener{
                    checkMission(binding.imagebuttonFirstFirst)
                }

            }
            else if(stampNumber == 2){
                binding.constraintlayoutSubFirst.visibility = View.GONE
                binding.constraintlayoutSubSecond.visibility = View.VISIBLE
                binding.constraintlayoutSubThird.visibility = View.GONE

                height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140F, getResources().getDisplayMetrics()).toInt()
                width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120F, getResources().getDisplayMetrics()).toInt()

                // 스탬프 누르면 인증 팝업 등장
                binding.imagebuttonSecondFirst.setOnClickListener{
                    checkMission(binding.imagebuttonSecondFirst)
                }
                binding.imagebuttonSecondSecond.setOnClickListener{
                    checkMission(binding.imagebuttonSecondSecond)
                }
            }
            else if(stampNumber == 3){
                binding.constraintlayoutSubFirst.visibility = View.GONE
                binding.constraintlayoutSubSecond.visibility = View.GONE
                binding.constraintlayoutSubThird.visibility = View.VISIBLE
                height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 124F, getResources().getDisplayMetrics()).toInt()
                width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 106F, getResources().getDisplayMetrics()).toInt()

                // 스탬프 누르면 인증 팝업 등장
                binding.imagebuttonThirdFirst.setOnClickListener{
                    checkMission(binding.imagebuttonThirdFirst)
                }
                binding.imagebuttonThirdSecond.setOnClickListener{
                    checkMission(binding.imagebuttonThirdSecond)
                }
                binding.imagebuttonThirdThird.setOnClickListener{
                    checkMission(binding.imagebuttonThirdThird)
                }
            }
            binding.imageviewChallengeJourney.getLayoutParams().height=height
            binding.imageviewChallengeJourney.getLayoutParams().width=width
            binding.imageviewChallengeJourney.requestLayout()
        }
        else{
            binding.constraintlayoutChallengeGone.visibility = View.VISIBLE
            binding.constraintlayoutChallengeOngoing.visibility = View.GONE
        }
    }

    // 버튼 이벤트
    fun setButtonEvent(){
        // 챌린지 시작 전 코스 라이브러리로 이동하는 버튼
        binding.buttonChallengeCourse.setOnClickListener {
            
        }

        // 챌린지 진행 중 스탬프 찍는 버튼 이벤트
        /*
        binding.imagebuttonStampFirst.setOnClickListener{
            binding.imagebuttonStampFirst.setImageResource(R.drawable.stamp_selected)
        }
        binding.imagebuttonStampSecond.setOnClickListener {
            binding.imagebuttonStampFirst.setImageResource(R.drawable.stamp_selected)
        }
        binding.imagebuttonStampThird.setOnClickListener{
            binding.imagebuttonStampFirst.setImageResource(R.drawable.stamp_selected)
        }
*/
        // 챌린지 진행 중 현재 진행중인 코스로 이동하는 버튼
        binding.imagebuttonChallengeCourse.setOnClickListener {
            //Navigation.findNavController(binding.root)
            //    .navigate(R.id.course)

        }

        // 챌린지 진행 중 코스 라이브러리로 이동하는 버튼
        binding.imagebuttonChallengeBrowse.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}