package com.efisherylite.app.presentation.homepage.view

import android.app.SearchManager
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.efisherylite.app.R
import com.efisherylite.app.data.dao.storagelist.StorageListEntity
import com.efisherylite.app.databinding.ActivityHomepageBinding
import com.efisherylite.app.databinding.ItemStorageListBinding
import com.efisherylite.app.domain.adapter.banner.SliderAdapter
import com.efisherylite.app.domain.base.activity.BaseActivity
import com.efisherylite.app.external.extensions.*
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
        setupSearchview()
        setupToolbar()
        setupSwipeRefresh()
        setupHomepageButtons()
        loadAllData()
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

    private fun loadAllData() {
        showLoading(true)

        if (isNetworkActive()) {
            viewModel.fetchAllData()
        } else {
            refreshStorageList()
        }
    }

    private fun setupSwipeRefresh() {
        binding.layoutStorageList.swipeRefreshLayout.setOnRefreshListener {
            loadAllData()
            binding.layoutStorageList.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupStorage(storages: List<StorageListEntity>) {
        binding.layoutStorageList.rvStorageList.setup(
            storages,
            ItemStorageListBinding::class.java,
            { itemBinding, itemData ->
                itemBinding?.tvKomoditas?.text = itemData.komoditas
                itemBinding?.tvSize?.text =
                    getString(R.string.storage_list_size).format(itemData.size)
                itemBinding?.tvPrice?.text = itemData.price.toIDR()
                itemBinding?.tvArea?.text = getString(R.string.storage_list_area).format(
                    itemData.areaProvinsi,
                    itemData.areaKota
                )

                itemBinding?.tvArea?.setVisibleIf(itemData.areaKota?.isNotEmpty() == true)
                itemBinding?.tvSize?.setVisibleIf(itemData.size?.isNotEmpty() == true)
                itemBinding?.tvPrice?.setVisibleIf(itemData.price?.isNotEmpty() == true)
            },
            {
                toast("${it.komoditas} clicked")
            }
        )
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
                indicatorSelectedColor =
                    ContextCompat.getColor(this@HomepageActivity, R.color.dark_teal)
                indicatorUnselectedColor =
                    ContextCompat.getColor(this@HomepageActivity, R.color.white_80)
                scrollTimeInSec = 4
            }.startAutoCycle()
        })
    }

    private fun observeStorageList() {
        viewModel.storageList.observe(this, Observer {
            when (it) {
                is ResultState.Idle -> {
                    //Idle
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
                    refreshStorageList()
                }
            }
        })
    }

    private fun refreshStorageList() {
        viewModel.getStorageList().observe(this, Observer {
            showLoading(false)
            setupStorage(it)
        })
    }

    private fun setupSearchview() {
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager?
        val clearTextButton =
            binding.layoutSearchView.searchView.findViewById<ImageView>(R.id.search_close_btn)

        binding.layoutSearchView.searchView.setSearchableInfo(
            searchManager?.getSearchableInfo(
                componentName
            )
        )
        binding.layoutSearchView.searchView.clearFocus()
        binding.layoutSearchView.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query.notNullOrEmpty {
                    showLoading(true)
                    viewModel.searchCommodity(it)
                    refreshStorageList()
                }
                return true
            }
        })

        clearTextButton.setOnClickListener {
            clearSearchFocus()
            viewModel.searchCommodity("")
            refreshStorageList()
        }

    }

    private fun clearSearchFocus() {
        binding.layoutSearchView.searchView.setQuery("", false)
        binding.layoutSearchView.searchView.clearFocus()
    }

    private fun hideHomepageButtons() {
        binding.layoutButtons.cardviewButtons.startAnimation(hideTransition)
        binding.layoutButtons.cardviewButtons.visibility = View.GONE
    }

    private fun showHomepageButtons() {
        binding.layoutButtons.cardviewButtons.visibility = View.VISIBLE
        binding.layoutButtons.cardviewButtons.startAnimation(showTransition)
    }

    private fun setupHomepageButtons() {
        initAnimation()

        binding.layoutStorageList.rvStorageList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !binding.layoutButtons.cardviewButtons.isVisible) {
                    showHomepageButtons()
                } else if (dy > 0 && binding.layoutButtons.cardviewButtons.isVisible) {
                    hideHomepageButtons()
                }
            }
        })

        binding.layoutButtons.tvSort.setOnClickListener {
            toast("Sort item clicked")
        }

        binding.layoutButtons.tvFilter.setOnClickListener {
            toast("Filter item clicked")
        }

        binding.layoutButtons.tvAddItem.setOnClickListener {
            toast("Add item clicked")
        }
    }

}