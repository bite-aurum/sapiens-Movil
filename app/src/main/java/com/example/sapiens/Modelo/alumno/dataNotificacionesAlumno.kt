package com.example.sapiens.Modelo.alumno

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class dataNotificacionesAlumno(
    @SerialName("id_notificacion") val idNotificacion: Int = 0,
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("tipo") val tipo: String = "",
    @SerialName("fecha") val fecha: String = "",
    @SerialName("leida") val leida: Int = 0,
    @SerialName("remitente") val remitente: String = ""
)