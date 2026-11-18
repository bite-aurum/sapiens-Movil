package com.example.sapiens.Modelo.docente

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataReportesDocente(
    @SerialName("reportes") val reportes: List<dataReporteDocente>
)

@Serializable
data class dataReporteDocente(
    @SerialName("id_reporte") val idReporte: Int = 0,
    @SerialName("tipo") val tipo: String = "",
    @SerialName("fecha") val fecha: String = "",
    @SerialName("contenido") val contenido: String = "",
    @SerialName("proyecto") val proyecto: String? = null
)


@Serializable
data class dataReporteGenerado(
    @SerialName("id_reporte") val idReporte: Int = 0,
    @SerialName("contenido") val contenido: String = ""
)


@Serializable
data class dataProyectosReporte(
    @SerialName("proyectos") val proyectos: List<dataProyectoReporte> = emptyList()
)

@Serializable
data class dataProyectoReporte(
    @SerialName("id_proyecto") val idProyecto: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("descripcion") val descripcion: String? = null,
    @SerialName("estado") val estado: String = "",
    @SerialName("total_tareas") val totalTareas: Int = 0,
    @SerialName("tareas_completadas") val tareasCompletadas: Int = 0,
    @SerialName("avance") val avance: Int = 0
)