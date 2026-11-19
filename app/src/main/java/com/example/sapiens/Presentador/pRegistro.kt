package com.example.sapiens.Presentador

import com.example.sapiens.Modelo.clsRegistroModelo
import com.example.sapiens.Vista.registroUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class pRegistro(val vista: registroUsuario) {
    private val modelo = clsRegistroModelo()

    fun registrar(
        rol: String,
        nombreCompleto: String,
        correo: String,
        contrasena: String,
        confirmar: String
    ) {
        if (rol == "") {
            vista.mostrarError("Selecciona si eres alumno, docente o coordinador")
            return
        }
        if (nombreCompleto == "" || correo == "" || contrasena == "" || confirmar == "") {
            vista.mostrarError("Completa todos los campos")
            return
        }


        if (nombreCompleto.split(" ").size < 2) {
            vista.mostrarError("Escribe tu nombre y al menos un apellido")
            return
        }
        if (!correo.contains("@")) {
            vista.mostrarError("El correo no es válido")
            return
        }
        if (contrasena.length < 8) {
            vista.mostrarError("La contraseña debe tener al menos 8 caracteres")
            return
        }
        if (contrasena != confirmar) {
            vista.mostrarError("Las contraseñas no coinciden")
            return
        }

        vista.activarBoton(false)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.registrarUsuario(rol, nombreCompleto, correo, contrasena)

                if (respuesta.ok) {
                    vista.registroExitoso(respuesta.mensaje)
                } else {
                    vista.mostrarError(respuesta.mensaje)
                }
            } catch (e: Exception) {
                vista.mostrarError(obtenerError(e))
            }
            vista.activarBoton(true)
        }
    }

    private fun obtenerError(e: Exception): String {
        if (e is HttpException) {
            try {
                val texto = e.response()?.errorBody()?.string() ?: ""
                return JSONObject(texto).getString("error")
            } catch (ex: Exception) {
                return "Error del servidor (${e.code()})"
            }
        }
        return "Error de conexión: " + e.localizedMessage
    }
}