package com.efisherylite.app.domain.router

import android.content.Context
import android.content.Intent

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class ScreenRouterImpl : ScreenRouter {

    override fun getScreenIntent(context: Context, screen: ScreenRouter.ActivityScreen): Intent? {
        val klazz: Class<*>? = when(screen) {
            ScreenRouter.ActivityScreen.Homepage -> null
        }

        return if(klazz == null) null else Intent(context, klazz)
    }

}