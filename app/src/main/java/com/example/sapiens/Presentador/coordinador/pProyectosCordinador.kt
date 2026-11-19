package com.example.sapiens.Presentador.coordinador

import com.example.sapiens.Modelo.coordinador.clsProyectosCoordinador
import com.example.sapiens.Vista.coordinador.proyectosCoordinador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pProyectosCordinador(val vista: proyectosCoordinador) {

    private val modelo = clsProyectosCoordinador()

    fun iniciar(idCoordinador: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val datos = modelo.obtenerProyectos(idCoordinador)
                vista.mostrarResumen(datos.resumen)
                vista.mostrarProyectos(datos.proyectos)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }
}