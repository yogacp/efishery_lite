package com.efisherylite.app.domain.base.activity

import android.transition.Explode
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.efisherylite.app.domain.router.RouterNavigation
import org.koin.android.ext.android.inject

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
abstract class BaseActivity : AppCompatActivity() {

    val router: RouterNavigation by inject()

    fun requestTransition() {
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            exitTransition = Explode()
        }
    }
}