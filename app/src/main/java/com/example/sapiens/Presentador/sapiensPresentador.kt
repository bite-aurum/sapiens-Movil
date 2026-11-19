package com.example.sapiens.Presentador

import com.example.sapiens.Modelo.LoginRequest
import com.example.sapiens.Modelo.clsLoginModelo
import com.example.sapiens.Vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class sapiensPresentador(private val vista: MainActivity) {

    private val modelo = clsLoginModelo()

    fun iniciarSesion(correo: String, contrasena: String) {
        if (correo.isEmpty()) {
            vista.mostrarError("Ingresa tu correo institucional")
            return
        }
        if (contrasena.isEmpty()) {
            vista.mostrarError("Ingresa tu contraseña")
            return
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val solicitud = LoginRequest(correo, contrasena)
                val respuesta = modelo.iniciarSesion(solicitud)
                vista.mostrarCargando(false)
                vista.procesarRespuestaLogin(respuesta)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error de conexión: " + e.localizedMessage)
            }
        }
    }
}