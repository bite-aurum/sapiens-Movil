package com.example.sapiens.Modelo

class clsDocentesCoordinador {
    suspend fun obtenerDocentesCoordinador (idCoordinador: Int) : dataDocentesCoordinador {
        return RetrofitCliente.apiService.obtenerDocentesCoordinador(idCoordinador)
    }
}