package com.efisherylite.app.data.network.repository

import com.efisherylite.app.data.model.optionarea.OptionArea
import com.efisherylite.app.data.model.optionsize.OptionSize
import com.efisherylite.app.data.model.storagelist.StorageList
import com.efisherylite.app.data.network.services.EFisheryServices
import com.efisherylite.app.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class EFisheryDataStore(
    private val service: EFisheryServices,
    private val dispatcher: DispatcherProvider
) : EFisheryRepository {

    override suspend fun getStorageList(): Response<List<StorageList>> {
        return withContext(dispatcher.io()) {
            coroutineScope {
                async { service.getStorageList().await() }
            }
        }.await()
    }

    override suspend fun getOptionArea(): Response<List<OptionArea>> {
        return withContext(dispatcher.io()) {
            coroutineScope {
                async { service.getOptionArea().await() }
            }
        }.await()
    }

    override suspend fun getOptionSize(): Response<List<OptionSize>> {
        return withContext(dispatcher.io()) {
            coroutineScope {
                async { service.getOptionSize().await() }
            }
        }.await()
    }

}