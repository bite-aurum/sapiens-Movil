package com.example.sapiens.Presentador

import com.example.sapiens.Modelo.clsPerfilModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.sapiens.Vista.perfilUsuario
import java.io.File


class pPerfil (val vista: perfilUsuario) {
    private val modelo= clsPerfilModelo()

    fun iniciar(rol: String, id: Int){
        if (rol == "" || id <= 0){
            vista.mostrarError("No se encontro el usuario")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerPerfil(rol, id)
                val perfil = respuesta.perfil
                val apellidos = (perfil.apellidoPaterno + " " + perfil.apellidoMaterno).trim()
                vista.mostrarPerfil(perfil.nombre, apellidos, perfil.correo)
                vista.mostrarFoto(perfil.foto)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun guardarCambios(rol: String, id: Int, nombre: String, apellidos: String, correo: String) {
        if (nombre == "" || apellidos == "" || correo == "") {
            vista.mostrarError("Completa tu nombre, apellidos y correo")
            return
        }
        if (!correo.contains("@")) {
            vista.mostrarError("El correo no es válido")
            return
        }

        enviarCambios(rol, id, nombre, apellidos, correo, "", "")
    }

    fun cambiarContrasena(
        rol: String,
        id: Int,
        nombre: String,
        apellidos: String,
        correo: String,
        contrasenaActual: String,
        nuevaContrasena: String,
        confirmar: String
    ) {
        if (nombre == "" || apellidos == "" || correo == "") {
            vista.mostrarError("Completa tu nombre, apellidos y correo")
            return
        }
        if (!correo.contains("@")) {
            vista.mostrarError("El correo no es válido")
            return
        }
        if (contrasenaActual == "" || nuevaContrasena == "" || confirmar == "") {
            vista.mostrarError("Completa los tres campos de contraseña")
            return
        }
        if (nuevaContrasena.length < 8) {
            vista.mostrarError("La contraseña debe tener al menos 8 caracteres")
            return
        }
        if (nuevaContrasena != confirmar) {
            vista.mostrarError("Las contraseñas no coinciden")
            return
        }

        enviarCambios(rol, id, nombre, apellidos, correo, contrasenaActual, nuevaContrasena)
    }

    private fun enviarCambios(
        rol: String,
        id: Int,
        nombre: String,
        apellidos: String,
        correo: String,
        contrasenaActual: String,
        nuevaContrasena: String
    ) {
        val partes = apellidos.split(" ")
        val apellidoPaterno = partes[0]
        var apellidoMaterno = ""
        for (i in 1 until partes.size) {
            apellidoMaterno = apellidoMaterno + " " + partes[i]
        }
        apellidoMaterno = apellidoMaterno.trim()

        vista.activarBotones(false)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.actualizarPerfil(
                    rol, id, nombre, apellidoPaterno, apellidoMaterno,
                    correo, contrasenaActual, nuevaContrasena
                )
                vista.perfilActualizado(respuesta.mensaje, nombre)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
            vista.activarBotones(true)
        }
    }
    fun subirFoto(rol: String, id: Int, archivo: File, tipoMime: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.subirFoto(rol, id, archivo, tipoMime)
                vista.mostrarError(respuesta.mensaje)
                if (respuesta.ok) {
                    vista.mostrarFoto(respuesta.foto)
                }
            } catch (e: Exception) {
                vista.mostrarError("Error al subir: " + e.localizedMessage)
            }
        }
    }
}