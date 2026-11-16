package com.example.sapiens.Network

import com.example.sapiens.Modelo.LoginRequest
import com.example.sapiens.Modelo.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query

interface apiService {
    @POST("login.php")
    suspend fun iniciarSesion(@Body request: LoginRequest): LoginResponse
    @GET("dashboard_docente.php")
    suspend fun obtenerDashboardDocente(
        @Query("id_docente") idDocente: Int
    ): dataDashboardDocente
}