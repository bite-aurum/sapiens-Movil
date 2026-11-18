package com.example.sapiens.Modelo.docente

import com.example.sapiens.Modelo.RetrofitCliente

class clsNuevoProyectoDocente {
    suspend fun buscarAlumno(correo: String): dataBuscarAlumno {
        return RetrofitCliente.apiService.buscarAlumno(correo)
    }
    suspend fun guardarProyecto(
        idDocente: Int,
        nombre: String,
        descripcion: String,
        problematica: String,
        objetivos: String,
        fechaInicio: String,
        fechaFin: String,
        alumnos: List<Int>
    ): dataGuardarProyecto {
        return RetrofitCliente.apiService.guardarProyecto(idDocente, nombre, descripcion, problematica, objetivos, fechaInicio, fechaFin, alumnos)
    }

}