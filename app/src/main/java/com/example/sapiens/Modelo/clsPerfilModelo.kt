package com.example.sapiens.Modelo

import com.example.sapiens.Network.RetrofitCliente
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

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
    suspend fun subirFoto(rol: String, id: Int, archivo: File, tipoMime: String): dataSubirFoto {
        val texto = "text/plain".toMediaType()
        val rolParte = rol.toRequestBody(texto)
        val idParte = id.toString().toRequestBody(texto)

        val cuerpo = archivo.asRequestBody(tipoMime.toMediaType())
        val fotoParte = MultipartBody.Part.createFormData("foto", archivo.name, cuerpo)

        return RetrofitCliente.apiService.subirFoto(rolParte, idParte, fotoParte)
    }

}