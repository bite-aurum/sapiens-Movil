package com.example.sapiens.Vista.docente

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataAlumnos
import com.example.sapiens.Modelo.docente.dataNotificacionesEnviadas
import com.example.sapiens.Modelo.docente.dataProyectosDocente
import com.example.sapiens.Presentador.docente.pNotificacionesDocente
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.Vista.docente.Adaptador.NotificacionesDocenteAdaptador
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class notificacionesDocente : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var tvContadorRegistros: TextView
    private lateinit var tvSinNotificaciones: TextView
    private lateinit var rvNotificaciones: RecyclerView
    private lateinit var adaptadorNotificaciones: NotificacionesDocenteAdaptador

    private lateinit var spProyecto: Spinner
    private lateinit var spAlumno: Spinner
    private lateinit var spTipo: Spinner
    private lateinit var etMensaje: EditText
    private lateinit var tvFechaEnvioAuto: TextView
    private lateinit var btnEnviarNotificacion: MaterialButton

    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView
    private lateinit var navReportes: TextView
    private lateinit var progressBar: ProgressBar

    private var listaProyectos: List<dataProyectosDocente> = emptyList()
    private var listaAlumnos: List<dataAlumnos> = emptyList()
    private var fechaEnvioApi: String = ""
    private var idDocente: Int = 1

    private lateinit var presentador: pNotificacionesDocente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notificaciones_docente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        tvContadorRegistros = findViewById(R.id.tvContadorRegistros)
        tvSinNotificaciones = findViewById(R.id.tvSinNotificaciones)

        rvNotificaciones = findViewById(R.id.rvNotificacionesEnviadas)
        rvNotificaciones.layoutManager = LinearLayoutManager(this)
        adaptadorNotificaciones = NotificacionesDocenteAdaptador(this, emptyList())
        rvNotificaciones.adapter = adaptadorNotificaciones

        spProyecto = findViewById(R.id.spProyectoNotif)
        spAlumno = findViewById(R.id.spAlumnoNotif)
        spTipo = findViewById(R.id.spTipoNotif)
        etMensaje = findViewById(R.id.etMensajeNotif)
        tvFechaEnvioAuto = findViewById(R.id.tvFechaEnvioAuto)
        btnEnviarNotificacion = findViewById(R.id.btnEnviarNotificacion)

        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)
        navReportes = findViewById(R.id.navReportes)
        progressBar = findViewById(R.id.progressBarNotificaciones)

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idDocente = intent.getIntExtra("id_docente", prefs.getInt("id_usuario", 1))

        configurarSpinnerTipos()
        llenarSpinnerAlumnos(emptyList())
        configurarFechaActual()

        presentador = pNotificacionesDocente(this)

        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        btnEnviarNotificacion.setOnClickListener(this::ejecutarEnviarNotificacion)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarTareas)
        navReportes.setOnClickListener(this::ejecutarReportes)
        spProyecto.onItemSelectedListener = this

        presentador.cargarDatosIniciales(idDocente)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        if (position > 0 && position - 1 < listaProyectos.size) {
            val idProyecto = listaProyectos[position - 1].idProyecto
            presentador.cargarAlumnosDeProyecto(idProyecto)
        } else {
            llenarSpinnerAlumnos(emptyList())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
    }

    private fun configurarSpinnerTipos() {
        val tipos = listOf("Alerta", "Recordatorio", "Aviso")
        val adaptadorTipos = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        adaptadorTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipo.adapter = adaptadorTipos
    }

    private fun configurarFechaActual() {
        val ahora = Date()
        val formatoVisible = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault())
        val formatoApi = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        tvFechaEnvioAuto.text = formatoVisible.format(ahora)
        fechaEnvioApi = formatoApi.format(ahora)
    }

    fun ejecutarPerfil(v: View) {
        val intent = Intent(this, perfilUsuario::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun ejecutarInicio(v: View) {
        startActivity(Intent(this, dashboardDocente::class.java))
    }

    fun ejecutarProyectos(v: View) {
        startActivity(Intent(this, proyectosDocente::class.java))
    }

    fun ejecutarTareas(v: View) {
        startActivity(Intent(this, tareasDocente::class.java))
    }

    fun ejecutarReportes(v: View) {
        startActivity(Intent(this, reportesDocente::class.java))
    }

    fun ejecutarEnviarNotificacion(v: View) {
        val posAlumno = spAlumno.selectedItemPosition
        var idAlumno = 0
        if (posAlumno > 0 && posAlumno - 1 < listaAlumnos.size) {
            idAlumno = listaAlumnos[posAlumno - 1].idAlumno
        }

        val tipoSeleccionado = spTipo.selectedItem.toString()
        val mensaje = etMensaje.text.toString().trim()

        configurarFechaActual()
        presentador.enviarNotificacion(idDocente, idAlumno, tipoSeleccionado, mensaje, fechaEnvioApi)
    }

    fun mostrarNotificaciones(lista: List<dataNotificacionesEnviadas>) {
        tvContadorRegistros.text = lista.size.toString() + " registros"

        if (lista.isEmpty()) {
            tvSinNotificaciones.visibility = View.VISIBLE
            rvNotificaciones.visibility = View.GONE
        } else {
            tvSinNotificaciones.visibility = View.GONE
            rvNotificaciones.visibility = View.VISIBLE
            adaptadorNotificaciones.actualizarLista(lista)
        }
    }

    fun llenarSpinnerProyectos(proyectos: List<dataProyectosDocente>) {
        listaProyectos = proyectos
        val nombres = mutableListOf("Selecciona un proyecto")
        for (proyecto in proyectos) {
            nombres.add(proyecto.nombre)
        }
        val adaptadorProyectos = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombres)
        adaptadorProyectos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProyecto.adapter = adaptadorProyectos
    }

    fun llenarSpinnerAlumnos(alumnos: List<dataAlumnos>) {
        listaAlumnos = alumnos
        val nombres = mutableListOf("Selecciona un alumno")
        for (alumno in alumnos) {
            nombres.add(alumno.nombre)
        }
        val adaptadorAlumnos = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombres)
        adaptadorAlumnos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spAlumno.adapter = adaptadorAlumnos
    }

    fun mostrarCargando(mostrar: Boolean) {
        if (mostrar) {
            progressBar.visibility = View.VISIBLE
            btnEnviarNotificacion.isEnabled = false
        } else {
            progressBar.visibility = View.GONE
            btnEnviarNotificacion.isEnabled = true
        }
    }

    fun notificacionEnviadaExito(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        etMensaje.text.clear()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}