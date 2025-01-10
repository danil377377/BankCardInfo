package com.example.bankcardinfo.domain.api

import com.example.bankcardinfo.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getHistory(): Flow<List<CardInfo>>
    suspend fun addInfoToHistory(cardInfo: CardInfo)
}