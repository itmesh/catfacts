package com.android.catfacts.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.catfacts.R
import com.android.catfacts.databinding.CatFactDetailsFragmentBinding
import kotlinx.android.synthetic.main.cat_facts_fragment.*

class CatFactDetailsFragment : Fragment() {
    private lateinit var binding: CatFactDetailsFragmentBinding

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

    }

}