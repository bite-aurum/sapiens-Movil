package com.example.sapiens.Vista.docente

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataTarea
import com.example.sapiens.Presentador.docente.pTareasDocente
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.google.android.material.button.MaterialButton

class tareasDocente : AppCompatActivity() {

    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var btnNuevaTarea: MaterialButton
    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navReportes: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvSinTareas: TextView
    private lateinit var recyclerTareas: RecyclerView
    private lateinit var adaptadorTareas: TareasDocenteAdapter

    private var idDocente: Int = 1
    private var idProyectoFiltro: Int = -1
    private lateinit var presentador: pTareasDocente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tareas_docente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnNuevaTarea = findViewById(R.id.btnNuevaTarea)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navReportes = findViewById(R.id.navReportes)
        progressBar = findViewById(R.id.progressBarTareas)
        tvSinTareas = findViewById(R.id.tvSinTareas)

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idDocente = intent.getIntExtra("id_docente", prefs.getInt("id_usuario", 1))
        idProyectoFiltro = intent.getIntExtra("id_proyecto", -1)

        recyclerTareas = findViewById(R.id.rvTareasDocente)
        recyclerTareas.layoutManager = LinearLayoutManager(this)
        adaptadorTareas = TareasDocenteAdapter(emptyMap()) { tarea ->
            presentador.onTareaSeleccionada(tarea.idTarea)
        }
        recyclerTareas.adapter = adaptadorTareas

        presentador = pTareasDocente(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        btnNuevaTarea.setOnClickListener(this::ejecutarNuevaTarea)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navReportes.setOnClickListener(this::ejecutarReportes)
    }

    override fun onResume() {
        super.onResume()
        if (idDocente != 0) {
            presentador.cargarTareas(idDocente)
        }
    }

    fun ejecutarNotificaciones(v: View) {
        val intent = Intent(this, notificacionesDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun ejecutarPerfil(v: View) {
        val intent = Intent(this, perfilUsuario::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun ejecutarNuevaTarea(v: View) {
        val intent = Intent(this, nuevaTareaDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun ejecutarInicio(v: View) {
        startActivity(Intent(this, dashboardDocente::class.java))
    }

    fun ejecutarProyectos(v: View) {
       val intent = Intent(this, proyectosDocente::class.java)
       intent.putExtra("id_docente", idDocente)
       startActivity(intent)
    }

    fun ejecutarReportes(v: View) {
        val intent = Intent(this, reportesDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun mostrarCargando(mostrar: Boolean) {
        progressBar.visibility = if (mostrar) View.VISIBLE else View.GONE
    }

    fun mostrarTareas(tareas: List<dataTarea>) {
        if (tareas.isEmpty()) {
            tvSinTareas.visibility = View.VISIBLE
            adaptadorTareas.actualizarTareas(emptyList())
        } else {
            tvSinTareas.visibility = View.GONE
            adaptadorTareas.actualizarTareas(tareas)
        }
    }

    fun navegarADetalleTarea(idTarea: Int) {
        val intent = Intent(this, detalleTareaDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        intent.putExtra("id_tarea", idTarea)
        startActivity(intent)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}