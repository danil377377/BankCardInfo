package com.example.bankcardinfo.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "card_info_table")
data class HistoryEntity(
    @PrimaryKey
    val id: Int,
    val number: String?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: String?,
    val bank: String?,
)