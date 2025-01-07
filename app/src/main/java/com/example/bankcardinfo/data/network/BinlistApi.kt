package com.example.bankcardinfo.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface BinlistApi {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinlistResponse
}