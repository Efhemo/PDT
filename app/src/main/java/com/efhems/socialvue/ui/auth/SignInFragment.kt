package com.efhems.socialvue.ui.auth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.efhems.socialvue.R
import com.efhems.socialvue.databinding.FragmentSignInBinding
import com.efhems.socialvue.viewmodel.AuthViewModel


/**
 * A simple [Fragment] subclass.
 */
class SignInFragment : Fragment() {

    private val viewModel: AuthViewModel by lazy {

        ViewModelProviders.of(this).get(AuthViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sign_in, container, false)

        val binding: FragmentSignInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        binding.lifecycleOwner = this
        val navController = NavHostFragment.findNavController(this)

        binding.btnSignIn.setOnClickListener {

            //Just for seeing the warning
            /*if (binding.warning.visibility == View.VISIBLE) {
                binding.warning.visibility = View.GONE
            } else {
                binding.warning.visibility = View.VISIBLE
            }*/

            signIn(navController, binding)

        }

        binding.noAcct.setOnClickListener {
            //Toast.makeText(this.context, "Wont work for now", Toast.LENGTH_LONG).show()
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        return binding.root
    }

    private fun signIn(navcontroller: NavController,  binding: FragmentSignInBinding) {
        val email = binding.tvInputEmail.editText?.text.toString()
        val password = binding.textInputLayout.editText?.text.toString()

        if (email.isEmpty()) {
            binding.warning.visibility = View.VISIBLE
            return
        }

        if (password.isEmpty()) {
            binding.warning.visibility = View.VISIBLE
            return
        }

        navcontroller.navigate(R.id.action_signInFragment_to_profResponseActivity)
    }

}// Required empty public constructor
