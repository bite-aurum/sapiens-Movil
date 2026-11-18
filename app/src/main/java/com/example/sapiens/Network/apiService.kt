package com.example.sapiens.Network

import com.example.sapiens.Modelo.LoginRequest
import com.example.sapiens.Modelo.LoginResponse
import com.example.sapiens.Modelo.alumno.dataDashboardAlumno
import com.example.sapiens.Modelo.alumno.dataDetalleTareaAlumno
import com.example.sapiens.Modelo.alumno.dataProyectosAlumno
import com.example.sapiens.Modelo.alumno.dataSubirArchivoResponse
import com.example.sapiens.Modelo.alumno.dataTareaAlumno
import com.example.sapiens.Modelo.docente.dataAlumnosProyecto
import com.example.sapiens.Modelo.coordinador.dataProyectosCoordinador
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.sapiens.Modelo.docente.dataDashboardDocente
import com.example.sapiens.Modelo.docente.dataEnviarNotificacionResponse
import com.example.sapiens.Modelo.docente.dataNotificacionesDocente
import com.example.sapiens.Modelo.docente.dataProyectosDocente
import com.example.sapiens.Modelo.docente.dataTareasDocente
import com.example.sapiens.Modelo.docente.dataBuscarAlumno
import com.example.sapiens.Modelo.coordinador.dataDashboardCoordinador
import com.example.sapiens.Modelo.docente.dataGuardarProyecto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import com.example.sapiens.Modelo.docente.dataDetalleTareaDocente
import com.example.sapiens.Modelo.coordinador.dataDocentesCoordinador
import com.example.sapiens.Modelo.docente.dataTareaRespuesta
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.Part
import com.example.sapiens.Modelo.alumno.dataDetalleProyecto
import com.example.sapiens.Modelo.alumno.dataNotificacionesAlumno
import com.example.sapiens.Modelo.docente.dataReporteGenerado
import com.example.sapiens.Modelo.docente.dataReportesDocente
import com.example.sapiens.Modelo.docente.dataProyectosReporte
import com.example.sapiens.Modelo.dataActualizarPerfil
import com.example.sapiens.Modelo.dataPerfil
import com.example.sapiens.Modelo.docente.dataProyectoRespuesta

interface apiService {
    @POST("login.php")
    suspend fun iniciarSesion(@Body request: LoginRequest): LoginResponse
    @GET("dashboard_docente.php")
    suspend fun obtenerDashboardDocente(
        @Query("id_docente") idDocente: Int
    ): dataDashboardDocente
    

    @GET("tareas_docente.php")
    suspend fun obtenerTareasDocente(
        @Query("id_docente") idDocente: Int
    ): dataTareasDocente

    @FormUrlEncoded
    @POST("guardar_tarea.php")
    suspend fun guardarTarea(
        @Field("id_proyecto") idProyecto: Int,
        @Field("titulo") titulo: String,
        @Field("descripcion") descripcion: String,
        @Field("fecha_limite") fechaLimite: String,
        @Field("prioridad") prioridad: String,
        @Field("subtitulos[]") subtitulos: List<String>,
        @Field("subdescripciones[]") subdescripciones: List<String>
    ): dataTareaRespuesta

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
    @GET("dashboard_coordinador.php")
    suspend fun obtenerDashboardCoordinador(
        @Query("id_coordinador") idCoordinador: Int
    ): dataDashboardCoordinador

    @GET("proyectos_coordinador.php")
    suspend fun obtenerProyectosCoordinador(
        @Query("id_coordinador") idCoordinador: Int
    ): dataProyectosCoordinador
    @GET("docentes_coordinador.php")
    suspend fun obtenerDocentesCoordinador(
        @Query("id_coordinador") idCoordinador: Int
    ): dataDocentesCoordinador
    @GET("obtener_dashboard_alumno.php")
    suspend fun obtenerDashboardAlumno(
        @Query("id_alumno") idAlumno: Int
    ): dataDashboardAlumno

    @GET("obtener_mis_proyectosAlumno.php")
    suspend fun obtenerMisProyectosAlumno(
        @Query("id_alumno") idAlumno: Int
    ): List<dataProyectosAlumno>
    @GET("obtener_mis_tareasAlumno.php")
    suspend fun obtenerTareasAlumno(
        @Query("id_alumno") idAlumno: Int
    ): List<dataTareaAlumno>

    @GET("detalle_tareaAlumno.php")
    suspend fun obtenerDetalleTareaAlumno(
        @Query("id_tarea") idTarea: Int,
        @Query("id_alumno") idAlumno: Int
    ): dataDetalleTareaAlumno

    @Multipart
    @POST("subir_archivoAlumno.php")
    suspend fun subirArchivoAlumno(
        @Part("id_alumno") idAlumno: RequestBody,
        @Part("id_subtarea") idSubtarea: RequestBody,
        @Part archivo: MultipartBody.Part
    ): dataSubirArchivoResponse

    @GET("detalle_proyectoAlumno.php")
    suspend fun obtenerDetalleProyecto(
        @Query("id_proyecto") idProyecto: Int,
        @Query("id_alumno") idAlumno: Int
    ): dataDetalleProyecto

    @GET("obtener_notificaciones_alumno.php")
    suspend fun obtenerNotificacionesAlumno(
        @Query("id_alumno") idAlumno: Int
    ): List<dataNotificacionesAlumno>

    @GET("docente/proyectos_docente.php")
    suspend fun obtenerProyectosDocente(@Query("id_docente") idDocente: Int): dataProyectoRespuesta

    @GET("docente/proyectos_docente.php")
    suspend fun obtenerProyectosReporte(@Query("id_docente") idDocente: Int): dataProyectosReporte

    @FormUrlEncoded
    @POST("generar_reporte.php")
    suspend fun generarReporte(
        @Field("id_docente") idDocente: Int,
        @Field("tipo") tipo: String,
        @Field("id_proyecto") idProyecto: Int
    ): dataReporteGenerado

    @GET("historial_reportes.php")
    suspend fun obtenerHistorialReportes(
        @Query("id_docente") idDocente: Int
    ): dataReportesDocente
    @GET("obtener_perfil.php")
    suspend fun obtenerPerfil(
        @Query("rol") rol: String,
        @Query("id") id: Int
    ): dataPerfil

    @FormUrlEncoded
    @POST("actualizar_perfil.php")
    suspend fun actualizarPerfil(
        @Field("rol") rol: String,
        @Field("id") id: Int,
        @Field("nombre") nombre: String,
        @Field("apellido_paterno") apellidoPaterno: String,
        @Field("apellido_materno") apellidoMaterno: String,
        @Field("correo") correo: String,
        @Field("contrasena_actual") contrasenaActual: String,
        @Field("nueva_contrasena") nuevaContrasena: String
    ): dataActualizarPerfil

}
