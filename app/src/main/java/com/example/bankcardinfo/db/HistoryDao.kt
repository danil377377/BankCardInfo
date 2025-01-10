package com.example.bankcardinfo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserdCardInfo(cardInfo: HistoryEntity)
    @Query("SELECT * FROM card_info_table")
    suspend fun getCardsInfo(): List<HistoryEntity>
}