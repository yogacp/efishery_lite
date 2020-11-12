package com.efisherylite.app.presentation.homepage.module

import com.efisherylite.app.presentation.homepage.viewmodel.HomepageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
val homepageModule = module {
    viewModel { HomepageViewModel(androidApplication()) }
}