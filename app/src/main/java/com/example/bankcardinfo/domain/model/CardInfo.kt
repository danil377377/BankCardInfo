package com.example.bankcardinfo.domain.model


import com.example.bankcardinfo.utility.Bank
import com.example.bankcardinfo.utility.Country
import com.example.bankcardinfo.utility.Number

data class CardInfo(
    val number: Number?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?,
)

