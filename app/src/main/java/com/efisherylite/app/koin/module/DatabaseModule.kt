package com.efisherylite.app.koin.module

import com.efisherylite.app.domain.room.database.DatabaseBuilder
import com.efisherylite.app.domain.room.database.DatabaseHelper
import com.efisherylite.app.domain.room.database.DatabaseHelperImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
val databaseModule = module {
    single<DatabaseHelper> { DatabaseHelperImpl(DatabaseBuilder.getInstance(androidApplication())) }
}