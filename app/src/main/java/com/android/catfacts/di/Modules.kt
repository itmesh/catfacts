package com.android.catfacts.di

import com.android.catfacts.data.api.ApiFactory
import com.android.catfacts.data.repository.Repository
import com.android.catfacts.data.repository.RepositoryImp
import com.android.catfacts.ui.main.viewmodel.CatFactDetailsViewModel
import com.android.catfacts.ui.main.viewmodel.CatFactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { ApiFactory.api }
}

val viewModelModule = module {
    viewModel {
        CatFactsViewModel(get())
    }
    viewModel {
        CatFactDetailsViewModel(get())
    }
}

val repositoryModule = module {
    single<Repository> {
        get<RepositoryImp>()
    }
    single { RepositoryImp(get()) }

}