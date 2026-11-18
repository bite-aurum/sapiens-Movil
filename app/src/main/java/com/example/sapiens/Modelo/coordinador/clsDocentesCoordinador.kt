package com.example.sapiens.Modelo.coordinador

import com.example.sapiens.Modelo.RetrofitCliente

class clsDocentesCoordinador {
    suspend fun obtenerDocentesCoordinador (idCoordinador: Int) : dataDocentesCoordinador {
        return RetrofitCliente.apiService.obtenerDocentesCoordinador(idCoordinador)
    }
}