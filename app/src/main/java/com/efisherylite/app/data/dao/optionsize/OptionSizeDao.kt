package com.efisherylite.app.data.dao.optionsize

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Yoga C. Pranata on 13/11/2020.
 * Android Engineer
 */
@Dao
interface OptionSizeDao {
    @Query("SELECT * FROM option_size")
    fun getAllData(): LiveData<List<OptionSizeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(optionSizes: List<OptionSizeEntity>)

    @Delete
    suspend fun delete(optionSize: OptionSizeEntity)

    @Query("DELETE FROM option_size")
    suspend fun deleteAllData()
}