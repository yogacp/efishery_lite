package com.efisherylite.app.domain.router

import android.content.Context
import android.content.Intent

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface ScreenRouter {

    sealed class ActivityScreen {
        object Homepage : ActivityScreen()
    }

    fun getScreenIntent(context: Context, screen: ActivityScreen): Intent?
}