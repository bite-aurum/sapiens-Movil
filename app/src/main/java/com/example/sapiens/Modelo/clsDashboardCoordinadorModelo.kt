package com.example.sapiens.Modelo

class clsDashboardCoordinadorModelo {
    suspend fun obtenerDashboardCoordinador(idCoordinador: Int): dataDashboardCoordinador {
        return RetrofitCliente.apiService.obtenerDashboardCoordinador(idCoordinador)
    }
}