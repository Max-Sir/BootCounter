package com.max.sir.bootcounter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.max.sir.bootcounter.data.entity.AppConfig
import com.max.sir.bootcounter.data.entity.BootEvent

@Dao
interface BootEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBootEvent(bootEvent: BootEvent)

    @Query("SELECT * FROM boot_events")
    suspend fun getAllBootEvents(): List<BootEvent>
}

@Dao
interface AppConfigDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAppConfig(config: AppConfig)

    @Query("SELECT * FROM app_config LIMIT 1")
    suspend fun getAppConfig(): AppConfig
}