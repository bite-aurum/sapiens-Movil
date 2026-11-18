package com.example.sapiens.Network

import com.example.sapiens.Modelo.LoginRequest
import com.example.sapiens.Modelo.LoginResponse
import com.example.sapiens.Modelo.dataAlumnosProyecto
import com.example.sapiens.Modelo.dataProyectosCoordinador
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.sapiens.Modelo.dataDashboardDocente
import com.example.sapiens.Modelo.dataEnviarNotificacionResponse
import com.example.sapiens.Modelo.dataNotificacionesDocente
import com.example.sapiens.Modelo.dataProyectosDocente
import com.example.sapiens.Modelo.dataTareasDocente
import com.example.sapiens.Modelo.dataBuscarAlumno
import com.example.sapiens.Modelo.dataGuardarProyecto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import com.example.sapiens.Modelo.dataDetalleTareaDocente
import com.example.sapiens.Modelo.dataDocentesCoordinador
import com.example.sapiens.Modelo.dataDashboardCoordinador
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


    @GET("notificaciones_enviadas.php")
    suspend fun obtenerNotificacionesDocente(
        @Query("id_docente") idDocente: Int
    ): dataNotificacionesDocente

    @GET("alumnos_proyecto.php")
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
        @Field("fecha") fecha: String
    ): dataEnviarNotificacionResponse
    @GET("buscar_alumno.php")
    suspend fun buscarAlumno(@Query("correo") correo: String): dataBuscarAlumno


    @FormUrlEncoded
    @POST("guardar_proyecto.php")
    suspend fun guardarProyecto(
        @Field("id_docente") idDocente: Int,
        @Field("nombre") nombre: String,
        @Field("descripcion") descripcion: String,
        @Field("problematica") problematica: String,
        @Field("objetivos") objetivos: String,
        @Field("fecha_inicio") fechaInicio: String,
        @Field("fecha_fin") fechaFin: String,
        @Field("alumnos[]") alumnos: List<Int>
    ): dataGuardarProyecto

    @GET("detalle_tarea.php")
    suspend fun obtenerDetalleTarea(
        @Query("id_tarea") idTarea: Int
    ): dataDetalleTareaDocente

    @GET("proyectos_coordinador.php")
    suspend fun obtenerProyectosCoordinador(
        @Query("id_coordinador") idCoordinador: Int
    ): dataProyectosCoordinador

    @GET("docentes_Coordinador.php")
    suspend fun obtenerDocentesCoordinador(
        @Query("id_coordinador") idCoordinador: Int
    ): dataDocentesCoordinador
    @GET("dashboard_coordinador.php")
    suspend fun obtenerDashboardCoordinador(
        @Query("id_coordinador") idCoordinador: Int
    ): dataDashboardCoordinador
}
