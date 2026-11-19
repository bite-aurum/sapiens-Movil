package com.example.sapiens.Presentador.alumno

import com.example.sapiens.Modelo.alumno.clsDetalleProyectoModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.sapiens.Vista.alumno.detalleProyectoAlumno

class pDetalleProyectoAlumno (val vista: detalleProyectoAlumno) {

    private val modelo = clsDetalleProyectoModelo()

    fun iniciar(idProyecto: Int, idAlumno: Int){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerDetalle(idProyecto, idAlumno)
                vista.mostrarDatos(respuesta.nombre, respuesta.problematica, respuesta.objetivos)
                vista.mostrarEquipo(respuesta.equipo)
                vista.mostrarEntregas(respuesta.entregas)
                vista.mostrarTareas(respuesta.tareas)
            } catch (e: Exception){
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }
}