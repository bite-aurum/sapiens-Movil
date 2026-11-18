package com.example.sapiens.Modelo.docente

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataTareasDocente(
    @SerialName("tareas") val tareas: List<dataTarea>
    )
    @Serializable
    data class dataTarea(
        @SerialName("id_tarea") val idTarea: Int = 0,
        @SerialName("titulo") val titulo: String = "",
        @SerialName("prioridad") val prioridad: String = "",
        @SerialName("estado") val estado: String = "",
        @SerialName("fecha_limite") val fechaLimite: String = "",
        @SerialName("proyecto") val proyecto: String = ""
    )
