package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.docente.clsNotificacionesDocente
import com.example.sapiens.Modelo.docente.clsProyectoDocenteModelo
import com.example.sapiens.Vista.docente.notificacionesDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pNotificacionesDocente(private val vista: notificacionesDocente) {

    private val modeloNotificaciones = clsNotificacionesDocente()
    private val modeloProyectos = clsProyectoDocenteModelo()

    fun cargarDatosIniciales(idDocente: Int) {
        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val resNotificaciones = modeloNotificaciones.obtenerNotificaciones(idDocente)
                val listaProyectos = modeloProyectos.obtenerProyectosDocente(idDocente)

                vista.mostrarCargando(false)
                vista.mostrarNotificaciones(resNotificaciones.notificaciones)
                vista.llenarSpinnerProyectos(listaProyectos)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al cargar datos: " + e.localizedMessage)
            }
        }
    }

    fun cargarAlumnosDeProyecto(idProyecto: Int) {
        if (idProyecto <= 0) {
            vista.llenarSpinnerAlumnos(emptyList())
            return
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val resAlumnos = modeloNotificaciones.obtenerAlumnosProyecto(idProyecto)
                vista.mostrarCargando(false)
                vista.llenarSpinnerAlumnos(resAlumnos.alumnos)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al cargar alumnos: " + e.localizedMessage)
            }
        }
    }

    fun enviarNotificacion(
        idDocente: Int,
        idAlumno: Int,
        tipo: String,
        mensaje: String,
        fechaApi: String
    ) {
        if (idAlumno <= 0) {
            vista.mostrarError("Selecciona un alumno destinatario")
            return
        }
        if (mensaje.isEmpty()) {
            vista.mostrarError("Escribe el mensaje de la notificación")
            return
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modeloNotificaciones.enviarNotificacion(
                    idDocente = idDocente,
                    idAlumno = idAlumno,
                    tipo = tipo,
                    mensaje = mensaje,
                    fecha = fechaApi
                )
                vista.mostrarCargando(false)
                if (respuesta.idNotificacion > 0) {
                    vista.notificacionEnviadaExito("Notificación enviada correctamente")
                    cargarDatosIniciales(idDocente)
                } else {
                    vista.mostrarError("No se pudo registrar la notificación")
                }
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al enviar notificación: " + e.localizedMessage)
            }
        }
    }
}