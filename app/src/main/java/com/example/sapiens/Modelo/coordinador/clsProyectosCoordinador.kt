package com.example.sapiens.Modelo

class clsProyectosCoordinador {
    suspend fun obtenerProyectos(idCoordinador: Int): dataProyectosCoordinador {
        return RetrofitCliente.apiService.obtenerProyectosCoordinador(idCoordinador)
    }
}