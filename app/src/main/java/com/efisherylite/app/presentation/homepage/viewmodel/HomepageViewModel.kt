package com.efisherylite.app.presentation.homepage.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.efisherylite.app.data.model.banner.SliderItem
import com.efisherylite.app.data.model.storagelist.StorageList
import com.efisherylite.app.domain.base.viewmodel.BaseViewModel
import com.efisherylite.app.external.extensions.fetchData
import com.efisherylite.app.external.extensions.liveDataOf
import kotlinx.coroutines.launch

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class HomepageViewModel (application: Application): BaseViewModel(application) {

    val storageList = liveDataOf<List<StorageList>>()
    val imageBanners = MutableLiveData<List<SliderItem>>()

    init {
        fetchStorageList()
        loadStaticImages()
    }

    private fun fetchStorageList() {
        viewModelScope.launch(appDispatcher.io()) {
            val data = fetchData { repository.getStorageList() }
            storageList.postValue(data)
        }
    }

    private fun loadStaticImages() {
        val image1 = SliderItem("https://efishery.com/uploads/images/sliders/18941925b2410bd6178829a0c182d887.png")
        val image2 = SliderItem("https://efishery.com/uploads/images/sliders/1cfc363757300c69b815e501460584d6.png")
        val image3 = SliderItem("https://efishery.com/uploads/images/sliders/a10d9e215ea519e46f3318538daa6d79.png")
        imageBanners.postValue(listOf(image1, image2, image3))
    }

}