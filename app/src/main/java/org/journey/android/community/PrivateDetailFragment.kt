package org.journey.android.community

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.journey.android.R
import org.journey.android.databinding.FragmentPrivateDetailBinding

class PrivateDetailFragment: Fragment() {

    private lateinit var  binding : FragmentPrivateDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
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
            val delete:Button = mView.findViewById(R.id.button_real_delete)
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

        }
}