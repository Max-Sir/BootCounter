package com.max.sir.bootcounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.max.sir.bootcounter.data.entity.AppConfig
import com.max.sir.bootcounter.data.entity.BootEvent

@Database(entities = [BootEvent::class, AppConfig::class], version = 1)
abstract class BootCounterDatabase : RoomDatabase() {
    abstract fun bootEventDao(): BootEventDao
    abstract fun appConfigDao(): AppConfigDao

    companion object {
        @Volatile
        private var INSTANCE: BootCounterDatabase? = null

        fun getDatabase(context: Context): BootCounterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BootCounterDatabase::class.java,
                    "boot_counter_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}