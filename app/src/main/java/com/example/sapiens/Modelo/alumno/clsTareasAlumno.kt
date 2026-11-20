package com.example.sapiens.Modelo.alumno

import com.example.sapiens.Network.RetrofitCliente

class clsTareasAlumno {
    suspend fun obtenerTareas(idAlumno: Int): List<dataTareaAlumno> {
        return RetrofitCliente.apiService.obtenerTareasAlumno(idAlumno)

    }
}