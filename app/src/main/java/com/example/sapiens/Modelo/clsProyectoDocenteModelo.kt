package com.example.sapiens.Modelo

class clsProyectoDocenteModelo {
    suspend fun obtenerProyectosDocente(idDocente: Int): List<dataProyectosDocente> {
        return RetrofitCliente.apiService.obtenerProyectosDocente(idDocente)
    }
}