package com.example.sapiens.Modelo.alumno

import com.example.sapiens.Modelo.RetrofitCliente

class clsProyectoAlumno {
    suspend fun obtenerMisProyectos(idAlumno: Int): List<dataProyectosAlumno> {
        return RetrofitCliente.apiService.obtenerMisProyectosAlumno(idAlumno)
    }
}