package com.example.bankcardinfo.di

import androidx.room.Room
import com.example.bankcardinfo.data.CardInfoDbConverter
import com.example.bankcardinfo.data.HistoryRepositoryImpl
import com.example.bankcardinfo.data.network.BinlistApi
import com.example.bankcardinfo.data.network.CardInfoRepositoryImpl
import com.example.bankcardinfo.data.network.NetworkClient
import com.example.bankcardinfo.data.network.RetrofitNetworkClient
import com.example.bankcardinfo.db.AppDatabase
import com.example.bankcardinfo.domain.api.CardInfoRepository
import com.example.bankcardinfo.domain.api.HistoryRepository
import com.example.bankcardinfo.utility.log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module{
    single<BinlistApi> {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinlistApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(androidContext(),get())
    }
    single<CardInfoRepository> {

        CardInfoRepositoryImpl(get())
    }
    single<HistoryRepository>{
        log("history rep")
        HistoryRepositoryImpl(get(), get())
    }

    single {
        log("room")
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        log("converter")
        CardInfoDbConverter()
    }
}