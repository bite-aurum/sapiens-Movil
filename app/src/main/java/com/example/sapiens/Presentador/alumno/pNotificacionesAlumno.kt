package com.example.sapiens.Presentador.alumno

import com.example.sapiens.Modelo.alumno.clsNotificacionesAlumno
import com.example.sapiens.Vista.alumno.notificacionesAlumno
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pNotificacionesAlumno(private val vista: notificacionesAlumno) {

    private val modelo = clsNotificacionesAlumno()

    fun iniciar(idAlumno: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val notificaciones = modelo.obtenerNotificaciones(idAlumno)
                vista.mostrarNotificaciones(notificaciones)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }


    fun seleccionarInicio() {
        vista.abrirInicio()
    }

    fun seleccionarProyectos() {
        vista.abrirProyectos()
    }

    fun seleccionarTareas() {
        vista.abrirTareas()
    }
}