package com.example.sapiens.Presentador.alumno

import com.example.sapiens.Modelo.alumno.clsTareasAlumno
import com.example.sapiens.Vista.alumno.misTareasAlumno
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pTareasAlumno(private val vista: misTareasAlumno) {

    private val modelo = clsTareasAlumno()

    fun iniciar(idAlumno: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val tareas = modelo.obtenerTareas(idAlumno)
                vista.mostrarTareas(tareas)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarInicio() {
        vista.abrirInicio()
    }

    fun seleccionarProyectos() {
        vista.abrirProyectos()
    }
}