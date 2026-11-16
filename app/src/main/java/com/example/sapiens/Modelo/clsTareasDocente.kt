package com.example.sapiens.Modelo

class clsTareasDocente {
    suspend fun obtenerTareas(idDocente: Int): dataTareasDocente {
        return RetrofitCliente.apiService.obtenerTareasDocente(idDocente)
    }
}
