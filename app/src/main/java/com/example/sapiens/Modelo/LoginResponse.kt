package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName

data class LoginResponse(
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("rol") val rol: String = "",
    @SerialName("nombre") val nombre: String = ""
)
