package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.RetrofitCliente
import com.example.sapiens.Modelo.docente.clsDetalleTareaDocenteModelo
import com.example.sapiens.Modelo.docente.dataArchivoEvidencia
import com.example.sapiens.Vista.docente.detalleTareaDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pDetalleTareaDocente(val vista: detalleTareaDocente) {

    private val modelo = clsDetalleTareaDocenteModelo()

    fun iniciar(idTarea: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val detalle = modelo.obtenerDetalleTarea(idTarea)
                vista.mostrarTarea(detalle.tarea)
                vista.mostrarSubtareas(detalle.subtareas)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarVer(archivo: dataArchivoEvidencia) {
        vista.abrirArchivo(RetrofitCliente.SERVIDOR + archivo.url)
    }

    fun seleccionarDescargar(archivo: dataArchivoEvidencia) {
        vista.descargarArchivo(RetrofitCliente.SERVIDOR + archivo.url, archivo.nombreArchivo)
    }
}