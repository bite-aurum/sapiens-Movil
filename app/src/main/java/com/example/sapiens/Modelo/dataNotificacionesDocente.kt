package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataNotificacionesDocente(
    @SerialName("notificaciones") val notificaciones: List<dataNotificacionesEnviadas>,
)

@Serializable
data class dataNotificacionesEnviadas(
    @SerialName("id_notificacion") val idNotificacion: Int = 0,
    @SerialName("tipo") val tipo: String = "",
    @SerialName("mensaje") val mensaje: String  = "",
    @SerialName("fecha") val fecha: String  = "",
    @SerialName("alumno") val alumno: String  = ""
)

@Serializable
data class dataAlumnosProyecto(
    @SerialName("alumnos") val alumnos: List<dataAlumnos>
)
@Serializable
data class dataAlumnos(
    @SerialName("id_alumno") val idAlumno: Int = 0,
    @SerialName("nombre") val nombre: String  = ""
)

@Serializable
data class dataEnviarNotificacionResponse(
    @SerialName("id_notificacion") val idNotificacion: Int = 0
)






