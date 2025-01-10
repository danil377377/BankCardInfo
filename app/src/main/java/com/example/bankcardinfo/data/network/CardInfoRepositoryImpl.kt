package com.example.bankcardinfo.data.network

import com.example.bankcardinfo.domain.api.CardInfoRepository
import com.example.bankcardinfo.domain.model.CardInfo
import com.example.bankcardinfo.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardInfoRepositoryImpl(private val networkClient: NetworkClient) : CardInfoRepository {
    override fun getCardInfo(bin: String): Flow<Resource<CardInfo>> = flow {
        val response = networkClient.doRequest(bin)
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            600 -> {
                emit(Resource.Error("Сервер не отвечает. Проверьте подключение к интернету"))
            }

            0 -> emit(Resource.Error("По данному BIN нет данных"))
            200 -> emit(Resource.Success(convertToCardInfo(response as BinlistResponse, bin)))
            else -> {
                emit(Resource.Error("Ошибка сервера.\nВероятно было совершено слишком много запросов.\nПожалуйста, повторите запрос позже"))
            }
        }
    }

    private fun convertToCardInfo(response: BinlistResponse, bin: String): CardInfo = CardInfo(
        bin = bin.toInt(),
        response.number,
        response.scheme,
        response.type,
        response.brand,
        response.prepaid,
        response.country,
        response.bank
    )
}