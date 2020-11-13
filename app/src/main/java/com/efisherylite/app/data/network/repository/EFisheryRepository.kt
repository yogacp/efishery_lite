package com.efisherylite.app.data.network.repository

import com.efisherylite.app.data.model.optionarea.OptionArea
import com.efisherylite.app.data.model.optionsize.OptionSize
import com.efisherylite.app.data.model.storagelist.StorageList
import retrofit2.Response

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface EFisheryRepository {
    suspend fun getStorageList(): Response<List<StorageList>>
    suspend fun getOptionArea(): Response<List<OptionArea>>
    suspend fun getOptionSize(): Response<List<OptionSize>>
}