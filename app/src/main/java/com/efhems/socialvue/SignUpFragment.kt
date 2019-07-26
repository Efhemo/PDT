package com.efhems.socialvue


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.efhems.socialvue.databinding.FragmentSignUpBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val binding: FragmentSignUpBinding   = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        // return inflater.inflate(R.layout.fragment_sign_up, container, false)


        val string = SpannableStringBuilder(resources.getString(R.string.conditions))

        string.setSpan(ForegroundColorSpan(Color.RED),
                38, 56, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(UnderlineSpan(), 38, 56, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE )

        binding.spanTv.text = string

        return binding.root
    }


}
