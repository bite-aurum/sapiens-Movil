package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName

data class LoginRequest(
    @SerialName("correo") val correo: String= "",
    @SerialName("contrasena") val contrasena: String= ""
)
