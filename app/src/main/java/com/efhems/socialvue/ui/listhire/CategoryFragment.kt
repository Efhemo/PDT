package com.efhems.socialvue.ui.listhire


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.efhems.socialvue.R
import com.efhems.socialvue.databinding.FragmentCategoryBinding
import com.efhems.socialvue.model.Model
import com.efhems.socialvue.viewmodel.HomeViewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class CategoryFragment : Fragment(), HireListAdapter.OnHireClickLister {

    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(activity){
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        val adapter = HireListAdapter(this)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(false)

        viewModel.listCat.observe(this, Observer {
            adapter.submitList(it)
        })

        binding.nextBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_categoryFragment2_to_otherQuestionFragment)
        }

        return binding.root
    }

    override fun onClickHire(view: View, model: Model) {

        view.setBackgroundResource(R.drawable.selected_user)

        /*val bundle = Bundle()
        bundle.putParcelable("model", model)
        Navigation.findNavController(view).navigate(R.id.action_categoryFragment_to_listProfFragment, bundle)*/
    }

}
