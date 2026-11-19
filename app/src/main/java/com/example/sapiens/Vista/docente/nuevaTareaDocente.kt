package com.example.sapiens.Vista.docente

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sapiens.Modelo.docente.dataProyectosDocente
import com.example.sapiens.Presentador.docente.pNuevaTarea
import com.example.sapiens.R
import com.google.android.material.button.MaterialButton
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.jvm.java

class nuevaTareaDocente : AppCompatActivity() {

    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var btnVolver: TextView
    private lateinit var spProyecto: Spinner
    private lateinit var etNombreTarea: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etFechaLimite: EditText
    private lateinit var tvPrioridadAuto: TextView
    private lateinit var tvEstadoAuto: TextView

    private lateinit var etNombreSubtarea: EditText
    private lateinit var etDescripcionSubtarea: EditText
    private lateinit var btnAgregarSubtarea: MaterialButton
    private lateinit var tvLabelSubtareasAgregadas: TextView
    private lateinit var containerSubtareasAgregadas: LinearLayout
    private lateinit var btnQuitarSubtarea: MaterialButton

    private lateinit var btnCancelarTarea: MaterialButton
    private lateinit var btnGuardarTarea: MaterialButton
    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView
    private lateinit var navReportes: TextView
    private lateinit var progressBar: ProgressBar

    private var listaProyectos: List<dataProyectosDocente> = emptyList()
    private val listaSubtareas = mutableListOf<Pair<String, String>>()
    private var indiceSubtareaSeleccionada: Int = -1
    private var fechaSeleccionadaApi: String = ""
    private var idDocente: Int = 1

    private lateinit var presentador: pNuevaTarea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_tarea_docente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnVolver = findViewById(R.id.btnVolverNuevaTarea)
        spProyecto = findViewById(R.id.spProyectoTarea)
        etNombreTarea = findViewById(R.id.etNombreTarea)
        etDescripcion = findViewById(R.id.etDescripcionTarea)
        etFechaLimite = findViewById(R.id.etFechaLimiteTarea)
        tvPrioridadAuto = findViewById(R.id.tvPrioridadAuto)
        tvEstadoAuto = findViewById(R.id.tvEstadoAuto)

        etNombreSubtarea = findViewById(R.id.etNombreSubtarea)
        etDescripcionSubtarea = findViewById(R.id.etDescripcionSubtarea)
        btnAgregarSubtarea = findViewById(R.id.btnAgregarSubtarea)
        tvLabelSubtareasAgregadas = findViewById(R.id.tvLabelSubtareasAgregadas)
        containerSubtareasAgregadas = findViewById(R.id.containerSubtareasAgregadas)
        btnQuitarSubtarea = findViewById(R.id.btnQuitarSubtarea)

