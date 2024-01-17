package com.vikanshu.data.local.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.Calendar

object Migrations {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "ALTER TABLE CurrentWeather ADD COLUMN lastCacheUpdateTime INTEGER NOT NULL DEFAULT ${
                    Calendar.getInstance().apply { set(Calendar.YEAR, 1970) }.timeInMillis
                }"
            )
            database.execSQL("ALTER TABLE Forecast ADD COLUMN lastCacheUpdateTime INTEGER NOT NULL DEFAULT ${
                Calendar.getInstance().apply { set(Calendar.YEAR, 1970) }.timeInMillis
            }")
        }
    }

}