package com.example.bankcardinfo.domain.impl

import com.example.bankcardinfo.domain.api.HistoryInteractor
import com.example.bankcardinfo.domain.api.HistoryRepository
import com.example.bankcardinfo.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryInteractorImpl(private val repository: HistoryRepository):HistoryInteractor {
    override suspend fun getHistory(): Flow<List<CardInfo>> {
        return repository.getHistory().map { it.reversed() }
    }

    override suspend fun addInfoToHistory(cardInfo: CardInfo) {
        repository.addInfoToHistory(cardInfo)
    }
}