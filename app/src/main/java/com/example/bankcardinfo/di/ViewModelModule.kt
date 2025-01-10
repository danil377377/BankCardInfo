package com.example.bankcardinfo.di

import com.example.bankcardinfo.presentation.CardInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CardInfoViewModel(get(), get())
    }
}