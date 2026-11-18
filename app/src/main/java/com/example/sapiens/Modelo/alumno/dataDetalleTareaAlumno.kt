package com.example.sapiens.Modelo.alumno

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleTareaAlumno(
    @SerialName("id_tarea") val idTarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("proyecto") val proyecto: String = "",
    @SerialName("subtitulo") val subtitulo: String = "",
    @SerialName("prioridad") val prioridad: String = "",
    @SerialName("estado") val estado: String = "",
    @SerialName("progreso") val progreso: Int = 0,
    @SerialName("subtareas") val subtareas: List<dataSubtareaAlumno> = emptyList()
)

@Serializable
data class dataSubtareaAlumno(
    @SerialName("id_subtarea") val idSubtarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("estado") val estado: String = ""
)

@Serializable
data class dataSubirArchivoResponse(
    @SerialName("ok") val ok: Boolean = false,
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("id") val id: Int = 0
)