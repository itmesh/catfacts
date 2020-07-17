package com.android.catfacts.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.catfacts.R
import com.android.catfacts.databinding.CatFactDetailsFragmentBinding
import com.android.catfacts.ui.main.viewmodel.CatFactDetailsViewModel
import kotlinx.android.synthetic.main.cat_fact_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatFactDetailsFragment : Fragment() {
    private lateinit var binding: CatFactDetailsFragmentBinding
    private val catFactDetailsFragment: CatFactDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.cat_fact_details_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catFactDetailsFragment.fetchCatFactDetails((arguments?.getString("id"))!!)
        loadingViewDetails.visibility = View.VISIBLE
        catDetailsLayout.visibility = View.GONE
        errorDetailsLayout.visibility = View.GONE
        catFactDetailsFragment.catFactDetailsData.observe(viewLifecycleOwner, Observer { fact ->
            catFactText.text = fact.text
            updateDate.text = fact.updatedAt
            catDetailsLayout.visibility = View.VISIBLE
            errorDetailsLayout.visibility = View.GONE
        })
        catFactDetailsFragment.loadingLiveData.observe(viewLifecycleOwner, Observer { loading ->
            loadingViewDetails.visibility = if (loading) View.VISIBLE else View.GONE
        })
        catFactDetailsFragment.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            errorDetailsText.text = error
            errorDetailsLayout.visibility = View.VISIBLE
            catDetailsLayout.visibility = View.GONE
        })
        tryAgainDetailsButton.setOnClickListener {
            catFactDetailsFragment.fetchCatFactDetails((arguments?.getString("id"))!!)
        }
    }

    override fun onDestroy() {
        catFactDetailsFragment.cancelAllRequest()
        super.onDestroy()
    }
}