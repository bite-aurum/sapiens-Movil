package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataPerfil(
    @SerialName("perfil") val perfil: dataPerfilUsuario
)

@Serializable
data class dataPerfilUsuario(
    @SerialName("nombre") val nombre: String = "",
    @SerialName("apellido_paterno") val apellidoPaterno: String = "",
    @SerialName("apellido_materno") val apellidoMaterno: String = "",
    @SerialName("correo") val correo: String = "",
    @SerialName("foto") val foto: String? = null
)