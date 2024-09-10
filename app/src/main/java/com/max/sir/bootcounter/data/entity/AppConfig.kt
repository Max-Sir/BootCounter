package com.max.sir.bootcounter.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_config")
data class AppConfig(
    @PrimaryKey val id: Int = 1,
    val dismissalsAllowed: Int = 5,
    val dismissalInterval: Int = 20  // in minutes
)