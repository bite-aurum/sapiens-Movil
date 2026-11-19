package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.RetrofitCliente
import com.example.sapiens.Vista.docente.nuevaTareaDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pNuevaTarea(private val vista: nuevaTareaDocente) {

    fun cargarProyectos(idDocente: Int) {
        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = RetrofitCliente.apiService.obtenerProyectosDocente(idDocente)
                vista.mostrarCargando(false)
                vista.llenarSpinnerProyectos(respuesta.proyectos)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al cargar proyectos: " + e.localizedMessage)
            }
        }
    }

    fun guardarTarea(
        idProyecto: Int,
        titulo: String,
        descripcion: String,
        fechaLimiteApi: String,
        prioridad: String,
        subtitulos: List<String>,
        subdescripciones: List<String>
    ) {
        if (idProyecto <= 0) {
            vista.mostrarError("Selecciona un proyecto")
            return
        }
        if (titulo.isEmpty()) {
            vista.mostrarError("Ingresa el nombre de la tarea")
            return
        }
        if (fechaLimiteApi.isEmpty()) {
            vista.mostrarError("Selecciona la fecha límite")
            return
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = RetrofitCliente.apiService.guardarTarea(
                    idProyecto = idProyecto,
                    titulo = titulo,
                    descripcion = descripcion,
                    fechaLimite = fechaLimiteApi,
                    prioridad = prioridad.lowercase(),
                    subtitulos = subtitulos,
                    subdescripciones = subdescripciones
                )
                vista.mostrarCargando(false)
                vista.tareaGuardadaExito(respuesta.mensaje)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al guardar tarea: " + e.localizedMessage)
            }
        }
    }
}