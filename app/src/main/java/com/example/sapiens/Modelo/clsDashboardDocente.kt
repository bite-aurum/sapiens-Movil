package com.example.sapiens.Modelo

class clsDashboardDocente {
    suspend fun obtenerDashboard(idDocente: Int): dataDashboardDocente {
        return RetrofitCliente.apiService.obtenerDashboardDocente(idDocente)
    }
}