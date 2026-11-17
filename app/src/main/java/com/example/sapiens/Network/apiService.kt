package com.example.sapiens.Network

import com.example.sapiens.Modelo.LoginRequest
import com.example.sapiens.Modelo.LoginResponse
import com.example.sapiens.Modelo.dataAlumnosProyecto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.sapiens.Modelo.dataDashboardDocente
import com.example.sapiens.Modelo.dataEnviarNotificacionResponse
import com.example.sapiens.Modelo.dataNotificacionesDocente
import com.example.sapiens.Modelo.dataProyectosDocente
import com.example.sapiens.Modelo.dataTareasDocente
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

interface apiService {
    @POST("login.php")
    suspend fun iniciarSesion(@Body request: LoginRequest): LoginResponse
    @GET("dashboard_docente.php")
    suspend fun obtenerDashboardDocente(
        @Query("id_docente") idDocente: Int
    ): dataDashboardDocente

    @GET("proyectos_docente.php")
    suspend fun obtenerProyectosDocente(@Query("id_docente") idDocente: Int): List<dataProyectosDocente>

    @GET("tareas_docente.php")
    suspend fun obtenerTareasDocente(
        @Query("id_docente") idDocente: Int
    ): dataTareasDocente


    @GET("obtener_notificaciones_docente.php")
    suspend fun obtenerNotificacionesDocente(
        @Query("id_docente") idDocente: Int
    ): dataNotificacionesDocente

    @GET("obtener_alumnos_proyecto.php")
    suspend fun obtenerAlumnosProyecto(
        @Query("id_proyecto") idProyecto: Int
    ): dataAlumnosProyecto

    @FormUrlEncoded
    @POST("enviar_notificacion.php")
    suspend fun enviarNotificacion(
        @Field("id_docente") idDocente: Int,
        @Field("id_alumno") idAlumno: Int,
        @Field("tipo") tipo: String,
        @Field("mensaje") mensaje: String,
        @Field("fecha") fecha: String?
    ): dataEnviarNotificacionResponse
}
