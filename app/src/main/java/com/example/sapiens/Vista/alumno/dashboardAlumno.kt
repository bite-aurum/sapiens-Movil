package com.example.sapiens.Vista.alumno

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataAlumnoDashboard
import com.example.sapiens.Modelo.alumno.dataProyectosAlumno
import com.example.sapiens.Modelo.alumno.dataProximaEntrega
import com.example.sapiens.Presentador.alumno.pDashboardAlumno
import com.example.sapiens.R
import com.example.sapiens.adaptadores_alumno.ProyectosDashboardAlumnoAdaptador
import com.google.android.material.card.MaterialCardView

class dashboardAlumno : AppCompatActivity() {

    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var tvBienvenida: TextView

    private lateinit var cardAccesoProyectos: MaterialCardView
    private lateinit var cardAccesoTareas: MaterialCardView

    private lateinit var cardProximaEntrega: MaterialCardView
    private lateinit var tvSinProximaEntrega: TextView
    private lateinit var contenedorDetalleEntrega: LinearLayout
    private lateinit var tvTituloProximaEntrega: TextView
    private lateinit var tvProyectoProximaEntrega: TextView
    private lateinit var tvBadgeEstadoEntrega: TextView
    private lateinit var tvFechaProximaEntrega: TextView

    private lateinit var btnVerTodosProyectos: TextView
    private lateinit var tvSinProyectos: TextView
    private lateinit var rvProyectos: RecyclerView
    private lateinit var adaptadorProyectos: ProyectosDashboardAlumnoAdaptador

    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView
    private lateinit var progressBar: ProgressBar

    private var idAlumno: Int = 0
    private var idTareaProxima: Int = 0
    private lateinit var presentador: pDashboardAlumno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_alumno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnNotificaciones = findViewById(R.id.btnNotificacionesAlumno)
        btnPerfil = findViewById(R.id.btnPerfilAlumno)
        tvBienvenida = findViewById(R.id.tvBienvenidaAlumno)

        cardAccesoProyectos = findViewById(R.id.cardAccesoProyectosAlumno)
        cardAccesoTareas = findViewById(R.id.cardAccesoTareasAlumno)

        cardProximaEntrega = findViewById(R.id.cardProximaEntregaAlumno)
        tvSinProximaEntrega = findViewById(R.id.tvSinProximaEntrega)
        contenedorDetalleEntrega = findViewById(R.id.contenedorDetalleProximaEntrega)
        tvTituloProximaEntrega = findViewById(R.id.tvTituloProximaEntrega)
        tvProyectoProximaEntrega = findViewById(R.id.tvProyectoProximaEntrega)
        tvBadgeEstadoEntrega = findViewById(R.id.tvBadgeEstadoEntrega)
        tvFechaProximaEntrega = findViewById(R.id.tvFechaProximaEntrega)

        btnVerTodosProyectos = findViewById(R.id.btnVerTodosProyectosAlumno)
        tvSinProyectos = findViewById(R.id.tvSinProyectosAlumno)

        rvProyectos = findViewById(R.id.rvProyectosDashboardAlumno)
        rvProyectos.layoutManager = LinearLayoutManager(this)
        adaptadorProyectos = ProyectosDashboardAlumnoAdaptador(this, emptyList())
        rvProyectos.adapter = adaptadorProyectos

        navInicio = findViewById(R.id.navInicioAlumno)
        navProyectos = findViewById(R.id.navProyectosAlumno)
        navTareas = findViewById(R.id.navTareasAlumno)
        progressBar = findViewById(R.id.progressBarDashboardAlumno)

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idAlumno = intent.getIntExtra("id_alumno", prefs.getInt("id_usuario", 1))

        presentador = pDashboardAlumno(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        cardAccesoProyectos.setOnClickListener(this::ejecutarProyectos)
        cardAccesoTareas.setOnClickListener(this::ejecutarTareas)
        cardProximaEntrega.setOnClickListener(this::ejecutarDetalleProximaEntrega)
        btnVerTodosProyectos.setOnClickListener(this::ejecutarProyectos)

        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarTareas)

        presentador.cargarDashboard(idAlumno)
    }

    override fun onResume() {
        super.onResume()
        presentador.cargarDashboard(idAlumno)
    }

    fun ejecutarNotificaciones(v: View) {
        // startActivity(Intent(this, notificacionesAlumno::class.java))
    }

    fun ejecutarPerfil(v: View) {
        // startActivity(Intent(this, perfilAlumno::class.java))
    }

    fun ejecutarInicio(v: View) {
        presentador.cargarDashboard(idAlumno)
    }

    fun ejecutarProyectos(v: View) {
        // startActivity(Intent(this, proyectosAlumno::class.java))
    }

    fun ejecutarTareas(v: View) {
        // startActivity(Intent(this, tareasAlumno::class.java))
    }

    fun ejecutarDetalleProximaEntrega(v: View) {
        if (idTareaProxima > 0) {
            // val intent = Intent(this, detalleTareaAlumno::class.java)
            // intent.putExtra("id_tarea", idTareaProxima)
            // startActivity(intent)
        }
    }

    fun seleccionarProyecto(proyecto: dataProyectosAlumno) {
        // val intent = Intent(this, detalleProyectoAlumno::class.java)
        // intent.putExtra("id_proyecto", proyecto.idProyecto)
        // startActivity(intent)
    }

    fun mostrarDatosAlumno(alumno: dataAlumnoDashboard) {
        val nombreMostrar = if (alumno.nombre.isNotEmpty()) {
            alumno.nombre
        } else {
            "Alumno"
        }
        tvBienvenida.text = "Bienvenid@, " + nombreMostrar
    }

    fun mostrarProximaEntrega(proxima: dataProximaEntrega?) {
        if (proxima == null || proxima.idTarea <= 0) {
            idTareaProxima = 0
            tvSinProximaEntrega.visibility = View.VISIBLE
            contenedorDetalleEntrega.visibility = View.GONE
        } else {
            idTareaProxima = proxima.idTarea
            tvSinProximaEntrega.visibility = View.GONE
            contenedorDetalleEntrega.visibility = View.VISIBLE
            tvTituloProximaEntrega.text = proxima.titulo
            tvProyectoProximaEntrega.text = proxima.proyecto
            tvBadgeEstadoEntrega.text = "Fecha límite"
            tvFechaProximaEntrega.text = proxima.fechaLimite
        }
    }

    fun mostrarProyectos(proyectos: List<dataProyectosAlumno>) {
        if (proyectos.isEmpty()) {
            tvSinProyectos.visibility = View.VISIBLE
            rvProyectos.visibility = View.GONE
        } else {
            tvSinProyectos.visibility = View.GONE
            rvProyectos.visibility = View.VISIBLE
            adaptadorProyectos.actualizarLista(proyectos)
        }
    }

    fun mostrarCargando(mostrar: Boolean) {
        if (mostrar) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}