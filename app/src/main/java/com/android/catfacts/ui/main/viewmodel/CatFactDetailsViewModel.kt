package com.android.catfacts.ui.main.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import  androidx.fragment.app.Fragment
import com.android.catfacts.R
import com.android.catfacts.databinding.CatFactDetailsFragmentBinding

class CatFactDetailsViewModel : Fragment() {

    private lateinit var binding: CatFactDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.cat_fact_details_fragment, container, false)

        return binding.root
    }


}