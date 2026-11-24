package com.example.sapiens.Modelo.docente

import com.example.sapiens.Network.RetrofitCliente

class clsNotificacionesDocente {

    suspend fun obtenerNotificaciones(idDocente: Int): dataNotificacionesDocente {
        return RetrofitCliente.apiService.obtenerNotificacionesDocente(idDocente)
    }

    suspend fun obtenerAlumnosProyecto(idProyecto: Int): dataAlumnosProyecto {
        return RetrofitCliente.apiService.obtenerAlumnosProyecto(idProyecto)
    }

    suspend fun enviarNotificacion(
        idDocente: Int,
        idAlumno: Int,
        tipo: String,
        mensaje: String,
        fecha: String
    ): dataEnviarNotificacionResponse {
        return RetrofitCliente.apiService.enviarNotificacion(
            idDocente = idDocente,
            idAlumno = idAlumno,
            tipo = tipo,
            mensaje = mensaje,
            fecha = fecha
        )
    }
}