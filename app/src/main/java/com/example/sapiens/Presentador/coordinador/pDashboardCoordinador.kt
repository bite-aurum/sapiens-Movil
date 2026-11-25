package com.example.sapiens.Presentador.coordinador

import com.example.sapiens.Modelo.coordinador.clsDashboardCoordinadorModelo
import com.example.sapiens.Vista.coordinador.dashboardCoordinador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pDashboardCoordinador(private val vista: dashboardCoordinador) {

    private val modelo = clsDashboardCoordinadorModelo()

    fun iniciar(idCoordinador: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerDashboardCoordinador(idCoordinador)


                if (respuesta.error.isNotEmpty()) {
                    vista.mostrarError(respuesta.error)
                    return@launch
                }

                val conteos = respuesta.dashboard


                val activos = conteos.aTiempo + conteos.conRetraso
                var porcentajeATiempo = 0
                if (activos > 0) {
                    porcentajeATiempo = conteos.aTiempo * 100 / activos
                }

                vista.mostrarBienvenida(respuesta.coordinador.nombre)
                vista.mostrarFoto(respuesta.coordinador.foto)
                vista.mostrarResumen(
                    conteos.totalProyectos,
                    conteos.totalDocentes,
                    porcentajeATiempo,
                    conteos.conRetraso
                )
                vista.mostrarRendimientoDocentes(respuesta.docentes)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }


    fun seleccionarDocentes() {
        vista.abrirDocentes()
    }

    fun seleccionarProyectos() {
        vista.abrirProyectos()
    }
}