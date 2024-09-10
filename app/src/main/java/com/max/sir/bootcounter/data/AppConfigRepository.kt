package com.max.sir.bootcounter.data

import com.max.sir.bootcounter.data.entity.AppConfig

class AppConfigRepository(private val appConfigDao: AppConfigDao) {

    suspend fun updateConfig(config: AppConfig) {
        appConfigDao.updateAppConfig(config)
    }

    suspend fun getConfig(): AppConfig {
        return appConfigDao.getAppConfig()
    }
}