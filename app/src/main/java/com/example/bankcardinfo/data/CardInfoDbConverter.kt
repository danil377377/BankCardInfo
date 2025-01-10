package com.example.bankcardinfo.data

import com.example.bankcardinfo.db.HistoryEntity
import com.example.bankcardinfo.domain.model.CardInfo
import com.google.gson.Gson
import com.example.bankcardinfo.utility.Bank
import com.example.bankcardinfo.utility.Country
import com.example.bankcardinfo.utility.Number

class CardInfoDbConverter {

    val gson = Gson()
    fun map(historyEntity: HistoryEntity): CardInfo{
        return CardInfo(
            historyEntity.id,
            historyEntity.number?.let { gson.fromJson(it, Number::class.java) },
            historyEntity.scheme,
            historyEntity.type,
            historyEntity.brand,
            historyEntity.prepaid,
            historyEntity.country?.let{ gson.fromJson(it, Country::class.java) },
            historyEntity.bank?.let{ gson.fromJson(it, Bank::class.java) }
        )
    }
    fun map(cardInfo: CardInfo): HistoryEntity{
        return HistoryEntity(
            cardInfo.bin!!,
            cardInfo.number?.let { gson.toJson(it) },
            cardInfo.scheme,
            cardInfo.type,
            cardInfo.brand,
            cardInfo.prepaid,
            cardInfo.country?.let { gson.toJson(it)},
            cardInfo.bank?.let {gson.toJson(it)}
        )
    }
}