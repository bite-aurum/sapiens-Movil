package com.example.sapiens.Vista.docente

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataTareaReciente
import com.example.sapiens.Presentador.docente.pDashboardDocente
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.adaptadores_docente.tareasRecientesAdaptador
import com.google.android.material.card.MaterialCardView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sapiens.Network.RetrofitCliente

class dashboardDocente : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var tvBienvenida: TextView
    private lateinit var cardProyectos: MaterialCardView
    private lateinit var cardTareas: MaterialCardView
    private lateinit var cardReportes: MaterialCardView
    private lateinit var tvSinTareas: TextView
    private lateinit var navInicio: LinearLayout
    private lateinit var navProyectos: LinearLayout
    private lateinit var navTareas: LinearLayout
    private lateinit var navReportes: LinearLayout
    private lateinit var recyclerTareas: RecyclerView
    private lateinit var adapterTareas: tareasRecientesAdaptador
    private var idDocente: Int = 0

    private lateinit var imgFotoDocente: ImageView

    private lateinit var presentador: pDashboardDocente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_docente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idDocente = intent.getIntExtra("id_docente", prefs.getInt("id_usuario", 0))

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        tvBienvenida = findViewById(R.id.tvBienvenida)
        cardProyectos = findViewById(R.id.cardProyectos)
        cardTareas = findViewById(R.id.cardTareas)
        cardReportes = findViewById(R.id.cardReportes)
        tvSinTareas = findViewById(R.id.tvSinTareas)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)
        navReportes = findViewById(R.id.navReportes)
        imgFotoDocente = findViewById(R.id.imgFotoDocente)

        recyclerTareas = findViewById(R.id.rvTareasRecientes)
        recyclerTareas.layoutManager = LinearLayoutManager(this)
        adapterTareas = tareasRecientesAdaptador(emptyList())
        recyclerTareas.adapter = adapterTareas

        presentador = pDashboardDocente(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        cardProyectos.setOnClickListener(this::ejecutarProyectos)
        cardTareas.setOnClickListener(this::ejecutarTareas)
        cardReportes.setOnClickListener(this::ejecutarReportes)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarTareas)
        navReportes.setOnClickListener(this::ejecutarReportes)
    }

    override fun onResume() {
        super.onResume()
        presentador.iniciar(idDocente)
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


    fun ejecutarInicio(v: View) {
        presentador.iniciar(idDocente)
    }

    fun ejecutarProyectos(v: View) {
        presentador.seleccionarProyectos()
    }

    fun ejecutarTareas(v: View) {
        presentador.seleccionarTareas()
    }

    fun ejecutarReportes(v: View) {
        presentador.seleccionarReportes()
    }

    fun mostrarBienvenida(nombre: String) {
        val nombreMostrar = if (nombre.isNotEmpty()) nombre else "Docente"
        tvBienvenida.text = "Bienvenid@, " + nombreMostrar
    }
    fun mostrarFoto(foto: String?) {
        if (foto.isNullOrEmpty()) return
        Glide.with(this)
            .load(RetrofitCliente.SERVIDOR + foto)
            .circleCrop()
            .into(imgFotoDocente)
    }

    fun mostrarTareasRecientes(tareas: List<dataTareaReciente>) {
        if (tareas.isEmpty()) {
            tvSinTareas.visibility = View.VISIBLE
        } else {
            tvSinTareas.visibility = View.GONE
        }
        adapterTareas.actualizarLista(tareas)
    }

    fun abrirProyectos() {
        val intent = Intent(this, proyectosDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun abrirTareas() {
        val intent = Intent(this, tareasDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun abrirReportes() {
        val intent = Intent(this, reportesDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}