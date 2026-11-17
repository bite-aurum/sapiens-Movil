package com.example.sapiens.Modelo

class clsDetalleTareaDocenteModelo {
    suspend fun obtenerDetalleTarea(idTarea: Int): dataDetalleTareaDocente {
        return RetrofitCliente.apiService.obtenerDetalleTarea(idTarea)
    }
}