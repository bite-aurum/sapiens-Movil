package com.example.sapiens.Modelo

import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import com.example.sapiens.Network.apiService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitCliente {
    const val SERVIDOR = "https://saidmml.somos4a.org/sapiens_movil/"
    private val jsonConfig = Json{

        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }
    val apiService : apiService by lazy {
        val contentType="application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(SERVIDOR)
            .addConverterFactory(jsonConfig.asConverterFactory(contentType))
            .build()
            .create(com.example.sapiens.Network.apiService::class.java)
    }

}