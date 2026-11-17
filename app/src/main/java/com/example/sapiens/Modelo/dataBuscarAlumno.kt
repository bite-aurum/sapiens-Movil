package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataBuscarAlumno(
    @SerialName("alumno") val alumno: dataAlumno
)
@Serializable
data class dataAlumno(
    @SerialName("id_alumno") val idAlumno: Int=0,
    @SerialName("nombre") val nombre: String="",
    @SerialName("apellido_paterno") val apellidoPaterno: String="",
    @SerialName("apellido_materno") val apellidoMaterno: String="",
    @SerialName("correo") val correo: String=""
)
