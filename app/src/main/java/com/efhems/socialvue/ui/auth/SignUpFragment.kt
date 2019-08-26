package com.efhems.socialvue.ui.auth


import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.efhems.socialvue.R
import com.efhems.socialvue.databinding.FragmentSignUpBinding
import com.efhems.socialvue.viewmodel.AuthViewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class SignUpFragment : Fragment() {

    private val TAG = SignUpFragment::class.java.name

    private val viewModel: AuthViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, AuthViewModel.Factory(activity.application)).get(AuthViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this
        val string = SpannableStringBuilder(resources.getString(R.string.conditions))
        string.setSpan(ForegroundColorSpan(Color.RED),
                38, 56, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(UnderlineSpan(), 38, 56, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.spanTv.text = string



        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this.requireContext(),
                android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.prof_list))

        binding.spinner.adapter = arrayAdapter

        viewModel.professional.observe(this, Observer { it ->
            if (it) {
                //Professional here
                binding.wantHire.setBackgroundResource(R.drawable.grey_border)
                binding.wrapProf.setBackgroundResource(R.drawable.selected_user)
                binding.spinner.visibility = VISIBLE

                binding.btnSearch.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_signUpFragment_to_professionalActivity)
                }

            }else{
                binding.wantHire.setBackgroundResource(R.drawable.selected_user)
                binding.wrapProf.setBackgroundResource(R.drawable.grey_border)
                binding.spinner.visibility = GONE
                binding.btnSearch.setOnClickListener {
                    Navigation.findNavController(it).navigate(R.id.action_signUpFragment_to_listHireActivity)
                }
            }

            Log.i(TAG, "isProfessional: $it")
        })

        return binding.root
    }


}
