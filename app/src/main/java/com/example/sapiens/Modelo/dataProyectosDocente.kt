package com.example.sapiens.Modelo
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class dataProyectosDocente(
    @SerialName("id_proyecto") val idProyecto: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("descripcion") val descripcion: String = "",
    @SerialName("estado") val estado: String = "",
    @SerialName("avance") val avance: Int = 0
)
