package com.efisherylite.app.domain.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application), KoinComponent {



}