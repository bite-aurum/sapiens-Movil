package com.example.sapiens.Modelo.docente

import com.example.sapiens.Modelo.RetrofitCliente

class clsProyectoDocenteModelo {
    suspend fun obtenerProyectosDocente(idDocente: Int): List<dataProyectosDocente> {
        return RetrofitCliente.apiService.obtenerProyectosDocente(idDocente).proyectos
    }

    suspend fun obtenerProyectosSpinner(idDocente: Int): List<dataProyectosDocente> {
        return listOf(dataProyectosDocente(0, "Seleccione un proyecto")) + obtenerProyectosDocente(idDocente)
    }
}