        btnCancelarTarea = findViewById(R.id.btnCancelarTarea)
        btnGuardarTarea = findViewById(R.id.btnGuardarTarea)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)
        navReportes = findViewById(R.id.navReportes)
        progressBar = findViewById(R.id.progressBarNuevaTarea)

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idDocente = intent.getIntExtra("id_docente", prefs.getInt("id_usuario", 1))

        presentador = pNuevaTarea(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        btnVolver.setOnClickListener(this::ejecutarCancelar)
        btnCancelarTarea.setOnClickListener(this::ejecutarCancelar)
        etFechaLimite.setOnClickListener(this::ejecutarSeleccionarFecha)
        btnAgregarSubtarea.setOnClickListener(this::ejecutarAgregarSubtarea)
        btnQuitarSubtarea.setOnClickListener(this::ejecutarQuitarSubtarea)
        btnGuardarTarea.setOnClickListener(this::ejecutarGuardarTarea)

        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarTareas)
        navReportes.setOnClickListener(this::ejecutarReportes)

        presentador.cargarProyectos(idDocente)
    }

    fun ejecutarNotificaciones(v: View) {
        // startActivity(Intent(this, notificacionesDocente::class.java))  // pantalla pendiente
    }

    fun ejecutarPerfil(v: View) {
        // startActivity(Intent(this, perfilDocente::class.java))  // pantalla pendiente
    }

    fun ejecutarCancelar(v: View) {
        finish()
    }

    fun ejecutarInicio(v: View) {
        startActivity(Intent(this, dashboardDocente::class.java))
    }

    fun ejecutarProyectos(v: View) {
       // startActivity(Intent(this, proyectosDocente::class.java))
    }

    fun ejecutarTareas(v: View) {
        finish()
    }

    fun ejecutarReportes(v: View) {
        // startActivity(Intent(this, reportesDocente::class.java))  // pantalla pendiente
    }

    fun ejecutarSeleccionarFecha(v: View) {
        val hoy = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val mesReal = month + 1
                etFechaLimite.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, mesReal, year))
                fechaSeleccionadaApi = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, mesReal, dayOfMonth)

                val fechaObjetivo = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth, 0, 0, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val hoyLimpio = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val diasRestantes = TimeUnit.MILLISECONDS.toDays(fechaObjetivo.timeInMillis - hoyLimpio.timeInMillis)

                tvPrioridadAuto.text = when {
                    diasRestantes <= 3 -> "Alta"
                    diasRestantes <= 7 -> "Media"
                    else -> "Baja"
                }
            },
            hoy.get(Calendar.YEAR),
            hoy.get(Calendar.MONTH),
            hoy.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun ejecutarAgregarSubtarea(v: View) {
        val titulo = etNombreSubtarea.text.toString().trim()
        val desc = etDescripcionSubtarea.text.toString().trim()

        if (titulo.isEmpty()) {
            Toast.makeText(this, "Escribe el nombre de la subtarea", Toast.LENGTH_SHORT).show()
            return
        }

        listaSubtareas.add(Pair(titulo, desc))
        if (indiceSubtareaSeleccionada == -1) {
            indiceSubtareaSeleccionada = 0
        }

        etNombreSubtarea.text.clear()
        etDescripcionSubtarea.text.clear()
        renderizarSubtareas()
    }

    fun ejecutarQuitarSubtarea(v: View) {
        if (indiceSubtareaSeleccionada in listaSubtareas.indices) {
            listaSubtareas.removeAt(indiceSubtareaSeleccionada)
            indiceSubtareaSeleccionada = if (listaSubtareas.isNotEmpty()) 0 else -1
            renderizarSubtareas()
        } else {
            Toast.makeText(this, "Selecciona una subtarea para quitar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun renderizarSubtareas() {
        containerSubtareasAgregadas.removeAllViews()

        if (listaSubtareas.isEmpty()) {
            tvLabelSubtareasAgregadas.visibility = View.GONE
            btnQuitarSubtarea.visibility = View.GONE
            return
        }

        tvLabelSubtareasAgregadas.visibility = View.VISIBLE
        btnQuitarSubtarea.visibility = View.VISIBLE

        val inflater = LayoutInflater.from(this)
        listaSubtareas.forEachIndexed { index, (titulo, desc) ->
            val itemView = inflater.inflate(R.layout.item_subtarea_agregada, containerSubtareasAgregadas, false)
            itemView.findViewById<TextView>(R.id.tvSubtareaTituloItem).text = titulo

            val tvDesc = itemView.findViewById<TextView>(R.id.tvSubtareaDescItem)
            if (desc.isNotEmpty()) {
                tvDesc.visibility = View.VISIBLE
                tvDesc.text = desc
            } else {
                tvDesc.visibility = View.GONE
            }

            val rb = itemView.findViewById<RadioButton>(R.id.rbSeleccionarSubtarea)
            rb.isChecked = (index == indiceSubtareaSeleccionada)

            itemView.setOnClickListener {
                indiceSubtareaSeleccionada = index
                renderizarSubtareas()
            }

            containerSubtareasAgregadas.addView(itemView)
        }
    }

    fun ejecutarGuardarTarea(v: View) {
        val posProyecto = spProyecto.selectedItemPosition
        val idProyecto = if (posProyecto > 0 && posProyecto - 1 < listaProyectos.size) {
            listaProyectos[posProyecto - 1].idProyecto
        } else {
            0
        }

        presentador.guardarTarea(
            idProyecto = idProyecto,
            titulo = etNombreTarea.text.toString().trim(),
            descripcion = etDescripcion.text.toString().trim(),
            fechaLimiteApi = fechaSeleccionadaApi,
            prioridad = tvPrioridadAuto.text.toString(),
            subtitulos = listaSubtareas.map { it.first },
            subdescripciones = listaSubtareas.map { it.second }
        )
    }

    fun llenarSpinnerProyectos(proyectos: List<dataProyectosDocente>) {
        listaProyectos = proyectos
        val nombres = mutableListOf("Selecciona el proyecto")
        nombres.addAll(proyectos.map { it.nombre })
        spProyecto.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nombres)
    }

    fun mostrarCargando(mostrar: Boolean) {
        progressBar.visibility = if (mostrar) View.VISIBLE else View.GONE
    }

    fun tareaGuardadaExito(mensaje: String) {
        Toast.makeText(this, mensaje.ifEmpty { "Tarea guardada" }, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}