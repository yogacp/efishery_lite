package com.efisherylite.app.koin.module

import com.efisherylite.app.domain.router.RouterNavigation
import com.efisherylite.app.domain.router.ScreenRouter
import com.efisherylite.app.domain.router.ScreenRouterImpl
import com.google.gson.Gson
import org.koin.dsl.module

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
val appModule = module {
    single { Gson() }
    single<ScreenRouter> { ScreenRouterImpl() }
    single { RouterNavigation(get()) }
}