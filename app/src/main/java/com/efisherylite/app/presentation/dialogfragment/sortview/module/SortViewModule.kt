package com.efisherylite.app.presentation.dialogfragment.sortview.module

import com.efisherylite.app.presentation.dialogfragment.sortview.viewmodel.SortViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
val sortViewModule = module {
    viewModel { SortViewModel(androidApplication()) }
}