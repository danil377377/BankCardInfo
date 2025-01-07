package com.example.bankcardinfo.domain.api

import com.example.bankcardinfo.domain.model.CardInfo
import kotlinx.coroutines.flow.Flow

interface CardInfoInteractor {
    fun getCardInfo(bin: String): Flow<Pair<CardInfo?, String?>>
}