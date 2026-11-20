package com.example.sapiens.Modelo.docente

import com.example.sapiens.Network.RetrofitCliente

class clsTareasDocente {
    suspend fun obtenerTareasDocente(idDocente: Int): dataTareasDocente {
        return RetrofitCliente.apiService.obtenerTareasDocente(idDocente)
    }
}
