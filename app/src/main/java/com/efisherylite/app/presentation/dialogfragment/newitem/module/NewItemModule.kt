package com.efisherylite.app.presentation.dialogfragment.newitem.module

import com.efisherylite.app.presentation.dialogfragment.newitem.viewmodel.NewItemViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
val newItemModule = module {
    viewModel { NewItemViewModel(androidApplication()) }
}