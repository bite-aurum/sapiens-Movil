package com.example.sapiens.Modelo.docente

import com.example.sapiens.Modelo.RetrofitCliente

class clsDashboardDocente {
    suspend fun obtenerDashboard(idDocente: Int): dataDashboardDocente {
        return RetrofitCliente.apiService.obtenerDashboardDocente(idDocente)
    }
}