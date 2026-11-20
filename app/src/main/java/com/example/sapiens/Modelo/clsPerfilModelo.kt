package com.example.sapiens.Modelo

import com.example.sapiens.Network.RetrofitCliente

class clsPerfilModelo {

    suspend fun obtenerPerfil(rol: String, id: Int): dataPerfil {
        return RetrofitCliente.apiService.obtenerPerfil(rol, id)
    }

    suspend fun actualizarPerfil(
        rol: String,
        id: Int,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        correo: String,
        contrasenaActual: String,
        nuevaContrasena: String
    ): dataActualizarPerfil {
        return RetrofitCliente.apiService.actualizarPerfil(
            rol, id, nombre, apellidoPaterno, apellidoMaterno, correo, contrasenaActual, nuevaContrasena
        )
    }
}