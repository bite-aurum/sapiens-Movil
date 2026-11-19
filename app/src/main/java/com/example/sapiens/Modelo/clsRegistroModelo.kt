package com.example.sapiens.Modelo

class clsRegistroModelo {
    suspend fun registrarUsuario(
        rol: String,
        nombreCompleto: String,
        correo: String,
        contrasena: String
    ): dataRegistroRespuesta {
        return RetrofitCliente.apiService.registrarUsuario(rol, nombreCompleto, correo, contrasena)
    }
}