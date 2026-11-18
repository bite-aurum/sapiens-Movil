package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataActualizarPerfil(
    @SerialName("mensaje") val mensaje: String = ""
)