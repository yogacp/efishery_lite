package com.efisherylite.app.data.network.services

import com.efisherylite.app.data.constant.RESTConstant
import com.efisherylite.app.data.model.optionarea.OptionArea
import com.efisherylite.app.data.model.optionsize.OptionSize
import com.efisherylite.app.data.model.storagelist.SaveData
import com.efisherylite.app.data.model.storagelist.StorageList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
interface EFisheryServices {
    @Headers(RESTConstant.HEADERS.CONTENT_JSON)
    @GET(RESTConstant.API_VERSION.V1 + RESTConstant.LIST)
    fun getStorageList(): Deferred<Response<List<StorageList>>>

    @Headers(RESTConstant.HEADERS.CONTENT_JSON)
    @GET(RESTConstant.API_VERSION.V1 + RESTConstant.OPTION_AREA)
    fun getOptionArea(): Deferred<Response<List<OptionArea>>>

    @Headers(RESTConstant.HEADERS.CONTENT_JSON)
    @GET(RESTConstant.API_VERSION.V1 + RESTConstant.OPTION_SIZE)
    fun getOptionSize(): Deferred<Response<List<OptionSize>>>

    @Headers(RESTConstant.HEADERS.CONTENT_JSON)
    @POST(RESTConstant.API_VERSION.V1 + RESTConstant.LIST)
    fun saveData(@Body post: Any?): Deferred<Response<SaveData>>
}