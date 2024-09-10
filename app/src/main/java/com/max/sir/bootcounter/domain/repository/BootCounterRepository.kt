package com.max.sir.bootcounter.domain.repository

import com.max.sir.bootcounter.data.entity.AppConfig
import com.max.sir.bootcounter.data.entity.BootEvent

interface BootCounterRepository {
    suspend fun updateConfig(config: AppConfig)
    suspend fun getConfig(): AppConfig
    suspend fun getAllBootEvents(): List<BootEvent>
    suspend fun addBootEvent(event: BootEvent)
}