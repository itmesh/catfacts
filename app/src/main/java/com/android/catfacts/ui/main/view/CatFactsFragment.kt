package com.android.catfacts.ui.main.view

import CatFactsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.catfacts.R
import com.android.catfacts.databinding.CatFactsFragmentBinding
import com.android.catfacts.ui.main.viewmodel.CatFactsViewModel
import kotlinx.android.synthetic.main.cat_facts_fragment.*

class CatFactsFragment : Fragment() {
    private lateinit var viewModel: CatFactsViewModel
    private lateinit var binding: CatFactsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cat_facts_fragment, container, false)
        viewModel = ViewModelProvider(this).get(CatFactsViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.catFactsLiveData.observe(viewLifecycleOwner, Observer { facts ->
            recycleView.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CatFactsAdapter(facts) { catId -> openCatDetails(catId, view) }
            }
        })
        downloadFab.setOnClickListener {
            viewModel.fetchCatFacts()
        }
    }

    private fun openCatDetails(catId: String, view: View) {
        val action = CatFactsFragmentDirections.listToDetails(catId)
        view.findNavController().navigate(action)
    }

}