package com.efhems.socialvue.ui.listhire


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.efhems.socialvue.R
import com.efhems.socialvue.databinding.FragmentListProfBinding
import com.efhems.socialvue.model.Model
import com.efhems.socialvue.model.Professional

class ListProfFragment : Fragment(), ProfListAdapter.OnProfClickLister {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentListProfBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_prof, container, false)

        val profAdapter = ProfListAdapter(this)
        binding.rc.setHasFixedSize(false)
        binding.rc.adapter = profAdapter

        //val navController = Navigation.findNavController(binding.rc)

        arguments?.let {
            if (it.containsKey("model")) {
                val model: Model? = it.getParcelable("model")
                binding.categoryName.text = model?.category
                activity?.actionBar?.title = model?.category
                profAdapter.submitList(model?.professionals)
            }
        }

        return binding.root
    }

    override fun onClickProf(view: View, prof: Professional) {
        Toast.makeText(this.context, "View Prof Details", Toast.LENGTH_LONG).show()
    }

}
