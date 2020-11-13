package com.efisherylite.app.data.dao.optionarea

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yoga C. Pranata on 13/11/2020.
 * Android Engineer
 */
@Entity(tableName = "option_area")
data class OptionAreaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val city: String?,
    val province: String?
)