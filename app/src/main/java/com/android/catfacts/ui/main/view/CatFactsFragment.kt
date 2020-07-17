package com.android.catfacts.ui.main.view

import CatFactsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.catfacts.R
import com.android.catfacts.databinding.CatFactsFragmentBinding
import com.android.catfacts.ui.main.viewmodel.CatFactsViewModel
import kotlinx.android.synthetic.main.cat_facts_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatFactsFragment : Fragment() {
    private lateinit var binding: CatFactsFragmentBinding
    private val catFactsViewModel: CatFactsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.cat_facts_fragment, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleViewLayout.visibility = View.GONE
        emptyListLayout.visibility = View.VISIBLE
        initDownloadButton.setOnClickListener {
            catFactsViewModel.fetchCatFacts()
        }
        catFactsViewModel.catFactsLiveData.observe(viewLifecycleOwner, Observer { facts ->
            recycleView.also {
                if (facts.size > 0) {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = CatFactsAdapter(facts) { catId -> openCatDetails(catId, view) }
                    recycleViewLayout.visibility = View.VISIBLE
                    emptyListLayout.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                } else {
                    recycleViewLayout.visibility = View.GONE
                    emptyListLayout.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                }
            }
        })
        catFactsViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { loading ->
            loadingView.visibility = if (loading) View.VISIBLE else View.GONE
        })
        catFactsViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            recycleViewLayout.visibility = View.GONE
            emptyListLayout.visibility = View.GONE
            errorText.text = error
            errorLayout.visibility = View.VISIBLE
        })
        downloadFab.setOnClickListener {
            catFactsViewModel.fetchCatFacts()
        }
        tryAgainButton.setOnClickListener {
            catFactsViewModel.fetchCatFacts()
        }
    }

    private fun openCatDetails(catId: String, view: View) {
        val action = CatFactsFragmentDirections.listToDetails(catId)
        view.findNavController().navigate(action)
    }

    override fun onDestroy() {
        catFactsViewModel.cancelAllRequest()
        super.onDestroy()
    }

}