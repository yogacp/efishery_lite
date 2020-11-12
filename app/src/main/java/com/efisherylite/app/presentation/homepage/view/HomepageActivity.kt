package com.efisherylite.app.presentation.homepage.view

import android.os.Bundle
import android.view.Gravity
import android.viewbinding.library.activity.viewBinding
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.efisherylite.app.R
import com.efisherylite.app.data.model.storagelist.StorageList
import com.efisherylite.app.databinding.ActivityHomepageBinding
import com.efisherylite.app.domain.adapter.banner.SliderAdapter
import com.efisherylite.app.domain.base.activity.BaseActivity
import com.efisherylite.app.external.extensions.setVisibleIf
import com.efisherylite.app.external.extensions.toast
import com.efisherylite.app.external.state.ResultState
import com.efisherylite.app.presentation.homepage.viewmodel.HomepageViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import org.koin.android.viewmodel.ext.android.viewModel

class HomepageActivity : BaseActivity() {

    private val binding: ActivityHomepageBinding by viewBinding()
    private val viewModel: HomepageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        observeStorageList()
        observeImageBanner()
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    private fun setupToolbar() {
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun showLoading(isShow: Boolean) {
        binding.layoutLoading.layoutProgressbar.setVisibleIf(isShow)
    }

    private fun setupView(storages: List<StorageList>) {
        toast("Success. Data fetched: ${storages.size}")
        binding.tvHomepage.text = "Data Fetched: ${storages.size}"
    }

    private fun observeImageBanner() {
        val sliderAdapter = SliderAdapter(this)
        val imageSlider = binding.layoutBanner.imageSlider
        viewModel.imageBanners.observe(this, Observer {
            sliderAdapter.renewItems(it.toMutableList())
            imageSlider.apply {
                setSliderAdapter(sliderAdapter)
                setIndicatorAnimation(IndicatorAnimationType.WORM)
                setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                setIndicatorGravity(Gravity.BOTTOM)

                autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
                indicatorSelectedColor = ContextCompat.getColor(this@HomepageActivity, R.color.dark_teal)
                indicatorUnselectedColor = ContextCompat.getColor(this@HomepageActivity, R.color.white_80)
                scrollTimeInSec = 4
            }.startAutoCycle()
        })
    }

    private fun observeStorageList() {
        viewModel.storageList.observe(this, Observer {
            when (it) {
                is ResultState.Idle -> {
                    // idle
                }
                is ResultState.Loading -> {
                    showLoading(true)
                }
                is ResultState.Error -> {
                    showLoading(false)
                    val throwable = it.throwable
                    toast("error: ${throwable?.message}")
                }
                is ResultState.Message -> {
                    showLoading(false)
                    toast("Message: ${it.msg}")
                }
                is ResultState.Success -> {
                    showLoading(false)
                    val data = it.payload ?: emptyList()
                    setupView(data)
                }
            }
        })
    }
}