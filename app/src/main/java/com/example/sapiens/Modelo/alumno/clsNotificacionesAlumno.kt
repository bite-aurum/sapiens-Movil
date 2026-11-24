package com.example.sapiens.Modelo.alumno

import com.example.sapiens.Network.RetrofitCliente

class clsNotificacionesAlumno {

    suspend fun obtenerNotificaciones(idAlumno: Int): List<dataNotificacionesAlumno> {
        return RetrofitCliente.apiService.obtenerNotificacionesAlumno(idAlumno)
    }
}