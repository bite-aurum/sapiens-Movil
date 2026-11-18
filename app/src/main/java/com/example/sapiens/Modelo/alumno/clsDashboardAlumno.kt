package com.example.sapiens.Modelo.alumno

import com.example.sapiens.Modelo.RetrofitCliente

class clsDashboardAlumno {
    suspend fun obtenerDashboard(idAlumno: Int): dataDashboardAlumno {
        return RetrofitCliente.apiService.obtenerDashboardAlumno(idAlumno)
    }
}