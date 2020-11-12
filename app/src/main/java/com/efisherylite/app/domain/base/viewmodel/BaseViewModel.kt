package com.efisherylite.app.domain.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.efisherylite.app.data.network.repository.EFisheryRepository
import com.efisherylite.app.domain.dispatcher.DispatcherProvider
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application), KoinComponent {

    val appDispatcher: DispatcherProvider by inject()
    val repository: EFisheryRepository by inject()

}