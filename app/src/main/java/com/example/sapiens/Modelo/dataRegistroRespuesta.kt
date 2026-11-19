package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroRespuesta(
    @SerialName("ok") val ok: Boolean=false,
    @SerialName("mensaje") val mensaje: String="",
    @SerialName("id") val id: Int=0,
    @SerialName("rol") val rol: String=""
)
