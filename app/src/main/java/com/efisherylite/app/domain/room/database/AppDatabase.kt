package com.efisherylite.app.domain.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.efisherylite.app.data.dao.optionarea.OptionAreaDao
import com.efisherylite.app.data.dao.optionarea.OptionAreaEntity
import com.efisherylite.app.data.dao.optionsize.OptionSizeDao
import com.efisherylite.app.data.dao.optionsize.OptionSizeEntity
import com.efisherylite.app.data.dao.storagelist.StorageListDao
import com.efisherylite.app.data.dao.storagelist.StorageListEntity
import com.efisherylite.app.domain.room.typeconverter.RoomTypeConverters

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
@Database(version = 1, entities = [
        StorageListEntity::class,
        OptionAreaEntity::class,
        OptionSizeEntity::class
    ]
)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storageDao(): StorageListDao
    abstract fun optionAreaDao(): OptionAreaDao
    abstract fun optionSizeDao(): OptionSizeDao
}