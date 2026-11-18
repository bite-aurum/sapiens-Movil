package com.example.sapiens.Modelo.docente

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleTareaDocente(
    @SerialName("tarea") val tarea: dataTareaDetalle = dataTareaDetalle(),
    @SerialName("subtareas") val subtareas: List<dataSubtareaDetalle> = emptyList(),
    @SerialName("error") val error: String = ""
)

@Serializable
data class dataTareaDetalle(
    @SerialName("id_tarea") val idTarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("descripcion") val descripcion: String? = "",
    @SerialName("estado") val estado: String = "",
    @SerialName("prioridad") val prioridad: String = "",
    @SerialName("fecha_limite") val fechaLimite: String? = "",
    @SerialName("proyecto") val proyecto: String = ""
)

@Serializable
data class dataSubtareaDetalle(
    @SerialName("id_subtarea") val idSubtarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("descripcion") val descripcion: String? = "",
    @SerialName("estado") val estado: String = "",
    @SerialName("responsable") val responsable: String? = "",
    @SerialName("archivos") val archivos: List<dataArchivoEvidencia> = emptyList()
)

@Serializable
data class dataArchivoEvidencia(
    @SerialName("nombre_archivo") val nombreArchivo: String = "",
    @SerialName("url") val url: String = "",
    @SerialName("tipo_mime") val tipoMime: String? = "",
    @SerialName("fecha_subida") val fechaSubida: String? = ""
)