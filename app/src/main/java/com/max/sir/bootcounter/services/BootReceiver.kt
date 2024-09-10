package com.max.sir.bootcounter.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.max.sir.bootcounter.data.entity.BootEvent
import com.max.sir.bootcounter.utils.NotificationScheduler

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val bootTime = System.currentTimeMillis()
            val bootEvent = BootEvent(bootTime = bootTime)

            // Save event in DB using WorkManager or repository
            val workManager = WorkManager.getInstance(context)
            val saveBootWorkRequest = OneTimeWorkRequestBuilder<SaveBootEventWorker>()
                .setInputData(workDataOf("bootTime" to bootTime))
                .build()
            workManager.enqueue(saveBootWorkRequest)

            // Schedule notification to show every 15 minutes
            NotificationScheduler.scheduleNotifications(context)
        }
    }
}
