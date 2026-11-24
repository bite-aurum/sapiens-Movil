package com.example.sapiens.Vista.alumno

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sapiens.R
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.jvm.java
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataEquipoProyecto
import com.example.sapiens.Modelo.alumno.dataEntregaProyecto
import com.example.sapiens.Modelo.alumno.dataTareaProyecto
import com.example.sapiens.Presentador.alumno.pDetalleProyectoAlumno
import com.example.sapiens.adaptadores_alumno.EquipoProyectoAdaptador
import com.example.sapiens.adaptadores_alumno.EntregasProyectoAdaptador
import com.example.sapiens.adaptadores_alumno.TareasProyectoAdaptador


class detalleProyectoAlumno : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var tvNombreProyecto: TextView
    private lateinit var tvVolver: TextView
    private lateinit var tvTitulo: TextView
    private lateinit var tvProblematica: TextView
    private lateinit var tvObjetivo: TextView
    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView

    private lateinit var recyclerEquipo: RecyclerView
    private lateinit var recyclerEntregas: RecyclerView
    private lateinit var recyclerTareas: RecyclerView
    private lateinit var adapterEquipo: EquipoProyectoAdaptador
    private lateinit var adapterEntregas: EntregasProyectoAdaptador
    private lateinit var adapterTareas: TareasProyectoAdaptador

    private lateinit var presentador: pDetalleProyectoAlumno

    private var idProyecto: Int = 0
    private var idAlumno: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_proyecto_alumno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idProyecto = intent.getIntExtra("id_proyecto", 0)
        idAlumno = intent.getIntExtra("id_alumno", prefs.getInt("id_usuario", 0))

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        tvNombreProyecto = findViewById(R.id.tvNombreProyecto)
        tvVolver = findViewById(R.id.tvVolver)
        tvTitulo = findViewById(R.id.tvTituloProyecto)
        tvProblematica = findViewById(R.id.tvProblematica)
        tvObjetivo = findViewById(R.id.tvObjetivo)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)

        recyclerEquipo = findViewById(R.id.rvEquipo)
        recyclerEquipo.layoutManager = LinearLayoutManager(this)
        adapterEquipo = EquipoProyectoAdaptador(emptyList())
        recyclerEquipo.adapter = adapterEquipo

        recyclerEntregas = findViewById(R.id.rvEntregas)
        recyclerEntregas.layoutManager = LinearLayoutManager(this)
        adapterEntregas = EntregasProyectoAdaptador(emptyList())
        recyclerEntregas.adapter = adapterEntregas

        recyclerTareas = findViewById(R.id.rvTareas)
        recyclerTareas.layoutManager = LinearLayoutManager(this)
        adapterTareas = TareasProyectoAdaptador(emptyList())
        recyclerTareas.adapter = adapterTareas

        presentador = pDetalleProyectoAlumno(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        tvVolver.setOnClickListener { finish() }
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener { finish() }
        navTareas.setOnClickListener(this::ejecutarTareas)

        presentador.iniciar(idProyecto, idAlumno)
    }

    fun ejecutarNotificaciones(v: View) {
    }

    fun ejecutarPerfil(v: View) {
    }

    fun ejecutarInicio(v: View) {
        val intent = Intent(this, dashboardAlumno::class.java)
        intent.putExtra("id_alumno", idAlumno)
        startActivity(intent)
    }

    fun ejecutarTareas(v: View) {
        startActivity(Intent(this, misTareasAlumno::class.java))
    }


    fun mostrarDatos(nombre: String, problematica: String, objetivos: String) {
        tvNombreProyecto.text = nombre
        tvTitulo.text = nombre
        tvProblematica.text = problematica
        tvObjetivo.text = objetivos
    }

    fun mostrarEquipo(equipo: List<dataEquipoProyecto>) {
        adapterEquipo.actualizarLista(equipo)
    }

    fun mostrarEntregas(entregas: List<dataEntregaProyecto>) {
        adapterEntregas.actualizarLista(entregas)
    }

    fun mostrarTareas(tareas: List<dataTareaProyecto>) {
        adapterTareas.actualizarLista(tareas)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}