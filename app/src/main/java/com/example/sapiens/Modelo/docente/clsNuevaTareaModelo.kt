package com.example.sapiens.Modelo.docente

import com.example.sapiens.Modelo.RetrofitCliente

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
        return RetrofitCliente.apiService.guardarTarea(idProyecto, titulo, descripcion, fechaLimite, prioridad, subtitulos, subdescripciones)
    }
}