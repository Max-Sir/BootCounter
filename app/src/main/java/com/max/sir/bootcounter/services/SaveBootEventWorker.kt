package com.max.sir.bootcounter.services

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.max.sir.bootcounter.data.BootCounterDatabase
import com.max.sir.bootcounter.data.entity.BootEvent

class SaveBootEventWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val bootTime = inputData.getLong("bootTime", System.currentTimeMillis())

        val bootEventDao = BootCounterDatabase.getDatabase(applicationContext).bootEventDao()
        bootEventDao.insertBootEvent(BootEvent(bootTime = bootTime))

        return Result.success()
    }
}
