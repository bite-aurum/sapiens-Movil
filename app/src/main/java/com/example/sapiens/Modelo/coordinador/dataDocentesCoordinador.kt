package com.example.sapiens.Modelo.coordinador

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDocentesCoordinador(
    @SerialName("coordinador") val coordinador: dataCoordinadorClave,
    @SerialName("resumen") val resumen: dataResumenDocentes,
    @SerialName("docente") val docente: List<dataDocenteLista>
)

@Serializable
data class dataCoordinadorClave(
    @SerialName("id_coordinador") val idCoordinador: Int = 0,
    @SerialName("codigo_vinculo") val codigoVinculo: String = ""
)

@Serializable
data class dataResumenDocentes(
    @SerialName("docentes_registrados") val docentesRegistrados: Int = 0,
    @SerialName("docentes_activos") val docentesActivos: Int = 0,
    @SerialName("proyectos_asignados") val proyectosAsignados: Int = 0
)

@Serializable
data class dataDocenteLista(
    @SerialName("id_docente") val idDocente: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("apellido_paterno") val apellidoPaterno: String = "",
    @SerialName("apellido_materno") val apellidoMaterno: String = "",
    @SerialName("estado") val estado: String = ""
)
