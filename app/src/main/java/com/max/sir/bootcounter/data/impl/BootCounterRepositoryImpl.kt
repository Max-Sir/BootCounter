package com.max.sir.bootcounter.data.impl

import com.max.sir.bootcounter.data.AppConfigDao
import com.max.sir.bootcounter.data.BootEventDao
import com.max.sir.bootcounter.data.entity.AppConfig
import com.max.sir.bootcounter.data.entity.BootEvent
import com.max.sir.bootcounter.domain.repository.BootCounterRepository

class BootCounterRepositoryImpl(
    private val appConfigDao: AppConfigDao,
    private val bootEventDao: BootEventDao
) : BootCounterRepository {
    override suspend fun updateConfig(config: AppConfig) {
        appConfigDao.updateAppConfig(config)
    }

    override suspend fun getConfig(): AppConfig {
        return appConfigDao.getAppConfig()
    }

    override suspend fun getAllBootEvents(): List<BootEvent> {
        return bootEventDao.getAllBootEvents()
    }

    override suspend fun addBootEvent(event: BootEvent) {
        bootEventDao.insertBootEvent(event)
    }
}