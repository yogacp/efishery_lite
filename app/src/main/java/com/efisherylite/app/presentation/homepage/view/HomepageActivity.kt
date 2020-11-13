package com.efisherylite.app.presentation.homepage.view

import android.app.SearchManager
import android.os.Bundle
import android.view.Gravity
import android.viewbinding.library.activity.viewBinding
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.efisherylite.app.R
import com.efisherylite.app.data.dao.optionarea.OptionAreaEntity
import com.efisherylite.app.data.dao.optionsize.OptionSizeEntity
import com.efisherylite.app.data.dao.storagelist.StorageListEntity
import com.efisherylite.app.databinding.ActivityHomepageBinding
import com.efisherylite.app.domain.adapter.banner.SliderAdapter
import com.efisherylite.app.domain.base.activity.BaseActivity
import com.efisherylite.app.external.extensions.notNullOrEmpty
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
        observeImageBanner()
        observeStorageList()
        observeOptionArea()
        observeOptionSize()
        setupSearchview()
        setupToolbar()
        viewModel.fetchAllData()
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

    private fun setupStorage(storages: List<StorageListEntity>) {
        binding.tvHomepage.text = "Storage List Fetched: ${storages.size} data"
    }

    private fun setupArea(areas: List<OptionAreaEntity>) {
        binding.tvArea.text = "Option Area Fetched: ${areas.size} area"
    }

    private fun setupSize(sizes: List<OptionSizeEntity>) {
        binding.tvSize.text = "Option Size Fetched: ${sizes.size} size"
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
                    observeGetStorages()
                }
            }
        })
    }

    private fun observeOptionArea() {
        viewModel.optionAreas.observe(this, Observer {
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
                    observeGetAreas()
                }
            }
        })
    }

    private fun observeOptionSize() {
        viewModel.optionSizes.observe(this, Observer {
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
                    observeGetSizes()
                }
            }
        })
    }

    private fun observeGetStorages() {
        viewModel.getSavedStorages().observe(this, Observer {
            setupStorage(it)
        })
    }

    private fun observeGetAreas() {
        viewModel.getSavedAreas().observe(this, Observer {
            setupArea(it)
        })
    }

    private fun observeGetSizes() {
        viewModel.getSavedSizes().observe(this, Observer {
            setupSize(it)
        })
    }

    private fun setupSearchview() {
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager?
        val clearTextButton = binding.layoutSearchView.searchView.findViewById<ImageView>(R.id.search_close_btn)

        binding.layoutSearchView.searchView.setSearchableInfo(searchManager?.getSearchableInfo(componentName))
        binding.layoutSearchView.searchView.clearFocus()
        binding.layoutSearchView.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query.notNullOrEmpty {
                    viewModel.searchStorageList(it)

                    //TODO: Will remove when searchStorage is done
                    toast("Searching")
                }
                return true
            }
        })

        clearTextButton.setOnClickListener {
            clearSearchFocus()
        }

    }

    private fun clearSearchFocus() {
        binding.layoutSearchView.searchView.setQuery("", false)
        binding.layoutSearchView.searchView.clearFocus()
    }

}