package com.example.sapiens.Network

import com.example.sapiens.Modelo.LoginRequest
import com.example.sapiens.Modelo.LoginResponse

interface apiService {
    @POST("login.php")
    suspend fun iniciarSesion(@Body request: LoginRequest): LoginResponse
}