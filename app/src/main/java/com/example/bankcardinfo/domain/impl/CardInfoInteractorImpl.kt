package com.example.bankcardinfo.domain.impl

import com.example.bankcardinfo.domain.api.CardInfoInteractor
import com.example.bankcardinfo.domain.api.CardInfoRepository
import com.example.bankcardinfo.domain.model.CardInfo
import com.example.bankcardinfo.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CardInfoInteractorImpl(private val repository: CardInfoRepository): CardInfoInteractor {
    override fun getCardInfo(bin: String): Flow<Pair<CardInfo?, String?>> {
        return repository.getCardInfo(bin).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
}