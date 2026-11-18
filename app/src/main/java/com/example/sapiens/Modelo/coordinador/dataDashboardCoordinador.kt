package com.example.sapiens.Modelo.coordinador

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDashboardCoordinador(
    @SerialName("coordinador") val coordinador: dataCoordinadorInfo = dataCoordinadorInfo(),
    @SerialName("dashboard") val dashboard: dataDashboardConteos = dataDashboardConteos(),
    @SerialName("docente") val docentes: List<dataDocenteRendimiento> = emptyList(),
    @SerialName("error") val error: String = ""
)

@Serializable
data class dataCoordinadorInfo(
    @SerialName("id_coordinador") val idCoordinador: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("apellido_paterno") val apellidoPaterno: String = "",
    @SerialName("apellido_materno") val apellidoMaterno: String = "",
    @SerialName("correo") val correo: String = "",
    @SerialName("foto") val foto: String? = null,
    @SerialName("codigo_vinculo") val codigoVinculo: String = ""
)

@Serializable
data class dataDashboardConteos(
    @SerialName("total_docentes") val totalDocentes: Int = 0,
    @SerialName("total_proyectos") val totalProyectos: Int = 0,
    @SerialName("a_tiempo") val aTiempo: Int = 0,
    @SerialName("con_retraso") val conRetraso: Int = 0
)

@Serializable
data class dataDocenteRendimiento(
    @SerialName("id_docente") val idDocente: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("apellido_paterno") val apellidoPaterno: String = "",
    @SerialName("apellido_materno") val apellidoMaterno: String = "",
    @SerialName("rendimiento") val rendimiento: Int = 0
)