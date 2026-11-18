package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.docente.clsTareasDocente
import com.example.sapiens.Modelo.docente.dataTareasDocente
import com.example.sapiens.Vista.docente.tareasDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pTareasDocente(private val vista: tareasDocente) {
    private val modelo = clsTareasDocente()

    fun cargarTareas(idDocente: Int) {
        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta: dataTareasDocente = modelo.obtenerTareasDocente(idDocente)
                vista.mostrarCargando(false)
                vista.mostrarTareas(respuesta.tareas)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al cargar tareas: " + e.localizedMessage)
            }
        }
    }

    fun onTareaSeleccionada(idTarea: Int) {
        vista.navegarADetalleTarea(idTarea)
    }
}