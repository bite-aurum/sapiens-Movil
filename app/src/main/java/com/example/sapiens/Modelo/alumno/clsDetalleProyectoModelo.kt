package com.example.sapiens.Modelo.alumno
import com.example.sapiens.Modelo.RetrofitCliente

class clsDetalleProyectoModelo {
    suspend fun obtenerDetalle(idProyecto: Int, idAlumno: Int): dataDetalleProyecto {
        return RetrofitCliente.apiService.obtenerDetalleProyecto(idProyecto, idAlumno)
    }
}