package com.efisherylite.app.data.dao.optionsize

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yoga C. Pranata on 13/11/2020.
 * Android Engineer
 */
@Entity(tableName = "option_size")
data class OptionSizeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val size: String?
)