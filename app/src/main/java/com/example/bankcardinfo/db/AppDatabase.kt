package com.example.bankcardinfo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 3, entities = [HistoryEntity::class])
abstract class AppDatabase : RoomDatabase() {
abstract fun historyDao(): HistoryDao
}