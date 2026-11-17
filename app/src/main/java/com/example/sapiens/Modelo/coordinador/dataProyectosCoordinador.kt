package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataProyectosCoordinador(
    @SerialName("resumen") val resumen: dataResumenCoordinador,
    @SerialName("proyecto") val proyectos: List<dataProyectoCoordinador>
)

@Serializable
data class dataResumenCoordinador(
    @SerialName("proyectos_totales") val proyectosTotales: Int = 0,
    @SerialName("docentes_asignados") val docentesAsignados: Int = 0,
    @SerialName("a_tiempo") val aTiempo: Int = 0,
    @SerialName("con_retraso") val conRetraso: Int = 0,
    @SerialName("avisos_pendientes") val avisosPendientes: Int = 0,
    @SerialName("tasa_entrega") val tasaEntrega: Int = 0
)

@Serializable
data class dataProyectoCoordinador(
    @SerialName("id_proyecto") val idProyecto: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("docente") val docente: String = "",
    @SerialName("estado") val estado: String = ""
)