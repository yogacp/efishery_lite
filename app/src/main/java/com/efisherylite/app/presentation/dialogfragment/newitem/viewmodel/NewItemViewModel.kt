package com.efisherylite.app.presentation.dialogfragment.newitem.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.efisherylite.app.data.dao.storagelist.StorageListEntity
import com.efisherylite.app.data.model.storagelist.StorageList
import com.efisherylite.app.domain.base.viewmodel.BaseViewModel
import com.efisherylite.app.domain.room.database.DatabaseHelper
import com.efisherylite.app.external.extensions.getCurrentDateTime
import com.efisherylite.app.external.extensions.hashString
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.lang.Exception

/**
 * Created by Yoga C. Pranata on 15/11/2020.
 * Android Engineer
 */
class NewItemViewModel(application: Application) : BaseViewModel(application) {
    private val database: DatabaseHelper by inject()
    val dataSaved = MutableLiveData<String>()

    fun getOptionAreas() = database.getAllAreas()
    fun getOptionSizes() = database.getAllSizes()

    fun saveData(
        areaProvinsi: String?,
        areaKota: String?,
        komoditas: String?,
        harga: String?,
        ukuran: String?
    ) {
        viewModelScope.launch {
            try {
                val timeStamp = (System.currentTimeMillis() / 1000).toString()
                val storage = StorageList(
                    uuid = timeStamp.hashString(),
                    areaKota = areaKota,
                    areaProvinsi = areaProvinsi,
                    tglParsed = getCurrentDateTime(),
                    komoditas = komoditas,
                    price = harga,
                    size = ukuran,
                    timestamp = timeStamp
                )

                val saveData = repository.saveData(listOf(storage))
                if(saveData.isSuccessful) {
                    dataSaved.postValue("Data saved successfully!")
                } else {
                    dataSaved.postValue("Error saving data, please try again.")
                }
            } catch (error: Exception) {
                error.printStackTrace()
            }
        }
    }
}