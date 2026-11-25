package com.example.sapiens.Presentador.alumno

import com.example.sapiens.Modelo.alumno.clsDashboardAlumno
import com.example.sapiens.Modelo.clsAjustesModelo
import com.example.sapiens.Vista.alumno.dashboardAlumno
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class pDashboardAlumno(private val vista: dashboardAlumno) {

    private val modelo = clsDashboardAlumno()
    private val modeloAjustes = clsAjustesModelo()

    fun cargarDashboard(idAlumno: Int) {
        vista.mostrarCargando(true)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val datos = modelo.obtenerDashboardAlumno(idAlumno)
                val nombreLogo = modeloAjustes.obtenerLogoApp()
                vista.mostrarCargando(false)
                vista.mostrarLogo(nombreLogo)
                vista.mostrarDatosAlumno(datos.alumno)
                vista.mostrarProximaEntrega(datos.proximaEntrega)
                vista.mostrarProyectos(datos.proyectos)
            } catch (e: Exception) {
                vista.mostrarCargando(false)
                vista.mostrarError("Error al cargar dashboard: " + e.localizedMessage)
            }
        }
    }
}