package com.efisherylite.app.presentation.dialogfragment.sortview.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.efisherylite.app.data.constant.FilterConstant
import com.efisherylite.app.domain.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
class SortViewModel(application: Application) : BaseViewModel(application) {

    var selectedFilter = MutableLiveData<Int>()

    fun setSelectedSortFilter(selected: String) {
        viewModelScope.launch {
            when {
                selected.contains("harga terendah", ignoreCase = true) -> {
                    selectedFilter.postValue(FilterConstant.LOWEST_PRICE)
                }
                selected.contains("harga tertinggi", ignoreCase = true) -> {
                    selectedFilter.postValue(FilterConstant.HIGHEST_PRICE)
                }
                selected.contains("ukuran terkecil", ignoreCase = true) -> {
                    selectedFilter.postValue(FilterConstant.LOWEST_SIZE)
                }
                selected.contains("ukuran terbesar", ignoreCase = true) -> {
                    selectedFilter.postValue(FilterConstant.HIGHEST_SIZE)
                }
                else -> selectedFilter.postValue(FilterConstant.DEFAULT)
            }
        }
    }
}