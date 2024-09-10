package com.max.sir.bootcounter.data

import com.max.sir.bootcounter.data.entity.BootEvent

class BootEventRepository(private val bootEventDao: BootEventDao) {

    suspend fun getAllBootEvents(): List<BootEvent> {
        return bootEventDao.getAllBootEvents()
    }

    suspend fun addBootEvent(event: BootEvent) {
        bootEventDao.insertBootEvent(event)
    }
}