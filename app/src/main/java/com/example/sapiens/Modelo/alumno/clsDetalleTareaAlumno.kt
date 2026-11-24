package com.example.sapiens.Modelo.alumno

import com.example.sapiens.Network.RetrofitCliente
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class clsDetalleTareaAlumno {

    suspend fun obtenerDetalle(idTarea: Int, idAlumno: Int): dataDetalleTareaAlumno {
        return RetrofitCliente.apiService.obtenerDetalleTareaAlumno(idTarea, idAlumno)
    }

    suspend fun subirArchivo(
        idAlumno: Int,
        idSubtarea: Int,
        archivo: File,
        tipoMime: String = "application/octet-stream"
    ): dataSubirArchivoResponse {
        val texto = "text/plain".toMediaType()
        val idAlumnoParte = idAlumno.toString().toRequestBody(texto)
        val idSubtareaParte = idSubtarea.toString().toRequestBody(texto)

        val cuerpoArchivo = archivo.asRequestBody(tipoMime.toMediaType())
        val archivoParte = MultipartBody.Part.createFormData("archivo", archivo.name, cuerpoArchivo)

        return RetrofitCliente.apiService.subirArchivoAlumno(idAlumnoParte, idSubtareaParte, archivoParte)
    }
}