package com.example.bankcardinfo.di

import com.example.bankcardinfo.domain.api.CardInfoInteractor
import com.example.bankcardinfo.domain.impl.CardInfoInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    factory<CardInfoInteractor> {
        CardInfoInteractorImpl(get())
    }
}