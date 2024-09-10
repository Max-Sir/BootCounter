package com.max.sir.bootcounter.services

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.max.sir.bootcounter.data.BootCounterDatabase
import com.max.sir.bootcounter.utils.NotificationHelper
import java.text.SimpleDateFormat

class NotificationWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    @SuppressLint("SimpleDateFormat")
    override suspend fun doWork(): Result {
        val bootEventDao = BootCounterDatabase.getDatabase(applicationContext).bootEventDao()
        val bootEvents = bootEventDao.getAllBootEvents()

        val notificationText = when (bootEvents.size) {
            0 -> "No boots detected"
            1 -> "The boot was detected = ${SimpleDateFormat("dd/MM/yyyy").format(bootEvents.first().bootTime)}"
            else -> {
                val lastBoot = bootEvents.last().bootTime
                val secondLastBoot = bootEvents[bootEvents.size - 2].bootTime
                val delta = lastBoot - secondLastBoot
                "Last boots time delta = ${SimpleDateFormat("dd/MM/yyyy").format(delta)}"
            }
        }

        NotificationHelper.showNotification(applicationContext, notificationText)

        return Result.success()
    }
}