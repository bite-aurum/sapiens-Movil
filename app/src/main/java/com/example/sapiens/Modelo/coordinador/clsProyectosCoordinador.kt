package com.example.sapiens.Modelo.coordinador

import com.example.sapiens.Network.RetrofitCliente

class clsProyectosCoordinador {
    suspend fun obtenerProyectos(idCoordinador: Int): dataProyectosCoordinador {
        return RetrofitCliente.apiService.obtenerProyectosCoordinador(idCoordinador)
    }
}