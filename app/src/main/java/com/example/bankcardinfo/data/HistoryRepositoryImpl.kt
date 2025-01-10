package com.example.bankcardinfo.data

import com.example.bankcardinfo.db.AppDatabase
import com.example.bankcardinfo.domain.api.HistoryRepository
import com.example.bankcardinfo.domain.model.CardInfo
import com.example.bankcardinfo.utility.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val cardInfoDbConverter: CardInfoDbConverter,
) : HistoryRepository {
    override fun getHistory(): Flow<List<CardInfo>> = flow {
        val history = appDatabase.historyDao().getCardsInfo()
        emit(history.map { cardInfoDbConverter.map(it)})
    }

    override suspend fun addInfoToHistory(cardInfo: CardInfo) {
        appDatabase.historyDao().inserdCardInfo(cardInfoDbConverter.map(cardInfo))
    }
}