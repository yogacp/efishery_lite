package com.efisherylite.app.presentation.homepage.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.efisherylite.app.data.dao.optionarea.OptionAreaEntity
import com.efisherylite.app.data.dao.optionsize.OptionSizeEntity
import com.efisherylite.app.data.dao.storagelist.StorageListEntity
import com.efisherylite.app.data.model.banner.SliderItem
import com.efisherylite.app.data.model.optionarea.OptionArea
import com.efisherylite.app.data.model.optionsize.OptionSize
import com.efisherylite.app.data.model.storagelist.StorageList
import com.efisherylite.app.domain.base.viewmodel.BaseViewModel
import com.efisherylite.app.domain.room.database.DatabaseHelper
import com.efisherylite.app.external.extensions.fetchData
import com.efisherylite.app.external.extensions.liveDataOf
import com.efisherylite.app.external.extensions.notNullOrEmpty
import com.efisherylite.app.external.state.ResultState
import kotlinx.coroutines.launch
import org.koin.core.inject

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class HomepageViewModel(application: Application) : BaseViewModel(application) {

    private val database: DatabaseHelper by inject()
    val storageList = liveDataOf<List<StorageList>>()
    val imageBanners = MutableLiveData<List<SliderItem>>()
    var searchCommodityQuery: String? = null

    init {
        loadStaticImages()
    }

    fun getStorageList() : LiveData<List<StorageListEntity>> {
        return if (searchCommodityQuery?.isNotEmpty() == true) {
            database.searchStorageByCommodity("%$searchCommodityQuery%")
        } else {
            database.getAllStorage()
        }
    }

    fun searchCommodity(query: String) {
        searchCommodityQuery = query
    }

    fun fetchAllData() {
        viewModelScope.launch(appDispatcher.io()) {
            try {
                val storages = fetchData { repository.getStorageList() }
                val areas = fetchData { repository.getOptionArea() }
                val sizes = fetchData { repository.getOptionSize() }

                saveStorageList(storages)
                saveOptionArea(areas)
                saveOptionSize(sizes)
            } catch (error: Exception) {
                error.printStackTrace()
                storageList.postValue(ResultState.Error(error))
            }
        }
    }

    private fun saveStorageList(storages: ResultState<List<StorageList>>) {
        viewModelScope.launch(appDispatcher.io()) {
            val dbStorages = arrayListOf<StorageListEntity>()

            storages.payload?.forEach { storage ->
                if (storage.uuid?.isNotEmpty() == true && storage.komoditas?.isNotEmpty() == true) {
                    val entity = StorageListEntity(
                        uuid = storage.uuid,
                        areaKota = storage.areaKota,
                        areaProvinsi = storage.areaProvinsi,
                        tglParsed = storage.tglParsed,
                        komoditas = storage.komoditas,
                        price = storage.price,
                        size = storage.size,
                        timestamp = storage.timestamp
                    )
                    dbStorages.add(entity)
                }
            }

            database.insertAllStorage(dbStorages)
            storageList.postValue(storages)
        }
    }

    private fun saveOptionArea(areas: ResultState<List<OptionArea>>) {
        viewModelScope.launch(appDispatcher.io()) {
            val dbArea = arrayListOf<OptionAreaEntity>()

            areas.payload?.forEach { area ->
                area.city.notNullOrEmpty {
                    val entity = OptionAreaEntity(
                        city = it,
                        province = area.province
                    )
                    dbArea.add(entity)
                }
            }

            database.insertAllAreas(dbArea)
        }
    }

    private fun saveOptionSize(sizes: ResultState<List<OptionSize>>) {
        viewModelScope.launch(appDispatcher.io()) {
            val dbSize = arrayListOf<OptionSizeEntity>()

            sizes.payload?.forEach { option ->
                option.size.notNullOrEmpty {
                    val entity = OptionSizeEntity(size = it)
                    dbSize.add(entity)
                }
            }

            database.insertAllSizes(dbSize)
        }
    }

    private fun loadStaticImages() {
        val image1 = SliderItem(
            "https://efishery.com/uploads/images/sliders/18941925b2410bd6178829a0c182d887.png",
            "eFisheryFeeder",
            "Tumbuh Bersama Para Pembudidaya"
        )
        val image2 = SliderItem(
            "https://efishery.com/uploads/images/sliders/1cfc363757300c69b815e501460584d6.png",
            "eFisheryFresh",
            "Pesan Ikan Segar Jadi Lebih Mudah di eFisheryFresh"
        )
        val image3 = SliderItem(
            "https://efishery.com/uploads/images/sliders/a10d9e215ea519e46f3318538daa6d79.png",
            "eFisheryFeed & eFisheryFund",
            "Terus Mendukung Kesejahteraan Pembudidaya Indonesia"
        )
        imageBanners.postValue(listOf(image1, image2, image3))
    }

}