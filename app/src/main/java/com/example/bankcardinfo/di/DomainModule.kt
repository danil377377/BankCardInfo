package com.example.bankcardinfo.di

import com.example.bankcardinfo.domain.api.CardInfoInteractor
import com.example.bankcardinfo.domain.api.HistoryInteractor
import com.example.bankcardinfo.domain.impl.CardInfoInteractorImpl
import com.example.bankcardinfo.domain.impl.HistoryInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    factory<CardInfoInteractor> {
        CardInfoInteractorImpl(get())
    }
    factory<HistoryInteractor> {
        HistoryInteractorImpl(get())
    }
}