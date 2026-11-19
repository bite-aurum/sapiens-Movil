package com.example.sapiens.Presentador.docente

import com.example.sapiens.Modelo.docente.clsNuevoProyectoDocente
import com.example.sapiens.Modelo.docente.dataAlumno
import com.example.sapiens.Vista.docente.nuevoProyectoDocente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class pNuevoProyectoDocente(val vista: nuevoProyectoDocente) {
    private val modelo = clsNuevoProyectoDocente()

    private val listaAlumnos = mutableListOf<dataAlumno>()

    fun buscarAlumno(correo: String) {
        if (correo == "") {
            vista.mostrarError("Escribe el correo del alumno")
            return
        }
        if (!correo.contains("@")) {
            vista.mostrarError("El correo no es válido")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.buscarAlumno(correo)
                val alumno = respuesta.alumno

                for (a in listaAlumnos) {
                    if (a.idAlumno == alumno.idAlumno) {
                        vista.mostrarError("Ese alumno ya fue agregado")
                        return@launch
                    }
                }

                listaAlumnos.add(alumno)
                vista.mostrarAlumnos(listaAlumnos.toList())
                vista.limpiarCorreo()
            } catch (e: Exception) {
                vista.mostrarError(obtenerError(e))
            }
        }
    }

    fun quitarAlumno(posicion: Int) {
        if (posicion >= 0 && posicion < listaAlumnos.size) {
            listaAlumnos.removeAt(posicion)
            vista.mostrarAlumnos(listaAlumnos.toList())
        }
    }

    fun guardarProyecto(
        idDocente: Int,
        nombre: String,
        descripcion: String,
        problematica: String,
        objetivos: String,
        fechaInicio: String,
        fechaFin: String
    ) {
        if (idDocente <= 0) {
            vista.mostrarError("No se encontró el docente")
            return
        }
        if (nombre == "" || fechaInicio == "" || fechaFin == "") {
            vista.mostrarError("El nombre y las fechas son obligatorios")
            return
        }

        if (fechaFin < fechaInicio) {
            vista.mostrarError("La fecha fin no puede ser antes de la fecha de inicio")
            return
        }

        val idsAlumnos = mutableListOf<Int>()
        for (a in listaAlumnos) {
            idsAlumnos.add(a.idAlumno)
        }

        vista.activarBotones(false)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.guardarProyecto(
                    idDocente, nombre, descripcion, problematica,
                    objetivos, fechaInicio, fechaFin, idsAlumnos
                )
                vista.proyectoGuardado(respuesta.mensaje)
            } catch (e: Exception) {
                vista.mostrarError(obtenerError(e))
            }
            vista.activarBotones(true)
        }
    }

    private fun obtenerError(e: Exception): String {
        return "Error: " + e.localizedMessage
    }
}