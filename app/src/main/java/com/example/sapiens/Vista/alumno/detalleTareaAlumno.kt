package com.example.sapiens.Vista.alumno

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataDetalleTareaAlumno
import com.example.sapiens.Modelo.alumno.dataSubtareaAlumno
import com.example.sapiens.Modelo.clsAjustesModelo
import com.example.sapiens.Presentador.alumno.pDetalleTareaAlumno
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.adaptadores_alumno.SubtareasAlumnoAdaptador
import java.io.File

class detalleTareaAlumno : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var btnVolver: TextView
    private lateinit var txtTitulo: TextView
    private lateinit var txtProyecto: TextView
    private lateinit var txtSubtitulo: TextView
    private lateinit var txtPrioridad: TextView
    private lateinit var txtEstado: TextView
    private lateinit var barraProgreso: ProgressBar
    private lateinit var txtProgreso: TextView
    private lateinit var spinnerSubtareas: Spinner
    private lateinit var txtArchivo: TextView
    private lateinit var btnSeleccionar: Button
    private lateinit var btnSubir: Button
    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView
    private lateinit var recyclerSubtareas: RecyclerView
    private lateinit var adapterSubtareas: SubtareasAlumnoAdaptador
    private var idAlumno: Int = 0
    private var idTarea: Int = 0
    private var archivoElegido: File? = null
    private var tipoMime: String = "application/octet-stream"
    private lateinit var presentador: pDetalleTareaAlumno

    private val elegirArchivo = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            guardarArchivo(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_tarea_alumno)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        clsAjustesModelo.cargarLogoEn(findViewById(R.id.ivLogoSapiens))
        idAlumno = intent.getIntExtra("id_alumno", 0)
        idTarea = intent.getIntExtra("id_tarea", 0)

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnVolver = findViewById(R.id.btnVolver)
        txtTitulo = findViewById(R.id.txtTitulo)
        txtProyecto = findViewById(R.id.txtProyecto)
        txtSubtitulo = findViewById(R.id.txtSubtitulo)
        txtPrioridad = findViewById(R.id.txtPrioridad)
        txtEstado = findViewById(R.id.txtEstado)
        barraProgreso = findViewById(R.id.barraProgreso)
        txtProgreso = findViewById(R.id.txtProgreso)
        spinnerSubtareas = findViewById(R.id.spnSubtareas)
        txtArchivo = findViewById(R.id.txtArchivo)
        btnSeleccionar = findViewById(R.id.btnSeleccionar)
        btnSubir = findViewById(R.id.btnSubir)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)

        recyclerSubtareas = findViewById(R.id.rvSubtareas)
        recyclerSubtareas.layoutManager = LinearLayoutManager(this)
        adapterSubtareas = SubtareasAlumnoAdaptador(emptyList())
        recyclerSubtareas.adapter = adapterSubtareas

        presentador = pDetalleTareaAlumno(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        btnVolver.setOnClickListener(this::ejecutarVolver)
        btnSeleccionar.setOnClickListener(this::ejecutarSeleccionar)
        btnSubir.setOnClickListener(this::ejecutarSubir)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarVolver)

        presentador.iniciar(idTarea, idAlumno)
    }

    fun ejecutarNotificaciones(v: View) {
        abrir(notificacionesAlumno::class.java)
    }

    fun ejecutarPerfil(v: View) {
        abrir(perfilUsuario::class.java)
    }

    fun ejecutarVolver(v: View) {
        finish()
    }

    fun ejecutarInicio(v: View) {
        abrir(dashboardAlumno::class.java)
    }

    fun ejecutarProyectos(v: View) {
        abrir(proyectosAlumno::class.java)
    }

    fun ejecutarSeleccionar(v: View) {
        elegirArchivo.launch("*/*")
    }

    fun ejecutarSubir(v: View) {
        presentador.subirArchivo(idTarea, idAlumno, spinnerSubtareas.selectedItemPosition, archivoElegido, tipoMime)
    }

    fun mostrarDetalle(detalle: dataDetalleTareaAlumno) {
        txtTitulo.text = detalle.titulo
        txtProyecto.text = detalle.proyecto
        txtSubtitulo.text = detalle.subtitulo
        txtPrioridad.text = detalle.prioridad
        txtEstado.text = detalle.estado
        barraProgreso.progress = detalle.progreso
        txtProgreso.text = detalle.progreso.toString() + "%"

        if (detalle.prioridad == "Alta") {
            txtPrioridad.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FCE4E4"))
            txtPrioridad.setTextColor(Color.parseColor("#C93B3B"))
        } else if (detalle.prioridad == "Media") {
            txtPrioridad.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
            txtPrioridad.setTextColor(Color.parseColor("#9C7414"))
        } else {
            txtPrioridad.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            txtPrioridad.setTextColor(Color.parseColor("#1B7F4B"))
        }

        if (detalle.estado == "Completada") {
            txtEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            txtEstado.setTextColor(Color.parseColor("#1B7F4B"))
        } else {
            txtEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E3F2EE"))
            txtEstado.setTextColor(Color.parseColor("#0D7A6B"))
        }
    }

    fun mostrarSubtareas(subtareas: List<dataSubtareaAlumno>) {
        adapterSubtareas.actualizarLista(subtareas)

        val nombres = mutableListOf("Seleccione una subtarea")
        for (subtarea in subtareas) {
            nombres.add(subtarea.titulo)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSubtareas.adapter = adapter
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun guardarArchivo(uri: Uri) {
        var nombre = "archivo"
        val cursor = contentResolver.query(uri, null, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                nombre = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
            }
            cursor.close()
        }

        val archivo = File(cacheDir, nombre)
        val entrada = contentResolver.openInputStream(uri)
        val salida = archivo.outputStream()
        entrada?.copyTo(salida)
        entrada?.close()
        salida.close()

        archivoElegido = archivo
        tipoMime = contentResolver.getType(uri) ?: "application/octet-stream"
        txtArchivo.text = nombre
    }

    private fun abrir(pantalla: Class<*>) {
        val intent = Intent(this, pantalla)
        intent.putExtra("id_alumno", idAlumno)
        startActivity(intent)
    }
}