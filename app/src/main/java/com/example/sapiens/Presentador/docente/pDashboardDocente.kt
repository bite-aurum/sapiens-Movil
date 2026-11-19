package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.docente.clsDashboardDocente
import com.example.sapiens.Vista.docente.dashboardDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pDashboardDocente(private val vista: dashboardDocente) {

    private val modelo = clsDashboardDocente()

    fun iniciar(idDocente: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerDashboard(idDocente)
                vista.mostrarBienvenida(respuesta.docente.nombre)
                vista.mostrarTareasRecientes(respuesta.tareasRecientes)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }


    fun seleccionarProyectos() {
        vista.abrirProyectos()
    }

    fun seleccionarTareas() {
        vista.abrirTareas()
    }

    fun seleccionarReportes() {
        vista.abrirReportes()
    }
}