package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataGuardarProyecto(
    @SerialName("mensaje") val mensaje: String="",
    @SerialName("id_proyecto") val idProyecto: Int=0
)
