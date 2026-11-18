package com.example.sapiens.Modelo.alumno

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class dataProyectosAlumno(
    @SerialName("id_proyecto") val idProyecto: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("estado") val estado: String = "",
    @SerialName("docente") val docente: String = "",
    @SerialName("avance") val avance: Int = 0
)
