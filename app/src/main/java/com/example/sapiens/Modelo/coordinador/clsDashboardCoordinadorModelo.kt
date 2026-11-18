package com.example.sapiens.Modelo.coordinador

import com.example.sapiens.Modelo.RetrofitCliente

class clsDashboardCoordinadorModelo {
    suspend fun obtenerDashboardCoordinador(idCoordinador: Int): dataDashboardCoordinador {
        return RetrofitCliente.apiService.obtenerDashboardCoordinador(idCoordinador)
    }
}