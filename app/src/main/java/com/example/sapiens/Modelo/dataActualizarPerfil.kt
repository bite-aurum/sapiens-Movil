package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataActualizarPerfil(
    @SerialName("mensaje") val mensaje: String = ""
)
@Serializable
data class dataSubirFoto(
    @SerialName("ok") val ok: Boolean = false,
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("foto") val foto: String = ""
)