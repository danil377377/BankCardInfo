package com.example.bankcardinfo.data.network

interface NetworkClient {
    suspend fun doRequest(bin: String): Response
}