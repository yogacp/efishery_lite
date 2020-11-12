package com.efisherylite.app.domain.router

import android.app.ActivityOptions
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class RouterNavigation(private val screenRouter: ScreenRouter) {

    fun goToHomepage(context: AppCompatActivity) {
        val screen = screenRouter.getScreenIntent(context, ScreenRouter.ActivityScreen.Homepage)
        screen?.run {
            context.startActivity(
                this,
                ActivityOptions.makeSceneTransitionAnimation(context).toBundle()
            )
        }
    }

}