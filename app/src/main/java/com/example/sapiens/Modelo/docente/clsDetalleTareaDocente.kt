package com.example.sapiens.Modelo.docente

import com.example.sapiens.Network.RetrofitCliente

class clsDetalleTareaDocenteModelo {
    suspend fun obtenerDetalleTarea(idTarea: Int): dataDetalleTareaDocente {
        return RetrofitCliente.apiService.obtenerDetalleTarea(idTarea)
    }
}