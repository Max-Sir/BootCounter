package com.max.sir.bootcounter.utils

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.max.sir.bootcounter.services.NotificationWorker
import java.util.concurrent.TimeUnit

object NotificationScheduler {
    fun scheduleNotifications(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
