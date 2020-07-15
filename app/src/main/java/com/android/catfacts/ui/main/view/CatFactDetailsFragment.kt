package com.android.catfacts.ui.main.view

import androidx.fragment.app.Fragment
import com.android.catfacts.ui.main.viewmodel.CatFactDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatFactDetailsFragment : Fragment() {
    private val catFactDetailsViewModel: CatFactDetailsViewModel by viewModel()

}