package com.example.sapiens.Modelo

import com.example.sapiens.Network.RetrofitCliente

class clsLoginModelo {
    suspend fun iniciarSesion(solicitud: LoginRequest): LoginResponse {
        return RetrofitCliente.apiService.iniciarSesion(solicitud)
    }
}