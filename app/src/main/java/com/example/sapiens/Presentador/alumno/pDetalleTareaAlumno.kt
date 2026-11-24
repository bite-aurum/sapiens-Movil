package com.example.sapiens.Presentador.alumno

import com.example.sapiens.Modelo.alumno.clsDetalleTareaAlumno
import com.example.sapiens.Modelo.alumno.dataSubtareaAlumno
import com.example.sapiens.Vista.alumno.detalleTareaAlumno
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class pDetalleTareaAlumno(val vista: detalleTareaAlumno) {

    private val modelo = clsDetalleTareaAlumno()
    private var listaSubtareas: List<dataSubtareaAlumno> = emptyList()

    fun iniciar(idTarea: Int, idAlumno: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val detalle = modelo.obtenerDetalle(idTarea, idAlumno)
                listaSubtareas = detalle.subtareas
                vista.mostrarDetalle(detalle)
                vista.mostrarSubtareas(listaSubtareas)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun subirArchivo(idTarea: Int, idAlumno: Int, posicion: Int, archivo: File?, tipoMime: String) {
        if (posicion <= 0) {
            vista.mostrarError("Selecciona una subtarea")
            return
        }
        if (archivo == null) {
            vista.mostrarError("Selecciona un archivo")
            return
        }

        val subtarea = listaSubtareas[posicion - 1]

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.subirArchivo(idAlumno, subtarea.idSubtarea, archivo, tipoMime)
                vista.mostrarError(respuesta.mensaje)
                iniciar(idTarea, idAlumno)
            } catch (e: Exception) {
                vista.mostrarError("Error al subir: " + e.localizedMessage)
            }
        }
    }
}