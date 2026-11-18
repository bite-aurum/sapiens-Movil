package com.example.sapiens.Modelo.docente

import com.example.sapiens.Modelo.RetrofitCliente

class clsDetalleTareaDocenteModelo {
    suspend fun obtenerDetalleTarea(idTarea: Int): dataDetalleTareaDocente {
        return RetrofitCliente.apiService.obtenerDetalleTarea(idTarea)
    }
}