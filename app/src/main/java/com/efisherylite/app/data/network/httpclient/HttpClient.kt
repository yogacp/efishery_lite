package com.efisherylite.app.data.network.httpclient

import android.app.Application
import com.efisherylite.app.BuildConfig
import com.efisherylite.app.data.network.services.EFisheryServices
import com.efisherylite.app.external.extensions.debugMode
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
object HttpClient {

    fun instance(mainApp: Application): OkHttpClient {
        val httpCacheDir = File(mainApp.cacheDir, "httpCache")
        val cache = Cache(httpCacheDir, 10 * 1024 * 1024)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().apply {
            cache(cache)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)

            debugMode {
                addInterceptor(loggingInterceptor)
            }

            addInterceptor { chain ->
                try {
                    chain.proceed(chain.request())
                } catch (error: Exception) {
                    val offlineRequest = chain.request().newBuilder()
                        .header(
                            "Cache-Control", "public, only-if-cached," +
                                    "max-stale=" + 60 * 60 * 24
                        ).build()
                    chain.proceed(offlineRequest)
                }
            }
        }.build()
    }

    fun coroutinesRestClient(okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        val gson = GsonBuilder().setLenient().create()
        builder.client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
        return builder.build()
    }

    fun coroutinesServices(restAdapter: Retrofit): EFisheryServices {
        return restAdapter.create(EFisheryServices::class.java)
    }

}