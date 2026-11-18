package com.example.sapiens.Modelo.alumno

import com.example.sapiens.Modelo.RetrofitCliente

class clsReportesDocente {
    suspend fun obtenerProyecto(idDocente: Int): dataProyectosReporte{
        return RetrofitCliente.apiService.obtenerProyectosReporte(idDocente)
    }

    suspend fun generarReporte(idDocente: Int, tipo: String, idProyecto: Int = 0): dataReporteGenerado {
        return RetrofitCliente.apiService.generarReporte(idDocente, tipo, idProyecto)
    }


    suspend fun obtenerHistorial(idDocente: Int): dataReportesDocente {
        return RetrofitCliente.apiService.obtenerHistorialReportes(idDocente)
    }
}