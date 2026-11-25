package com.example.sapiens.Modelo

import com.example.sapiens.Network.RetrofitCliente

class clsAjustesModelo {

    suspend fun obtenerLogoApp(): String {
        val respuesta = RetrofitCliente.apiService.obtenerAjustes()
        return respuesta.logo
    }
}