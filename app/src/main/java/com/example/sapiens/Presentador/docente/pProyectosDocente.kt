package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.docente.clsProyectoDocenteModelo
import com.example.sapiens.Modelo.docente.dataProyectosDocente
import com.example.sapiens.Vista.docente.proyectosDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pProyectosDocente(val vista: proyectosDocente) {

    private val modelo = clsProyectoDocenteModelo()

    fun iniciar(idDocente: Int) {
        // Se usa un Scope simple para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val proyectos = modelo.obtenerProyectosDocente(idDocente)
                vista.mostrarProyectos(proyectos)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarTareas(proyecto: dataProyectosDocente) {
        vista.abrirTareas(proyecto.idProyecto)
    }
}
