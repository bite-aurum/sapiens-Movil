package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.docente.clsReportesDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.sapiens.Vista.docente.reportesDocente

class pReportesDocente (val vista: reportesDocente) {
    private val modelo = clsReportesDocente()

    fun iniciar(idDocente: Int) {
        if (idDocente <=0){
            vista.mostrarError("No se encontro el docente")
            return
        }

        cargarProyectos(idDocente)
        cargarHistorial(idDocente)
    }

    private fun cargarProyectos(idDocente: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerProyecto(idDocente)
                vista.llenarSpinnerProyectos(respuesta.proyectos)
            }catch (e: Exception){
                vista.mostrarError("Error al cargar proyectos" + e.localizedMessage)
            }
        }
    }

    private fun cargarHistorial(idDocente: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerHistorial(idDocente)
                vista.mostrarHistorial(respuesta.reportes)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar historial: " + e.localizedMessage)
            }
        }
    }

    fun generarReporte(idDocente: Int, tipo: String, idProyecto: Int) {
        if (idDocente <= 0) {
            vista.mostrarError("No se encontró el docente")
            return
        }
        if (tipo.isEmpty()) {
            vista.mostrarError("Selecciona el tipo de reporte")
            return
        }

        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.generarReporte(idDocente, tipo, idProyecto)
                vista.mostrarCargando(false)
                vista.mostrarVistaPrevia(respuesta.contenido)
                cargarHistorial(idDocente)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al generar reporte: " + e.localizedMessage)
            }
        }
    }
}