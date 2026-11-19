package com.example.sapiens.Vista.alumno

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataTareaAlumno
import com.example.sapiens.Presentador.alumno.pTareasAlumno
import com.example.sapiens.R
import com.example.sapiens.adaptadores_alumno.TareasAlumnoAdaptador

class misTareasAlumno : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var tvSinTareas: TextView
    private lateinit var navInicio: LinearLayout
    private lateinit var navProyectos: LinearLayout
    private lateinit var recyclerTareas: RecyclerView
    private lateinit var adapterTareas: TareasAlumnoAdaptador
    private var idAlumno: Int = 0

    private lateinit var presentador: pTareasAlumno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mis_tareas_alumno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idAlumno = intent.getIntExtra("id_alumno", prefs.getInt("id_usuario", 0))

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        tvSinTareas = findViewById(R.id.tvSinTareas)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)

        recyclerTareas = findViewById(R.id.rvTareasAlumno)
        recyclerTareas.layoutManager = LinearLayoutManager(this)
        adapterTareas = TareasAlumnoAdaptador(emptyList())
        recyclerTareas.adapter = adapterTareas

        presentador = pTareasAlumno(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
    }

    override fun onResume() {
        super.onResume()
        presentador.iniciar(idAlumno)
    }

    fun ejecutarNotificaciones(v: View) {
        // startActivity(Intent(this, notificacionesAlumno::class.java))  // pendiente
    }

    fun ejecutarPerfil(v: View) {
        // startActivity(Intent(this, perfilAlumno::class.java))  //pendiente
    }

    fun ejecutarInicio(v: View) {
        presentador.seleccionarInicio()
    }

    fun ejecutarProyectos(v: View) {
        presentador.seleccionarProyectos()
    }


    fun mostrarTareas(tareas: List<dataTareaAlumno>) {
        if (tareas.isEmpty()) {
            tvSinTareas.visibility = View.VISIBLE
            recyclerTareas.visibility = View.GONE
        } else {
            tvSinTareas.visibility = View.GONE
            recyclerTareas.visibility = View.VISIBLE
        }
        adapterTareas.actualizarLista(tareas)
    }

    fun abrirInicio() {
        val intent = Intent(this, dashboardAlumno::class.java)
        intent.putExtra("id_alumno", idAlumno)
        startActivity(intent)
    }

    fun abrirProyectos() {
        // val intent = Intent(this, proyectosAlumno::class.java)
        // intent.putExtra("id_alumno", idAlumno)
        // startActivity(intent)
        Toast.makeText(this, "Pantalla de proyectos pendiente", Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}