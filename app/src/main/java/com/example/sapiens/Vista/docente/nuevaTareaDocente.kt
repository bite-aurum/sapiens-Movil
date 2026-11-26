package com.example.sapiens.Vista.docente

import androidx.core.content.ContextCompat
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.DatePicker
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
import com.example.sapiens.Modelo.docente.SubtareaTemporal
import com.example.sapiens.Modelo.docente.dataProyectosDocente
import com.example.sapiens.Presentador.docente.pNuevaTarea
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.google.android.material.button.MaterialButton
import java.util.Calendar
import java.util.Locale
import com.example.sapiens.adaptadores_docente.SubtareasAgregadasAdaptador

class nuevaTareaDocente : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var btnVolverTareas: TextView

    private lateinit var spProyecto: Spinner
    private lateinit var etTituloTarea: EditText
    private lateinit var etDescripcionTarea: EditText
    private lateinit var tvFechaLimite: TextView
    private lateinit var layoutPrioridad: LinearLayout
    private lateinit var tvPrioridad: TextView

    private lateinit var etTituloSubtarea: EditText
    private lateinit var etDescripcionSubtarea: EditText
    private lateinit var btnAgregarSubtarea: MaterialButton
    private lateinit var tvContadorSubtareas: TextView
    private lateinit var rvSubtareas: RecyclerView
    private lateinit var adaptadorSubtareas: SubtareasAgregadasAdaptador

    private lateinit var btnGuardarTarea: MaterialButton
    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView
    private lateinit var navReportes: TextView
    private lateinit var progressBar: ProgressBar

    private var listaProyectos: List<dataProyectosDocente> = emptyList()
    private val listaSubtareas = mutableListOf<SubtareaTemporal>()
    private var fechaSeleccionadaApi: String = ""
    private var prioridadCalculada: String = ""
    private var idDocente: Int = 0

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
        btnVolverTareas = findViewById(R.id.btnVolverTareas)

        spProyecto = findViewById(R.id.spProyectoTarea)
        etTituloTarea = findViewById(R.id.etTituloTarea)
        etDescripcionTarea = findViewById(R.id.etDescripcionTarea)
        tvFechaLimite = findViewById(R.id.tvFechaLimiteTarea)
        layoutPrioridad = findViewById(R.id.layoutPrioridad)
        tvPrioridad = findViewById(R.id.tvPrioridadTarea)

        etTituloSubtarea = findViewById(R.id.etTituloSubtarea)
        etDescripcionSubtarea = findViewById(R.id.etDescripcionSubtarea)
        btnAgregarSubtarea = findViewById(R.id.btnAgregarSubtarea)
        tvContadorSubtareas = findViewById(R.id.tvContadorSubtareas)

        rvSubtareas = findViewById(R.id.rvSubtareasAgregadas)
        rvSubtareas.layoutManager = LinearLayoutManager(this)
        adaptadorSubtareas = SubtareasAgregadasAdaptador(this, listaSubtareas)
        rvSubtareas.adapter = adaptadorSubtareas

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
        btnVolverTareas.setOnClickListener(this::ejecutarVolverTareas)
        tvFechaLimite.setOnClickListener(this::ejecutarSeleccionarFecha)
        btnAgregarSubtarea.setOnClickListener(this::ejecutarAgregarSubtarea)
        btnGuardarTarea.setOnClickListener(this::ejecutarGuardarTarea)

        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarTareas)
        navReportes.setOnClickListener(this::ejecutarReportes)

        presentador.cargarProyectos(idDocente)
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

    fun ejecutarVolverTareas(v: View) {
        finish()
    }

    fun ejecutarInicio(v: View) {
        startActivity(Intent(this, dashboardDocente::class.java))
    }

    fun ejecutarProyectos(v: View) {
        startActivity(Intent(this, proyectosDocente::class.java))
    }

    fun ejecutarTareas(v: View) {
        finish()
    }

    fun ejecutarReportes(v: View) {
        startActivity(Intent(this, reportesDocente::class.java))
    }

    fun ejecutarSeleccionarFecha(v: View) {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val selector = DatePickerDialog(this, this, anio, mes, dia)
        selector.show()
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val mesReal = month + 1
        val fechaVisible = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, mesReal, year)
        fechaSeleccionadaApi = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, mesReal, dayOfMonth)
        tvFechaLimite.text = fechaVisible

        calcularPrioridad(year, month, dayOfMonth)
    }

    private fun calcularPrioridad(year: Int, month: Int, dayOfMonth: Int) {
        val hoy = Calendar.getInstance()
        hoy.set(Calendar.HOUR_OF_DAY, 0)
        hoy.set(Calendar.MINUTE, 0)
        hoy.set(Calendar.SECOND, 0)
        hoy.set(Calendar.MILLISECOND, 0)

        val fechaElegida = Calendar.getInstance()
        fechaElegida.set(year, month, dayOfMonth, 0, 0, 0)
        fechaElegida.set(Calendar.MILLISECOND, 0)

        val milisegundos = fechaElegida.timeInMillis - hoy.timeInMillis
        val dias = Math.round(milisegundos / 86400000.0).toInt()

        if (dias <= 3) {
            prioridadCalculada = "alta"
            tvPrioridad.text = "Alta"
            tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojoClaro))
            tvPrioridad.setTextColor(ContextCompat.getColor(this, R.color.rojoTexto))
        } else if (dias <= 7) {
            prioridadCalculada = "media"
            tvPrioridad.text = "Media"
            tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.amarilloClaro))
            tvPrioridad.setTextColor(ContextCompat.getColor(this, R.color.amarillo))
        } else {
            prioridadCalculada = "baja"
            tvPrioridad.text = "Baja"
            tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.verdeChip))
            tvPrioridad.setTextColor(ContextCompat.getColor(this, R.color.verdeTexto))
        }

        layoutPrioridad.visibility = View.VISIBLE
    }

    fun ejecutarAgregarSubtarea(v: View) {
        val tituloSub = etTituloSubtarea.text.toString().trim()
        val descSub = etDescripcionSubtarea.text.toString().trim()

        if (tituloSub.isEmpty()) {
            mostrarError("Escribe el título de la subtarea")
            return
        }

        listaSubtareas.add(SubtareaTemporal(tituloSub, descSub))
        etTituloSubtarea.text.clear()
        etDescripcionSubtarea.text.clear()
        actualizarListaSubtareas()
    }

    fun eliminarSubtarea(posicion: Int) {
        if (posicion >= 0 && posicion < listaSubtareas.size) {
            listaSubtareas.removeAt(posicion)
            actualizarListaSubtareas()
        }
    }

    private fun actualizarListaSubtareas() {
        tvContadorSubtareas.text = "Subtareas agregadas (" + listaSubtareas.size + ")"
        adaptadorSubtareas.actualizarLista(listaSubtareas)
    }

    fun ejecutarGuardarTarea(v: View) {
        val posicion = spProyecto.selectedItemPosition
        var idProyecto = 0
        if (posicion > 0 && posicion - 1 < listaProyectos.size) {
            idProyecto = listaProyectos[posicion - 1].idProyecto
        }

        val titulo = etTituloTarea.text.toString().trim()
        val descripcion = etDescripcionTarea.text.toString().trim()

        presentador.guardarNuevaTarea(
            idProyecto = idProyecto,
            titulo = titulo,
            descripcion = descripcion,
            fechaLimiteApi = fechaSeleccionadaApi,
            prioridad = prioridadCalculada,
            subtareas = listaSubtareas
        )
    }

    fun llenarSpinnerProyectos(proyectos: List<dataProyectosDocente>) {
        listaProyectos = proyectos
        val nombres = mutableListOf("Selecciona un proyecto")
        for (proyecto in proyectos) {
            nombres.add(proyecto.nombre)
        }
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombres)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProyecto.adapter = adaptador
    }

    fun mostrarCargando(mostrar: Boolean) {
        if (mostrar) {
            progressBar.visibility = View.VISIBLE
            btnGuardarTarea.isEnabled = false
        } else {
            progressBar.visibility = View.GONE
            btnGuardarTarea.isEnabled = true
        }
    }

    fun tareaGuardadaExito(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}