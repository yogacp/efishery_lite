package com.efisherylite.app.data.network.repository

import com.efisherylite.app.data.model.storagelist.StorageList
import retrofit2.Response

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface EFisheryRepository {
    suspend fun getStorageList(): Response<List<StorageList>>
}