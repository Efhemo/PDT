package com.efhems.socialvue.ui.response


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.efhems.socialvue.R
import com.efhems.socialvue.databinding.FragmentQueryBinding


class QueryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentQueryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_query, container, false)


        binding.wrapReport.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_queryFragment_to_categoryFragment2)
        }

        return binding.root
    }


}
