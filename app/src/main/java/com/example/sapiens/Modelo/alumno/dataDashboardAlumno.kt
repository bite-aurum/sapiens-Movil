package com.example.sapiens.Modelo.alumno

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDashboardAlumno(
    @SerialName("alumno") val alumno: dataAlumnoDashboard,
    @SerialName("proxima_entrega") val proximaEntrega: dataProximaEntrega? = null,
    @SerialName("proyectos") val proyectos: List<dataProyectoAlumno> = emptyList()
)

@Serializable
data class dataAlumnoDashboard(
    @SerialName("id_alumno") val idAlumno: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("apellido_paterno") val apellidoPaterno: String = "",
    @SerialName("apellido_materno") val apellidoMaterno: String = "",
    @SerialName("foto") val foto: String? = null
)

@Serializable
data class dataProximaEntrega(
    @SerialName("id_tarea") val idTarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("fecha_limite") val fechaLimite: String = "",
    @SerialName("proyecto") val proyecto: String = ""
)

@Serializable
data class dataProyectoAlumno(
    @SerialName("id_proyecto") val idProyecto: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("avance") val avance: Int = 0
)