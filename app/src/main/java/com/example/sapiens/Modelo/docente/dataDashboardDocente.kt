package com.example.sapiens.Modelo.docente

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDashboardDocente(
    @SerialName("docente") val docente: dataDocente,
    @SerialName("tareas_recientes") val tareasRecientes: List<dataTareaReciente>
)

@Serializable
data class dataDocente(
    @SerialName("id_docente") val idDocente: Int,
    @SerialName("nombre") val nombre: String,
    @SerialName("apellido_paterno") val apellidoPaterno: String,
    @SerialName("foto") val foto: String? = null
)

@Serializable
data class dataTareaReciente(
    @SerialName("titulo") val titulo: String,
    @SerialName("prioridad") val prioridad: String,
    @SerialName("fecha_limite") val fechaLimite: String
)
