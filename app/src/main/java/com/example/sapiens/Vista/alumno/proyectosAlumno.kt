package com.example.sapiens.Vista.alumno

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataProyectosAlumno
import com.example.sapiens.Presentador.alumno.pProyectosAlumno
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.adaptadores_alumno.ProyectosAlumnoAdaptador

class proyectosAlumno : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var navInicio: TextView
    private lateinit var navTareas: TextView
    private lateinit var recyclerProyectos: RecyclerView
    private lateinit var adapterProyectos: ProyectosAlumnoAdaptador
    private var idAlumno: Int = 0
    private lateinit var presentador: pProyectosAlumno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_proyectos_alumno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        idAlumno = intent.getIntExtra("id_alumno", 0)

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        navInicio = findViewById(R.id.navInicio)
        navTareas = findViewById(R.id.navTareas)

        recyclerProyectos = findViewById(R.id.rvProyectos)
        recyclerProyectos.layoutManager = LinearLayoutManager(this)
        adapterProyectos = ProyectosAlumnoAdaptador(this, emptyList())
        recyclerProyectos.adapter = adapterProyectos

        presentador = pProyectosAlumno(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navTareas.setOnClickListener(this::ejecutarTareas)

        presentador.iniciar(idAlumno)
    }

    fun ejecutarNotificaciones(v: View) {
        abrir(notificacionesAlumno::class.java)
    }

    fun ejecutarPerfil(v: View) {
        abrir(perfilUsuario::class.java)
    }

    fun ejecutarInicio(v: View) {
        abrir(dashboardAlumno::class.java)
    }

    fun ejecutarTareas(v: View) {
        abrir(misTareasAlumno::class.java)
    }

    fun seleccionarProyecto(proyecto: dataProyectosAlumno) {
        presentador.seleccionarProyecto(proyecto)
    }

    //Salidas
    fun mostrarProyectos(proyectos: List<dataProyectosAlumno>) {
        adapterProyectos.actualizarLista(proyectos)
    }

    fun abrirDetalleProyecto(idProyecto: Int) {
        // pantalla pendiente
        val intent = Intent(this, detalleProyectoAlumno::class.java)
        intent.putExtra("id_alumno", idAlumno)
        intent.putExtra("id_proyecto", idProyecto)
        startActivity(intent)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun abrir(pantalla: Class<*>) {
        val intent = Intent(this, pantalla)
        intent.putExtra("id_alumno", idAlumno)
        startActivity(intent)
    }
}