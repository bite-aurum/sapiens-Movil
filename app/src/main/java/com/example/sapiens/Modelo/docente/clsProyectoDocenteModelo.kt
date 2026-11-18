package com.example.sapiens.Modelo.docente

import com.example.sapiens.Modelo.RetrofitCliente

class clsProyectoDocenteModelo {
    suspend fun obtenerProyectosDocente(idDocente: Int): List<dataProyectosDocente> {
        return RetrofitCliente.apiService.obtenerProyectosDocente(idDocente)
    }

    suspend fun obtenerProyectosSpinner(idDocente: Int): List<dataProyectosDocente> {
        return listOf(dataProyectosDocente(0, "Seleccione un proyecto")) + RetrofitCliente.apiService.obtenerProyectosDocente(idDocente)
    }
}