package com.example.sapiens.Modelo.alumno
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataTareaAlumno(
    @SerialName("id_tarea") val idTarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("subtitulo") val subtitulo: String = "",
    @SerialName("prioridad") val prioridad: String = "",
    @SerialName("estado") val estado: String = ""
)
