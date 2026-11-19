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
import com.example.sapiens.Modelo.alumno.dataNotificacionesAlumno
import com.example.sapiens.Presentador.alumno.pNotificacionesAlumno
import com.example.sapiens.R
import com.example.sapiens.adaptadores_alumno.NotificacionesAlumnoAdaptador

class notificacionesAlumno : AppCompatActivity() {
    private lateinit var btnPerfil: ImageView
    private lateinit var tvSinNotificaciones: TextView
    private lateinit var navInicio: LinearLayout
    private lateinit var navProyectos: LinearLayout
    private lateinit var navTareas: LinearLayout
    private lateinit var recyclerNotificaciones: RecyclerView
    private lateinit var adapterNotificaciones: NotificacionesAlumnoAdaptador
    private var idAlumno: Int = 0

    private lateinit var presentador: pNotificacionesAlumno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notificaciones_alumno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idAlumno = intent.getIntExtra("id_alumno", prefs.getInt("id_usuario", 0))

        btnPerfil = findViewById(R.id.btnPerfil)
        tvSinNotificaciones = findViewById(R.id.tvSinNotificaciones)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)

        recyclerNotificaciones = findViewById(R.id.rvNotificacionesAlumno)
        recyclerNotificaciones.layoutManager = LinearLayoutManager(this)
        adapterNotificaciones = NotificacionesAlumnoAdaptador(emptyList())
        recyclerNotificaciones.adapter = adapterNotificaciones

        presentador = pNotificacionesAlumno(this)

        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarTareas)
    }
    override fun onResume() {
        super.onResume()
        presentador.iniciar(idAlumno)
    }

    fun ejecutarPerfil(v: View) {
        // startActivity(Intent(this, perfilAlumno::class.java))  //  pendiente
    }

    fun ejecutarInicio(v: View) {
        presentador.seleccionarInicio()
    }

    fun ejecutarProyectos(v: View) {
        presentador.seleccionarProyectos()
    }

    fun ejecutarTareas(v: View) {
        presentador.seleccionarTareas()
    }


    fun mostrarNotificaciones(notificaciones: List<dataNotificacionesAlumno>) {
        if (notificaciones.isEmpty()) {
            tvSinNotificaciones.visibility = View.VISIBLE
            recyclerNotificaciones.visibility = View.GONE
        } else {
            tvSinNotificaciones.visibility = View.GONE
            recyclerNotificaciones.visibility = View.VISIBLE
        }
        adapterNotificaciones.actualizarLista(notificaciones)
    }

    fun abrirInicio() {
        val intent = Intent(this, dashboardAlumno::class.java)
        intent.putExtra("id_alumno", idAlumno)
        startActivity(intent)
    }

    fun abrirProyectos() {
        val intent = Intent(this, proyectosAlumno::class.java)
        intent.putExtra("id_alumno", idAlumno)
        startActivity(intent)
        Toast.makeText(this, "Pantalla de proyectos pendiente", Toast.LENGTH_SHORT).show()
    }

    fun abrirTareas() {
        val intent = Intent(this, misTareasAlumno::class.java)
        intent.putExtra("id_alumno", idAlumno)
        startActivity(intent)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}