package com.example.bankcardinfo.domain.api

import com.example.bankcardinfo.domain.model.CardInfo
import com.example.bankcardinfo.utility.Resource
import kotlinx.coroutines.flow.Flow

interface CardInfoRepository {
    fun getCardInfo(bin: String): Flow<Resource<CardInfo>>
}