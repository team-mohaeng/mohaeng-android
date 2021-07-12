package org.journey.android.diary

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateDetailBinding

class PrivateDetailFragment: Fragment() {

    private lateinit var  binding : FragmentPrivateDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentPrivateDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Glide.with(this)
//                .load("http://goo.gl/gEgYUd")
//                .circleCrop()
//                .into(binding.imageviewPrivateDetailFront)
//        Glide.with(this)
//            .load("http://goo.gl/gEgYUd")
//            .circleCrop()
//            .into(binding.imageviewPrivateDetailBack)


        binding.buttonPrivateDelete.setOnClickListener()
        {
            val deleteDialog = activity?.let { it1 -> Dialog(it1) }
            val deleteDialogInflater : LayoutInflater = LayoutInflater.from(activity)
            val mView : View = deleteDialogInflater.inflate(R.layout.private_delete_message_dialog,null)
            val back : Button = mView.findViewById(R.id.button_do_not_delete)
            if (deleteDialog != null) {
                deleteDialog.setContentView(mView)
                deleteDialog.create()
                deleteDialog.show()
            }
            back.setOnClickListener{
                if (deleteDialog != null)
                {deleteDialog.dismiss()
                    deleteDialog.cancel()}
            }
        }
        val testString = "#긴 해시태" // 서버로부터 해시태그를 저장하는 문자열 불러오면 될듯
        if(testString.length<20)
        {
            val shortHashtagString = "\n"+testString
            binding.textviewPrivateDetailHashtag.isVisible = true
            binding.textviewPrivateDetailHashtag.text = shortHashtagString
        }
        else{
            val shortHashtagString = testString.substring(0,18) + "\n" + testString.substring(19, testString.length-1)
            binding.textviewPrivateDetailHashtag.isVisible = true
            binding.textviewPrivateDetailHashtag.text = shortHashtagString
        }
    }

}