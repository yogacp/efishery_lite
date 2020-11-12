package com.efisherylite.app.domain.room.database

import android.content.Context
import androidx.room.Room
import com.efisherylite.app.data.constant.DBConstant

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
object DatabaseBuilder {
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildRoomDB(context).also { instance = it }
        }
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DBConstant.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
}