package com.example.sapiens.Presentador.coordinador

import com.example.sapiens.Modelo.coordinador.clsDocentesCoordinador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.sapiens.Vista.coordinador.docentesCoordinador

class pDocentesCoordinador (val vista: docentesCoordinador) {
    private val modelo = clsDocentesCoordinador()

    fun iniciar(idCoordinador: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerDocentesCoordinador(idCoordinador)
                vista.mostrarCodigo(respuesta.coordinador.codigoVinculo)
                vista.mostrarResumen(respuesta.resumen)
                vista.mostrarDocentes(respuesta.docente)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }
}