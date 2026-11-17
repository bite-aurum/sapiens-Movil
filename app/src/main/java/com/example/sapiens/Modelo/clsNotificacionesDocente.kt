package com.example.sapiens.Modelo

class clsNotificacionesDocente {


        suspend fun obtenerNotificaciones(idDocente: Int): dataNotificacionesDocente {
            return RetrofitCliente.apiService.obtenerNotificacionesDocente(idDocente)
        }

        suspend fun obtenerAlumnos(idProyecto: Int): dataAlumnosProyecto {
            return RetrofitCliente.apiService.obtenerAlumnosProyecto(idProyecto)
        }

        suspend fun enviarNotificacion(
            idDocente: Int,
            idAlumno: Int,
            tipo: String,
            mensaje: String,
            fecha: String
        ): dataEnviarNotificacionResponse {
            return RetrofitCliente.apiService.enviarNotificacion(idDocente, idAlumno, tipo, mensaje, fecha)
        }
    }
