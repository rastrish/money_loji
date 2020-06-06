package com.zerone.moneyloji.di

import com.google.gson.Gson
import com.zerone.moneyloji.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    viewModel { MainViewModel(androidContext()) }
    single { Gson() }
}