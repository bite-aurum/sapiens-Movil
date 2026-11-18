package com.example.sapiens.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class dataTareaRespuesta(
    @SerialName("mensaje") val mensaje: String= "",
    @SerialName("id_tarea") val idTarea: Int = 0,
)
