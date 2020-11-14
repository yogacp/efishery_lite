package com.efisherylite.app.koin.components

import com.efisherylite.app.koin.module.appModule
import com.efisherylite.app.koin.module.databaseModule
import com.efisherylite.app.koin.module.networkModule
import com.efisherylite.app.presentation.homepage.module.homepageModule
import org.koin.core.module.Module

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
val appComponent: List<Module> = listOf(
    /**
     * Core Module
     */
    appModule,
    databaseModule,
    networkModule,

    /**
     * Feature Module
     */
    homepageModule,
)