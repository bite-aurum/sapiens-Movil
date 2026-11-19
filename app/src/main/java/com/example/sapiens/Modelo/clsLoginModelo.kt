package com.example.sapiens.Modelo

class clsLoginModelo {
    suspend fun iniciarSesion(solicitud: LoginRequest): LoginResponse {
        return RetrofitCliente.apiService.iniciarSesion(solicitud)
    }
}