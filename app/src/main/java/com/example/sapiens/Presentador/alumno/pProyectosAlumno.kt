package com.example.sapiens.Presentador.alumno

import com.example.sapiens.Modelo.alumno.clsProyectoAlumno
import com.example.sapiens.Modelo.alumno.dataProyectosAlumno
import com.example.sapiens.Vista.alumno.proyectosAlumno
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pProyectosAlumno(val vista: proyectosAlumno) {

    private val modelo = clsProyectoAlumno()

    fun iniciar(idAlumno: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val proyectos = modelo.obtenerMisProyectos(idAlumno)
                vista.mostrarProyectos(proyectos)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarProyecto(proyecto: dataProyectosAlumno) {
        vista.abrirDetalleProyecto(proyecto.idProyecto)
    }
}