package com.example.sapiens.Modelo.docente

import com.example.sapiens.Network.RetrofitCliente

data class SubtareaTemporal(
    val titulo: String,
    val descripcion: String
)

class clsNuevaTareaModelo {

    suspend fun guardarTarea(
        idProyecto: Int,
        titulo: String,
        descripcion: String,
        fechaLimite: String,
        prioridad: String,
        subtitulos: List<String>,
        subdescripciones: List<String>
    ): dataTareaRespuesta {
        return RetrofitCliente.apiService.guardarTarea(
            idProyecto = idProyecto,
            titulo = titulo,
            descripcion = descripcion,
            fechaLimite = fechaLimite,
            prioridad = prioridad,
            subtitulos = subtitulos,
            subdescripciones = subdescripciones
        )
    }
}