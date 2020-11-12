package com.efisherylite.app.data.network.services

import com.efisherylite.app.data.constant.RESTConstant
import com.efisherylite.app.data.model.storagelist.StorageList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface EFisheryServices {
    @Headers(RESTConstant.HEADERS.CONTENT_JSON)
    @POST(RESTConstant.API_VERSION.V1 + RESTConstant.LIST)
    fun getStorageList(): Deferred<Response<List<StorageList>>>
}