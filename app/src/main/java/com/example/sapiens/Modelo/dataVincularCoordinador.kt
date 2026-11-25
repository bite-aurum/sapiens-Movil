package com.example.sapiens.Modelo.docente

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataVincularCoordinador(
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("coordinador") val coordinador: dataCoordinadorDocente? = null
)

@Serializable
data class dataObtenerCoordinador(
    @SerialName("coordinador") val coordinador: dataCoordinadorDocente? = null
)

@Serializable
data class dataCoordinadorDocente(
    @SerialName("id_coordinador") val idCoordinador: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("apellido_paterno") val apellidoPaterno: String = "",
    @SerialName("apellido_materno") val apellidoMaterno: String = ""
)
