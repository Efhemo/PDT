package com.efhems.socialvue


import android.os.Bundle

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.efhems.socialvue.databinding.FragmentSignInBinding
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sign_in, container, false)

        val binding: FragmentSignInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        val navController = NavHostFragment.findNavController(this)

        binding.btnSignIn.setOnClickListener {

            //Just for seeing the warning
            if(binding.warning.visibility == View.VISIBLE){
                binding.warning.visibility = View.GONE
            }else{
                binding.warning.visibility = View.VISIBLE
            }
        }

        binding.noAcct.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        return binding.root
    }

}// Required empty public constructor
