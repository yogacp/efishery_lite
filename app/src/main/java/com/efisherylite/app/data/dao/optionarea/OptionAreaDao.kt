package com.efisherylite.app.data.dao.optionarea

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Yoga C. Pranata on 13/11/2020.
 * Android Engineer
 */
@Dao
interface OptionAreaDao {
    @Query("SELECT * FROM option_area")
    fun getAllData(): LiveData<List<OptionAreaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(optionAreas: List<OptionAreaEntity>)

    @Delete
    suspend fun delete(optionArea: OptionAreaEntity)

    @Query("DELETE FROM option_area")
    suspend fun deleteAllData()
}