package com.example.sapiens.Network

import com.example.sapiens.Modelo.LoginRequest
import com.example.sapiens.Modelo.LoginResponse
import com.example.sapiens.Modelo.dataAlumnosProyecto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.sapiens.Modelo.dataDashboardDocente
import com.example.sapiens.Modelo.dataProyectosDocente

interface apiService {
    @POST("login.php")
    suspend fun iniciarSesion(@Body request: LoginRequest): LoginResponse
    @GET("dashboard_docente.php")
    suspend fun obtenerDashboardDocente(
        @Query("id_docente") idDocente: Int
    ): dataDashboardDocente

    @GET("proyectos_docente.php")
    suspend fun obtenerProyectosDocente(@Query("id_docente") idDocente: Int): List<dataProyectosDocente>
}
