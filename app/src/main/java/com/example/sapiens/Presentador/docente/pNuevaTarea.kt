package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.docente.SubtareaTemporal
import com.example.sapiens.Modelo.docente.clsNuevaTareaModelo
import com.example.sapiens.Modelo.docente.clsProyectoDocenteModelo
import com.example.sapiens.Vista.docente.nuevaTareaDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class pNuevaTarea(private val vista: nuevaTareaDocente) {

    private val modeloNuevaTarea = clsNuevaTareaModelo()
    private val modeloProyectos = clsProyectoDocenteModelo()

    fun cargarProyectos(idDocente: Int) {
        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val listaProyectos = modeloProyectos.obtenerProyectosDocente(idDocente)
                vista.mostrarCargando(false)
                vista.llenarSpinnerProyectos(listaProyectos)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al cargar proyectos: " + e.localizedMessage)
            }
        }
    }

    fun guardarNuevaTarea(
        idProyecto: Int,
        titulo: String,
        descripcion: String,
        fechaLimiteApi: String,
        prioridad: String,
        subtareas: List<SubtareaTemporal>
    ) {
        if (idProyecto <= 0) {
            vista.mostrarError("Selecciona un proyecto")
            return
        }
        if (titulo.isEmpty()) {
            vista.mostrarError("Escribe el título de la tarea")
            return
        }
        if (fechaLimiteApi.isEmpty()) {
            vista.mostrarError("Selecciona la fecha límite")
            return
        }

        val subtitulos = mutableListOf<String>()
        val subdescripciones = mutableListOf<String>()
        for (sub in subtareas) {
            subtitulos.add(sub.titulo)
            subdescripciones.add(sub.descripcion)
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modeloNuevaTarea.guardarTarea(
                    idProyecto = idProyecto,
                    titulo = titulo,
                    descripcion = descripcion,
                    fechaLimite = fechaLimiteApi,
                    prioridad = prioridad,
                    subtitulos = subtitulos,
                    subdescripciones = subdescripciones
                )
                vista.mostrarCargando(false)
                vista.tareaGuardadaExito("Tarea guardada correctamente")
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al guardar tarea: " + e.localizedMessage)
            }
        }
    }
}