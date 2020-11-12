package com.efisherylite.app.presentation.entrance.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.viewbinding.library.activity.viewBinding
import com.efisherylite.app.data.constant.AppConstant
import com.efisherylite.app.databinding.ActivityEntranceBinding
import com.efisherylite.app.domain.base.activity.BaseActivity
import com.efisherylite.app.external.extensions.setVisibleIf
import com.efisherylite.app.external.extensions.toast

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class EntranceActivity : BaseActivity() {

    private val binding: ActivityEntranceBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestTransition()
        super.onCreate(savedInstanceState)
        showLoading(true)
        updateProgress(AppConstant.PROGRESSBAR.START)
        runSplashScreen()
    }

    private fun showLoading(isShow: Boolean) {
        binding.loadingView.setVisibleIf(isShow)
        binding.textLoading.setVisibleIf(isShow)
    }

    private fun updateProgress(progress: Float) {
        binding.loadingView.progress = progress
    }

    private fun runSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            kotlin.run {
                updateProgress(AppConstant.PROGRESSBAR.FINISH)
                router.goToHomepage(this)
                showLoading(false)
                //TODO: will remove when homepage is ready
                toast("Redirect to Homepage")
            }
        }, AppConstant.SPLASH_DELAY.toLong())
    }

}