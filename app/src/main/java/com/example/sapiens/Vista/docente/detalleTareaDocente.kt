package com.example.sapiens.Vista.docente

import androidx.core.content.ContextCompat
import com.example.sapiens.Modelo.clsAjustesModelo
import android.app.DownloadManager
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataArchivoEvidencia
import com.example.sapiens.Modelo.docente.dataSubtareaDetalle
import com.example.sapiens.Modelo.docente.dataTareaDetalle
import com.example.sapiens.Presentador.docente.pDetalleTareaDocente
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.adaptadores_docente.SubtareasDocenteAdaptador

class detalleTareaDocente : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var btnRegresar: TextView
    private lateinit var txtTitulo: TextView
    private lateinit var txtPrioridad: TextView
    private lateinit var txtEstado: TextView
    private lateinit var txtProyectoFecha: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView
    private lateinit var navReportes: TextView
    private lateinit var recyclerSubtareas: RecyclerView
    private lateinit var adapterSubtareas: SubtareasDocenteAdaptador
    private var idDocente: Int = 0
    private var idTarea: Int = 0
    private lateinit var presentador: pDetalleTareaDocente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_tarea_docente)
        clsAjustesModelo.cargarLogoEn(findViewById(R.id.ivLogoSapiens))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        idDocente = intent.getIntExtra("id_docente", getSharedPreferences("SapiensPrefs", MODE_PRIVATE).getInt("id_usuario", 0))
        idTarea = intent.getIntExtra("id_tarea", 0)

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnRegresar = findViewById(R.id.btnRegresar)
        txtTitulo = findViewById(R.id.txtTitulo)
        txtPrioridad = findViewById(R.id.txtPrioridad)
        txtEstado = findViewById(R.id.txtEstado)
        txtProyectoFecha = findViewById(R.id.txtProyectoFecha)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)
        navReportes = findViewById(R.id.navReportes)

        recyclerSubtareas = findViewById(R.id.rvSubtareas)
        recyclerSubtareas.layoutManager = LinearLayoutManager(this)
        adapterSubtareas = SubtareasDocenteAdaptador(this, emptyList())
        recyclerSubtareas.adapter = adapterSubtareas

        presentador = pDetalleTareaDocente(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        btnRegresar.setOnClickListener(this::ejecutarRegresar)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarRegresar)
        navReportes.setOnClickListener(this::ejecutarReportes)

        presentador.iniciar(idTarea)
    }

    fun ejecutarNotificaciones(v: View) {
        abrir(notificacionesDocente::class.java)
    }

    fun ejecutarPerfil(v: View) {
        abrir(perfilUsuario::class.java)
    }

    fun ejecutarRegresar(v: View) {
        finish()
    }

    fun ejecutarInicio(v: View) {
        abrir(dashboardDocente::class.java)
    }

    fun ejecutarProyectos(v: View) {
        abrir(proyectosDocente::class.java)
    }

    fun ejecutarReportes(v: View) {
        abrir(reportesDocente::class.java)
    }

    fun seleccionarVer(archivo: dataArchivoEvidencia) {
        presentador.seleccionarVer(archivo)
    }

    fun seleccionarDescargar(archivo: dataArchivoEvidencia) {
        presentador.seleccionarDescargar(archivo)
    }


    fun mostrarTarea(tarea: dataTareaDetalle) {
        txtTitulo.text = tarea.titulo
        txtDescripcion.text = tarea.descripcion
        txtProyectoFecha.text = tarea.proyecto + " · Vence " + tarea.fechaLimite

        if (tarea.prioridad == "alta") {
            txtPrioridad.text = "Alta"
            txtPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.rojoClaro))
            txtPrioridad.setTextColor(ContextCompat.getColor(this, R.color.rojoTexto))
        } else if (tarea.prioridad == "media") {
            txtPrioridad.text = "Media"
            txtPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.amarilloClaro))
            txtPrioridad.setTextColor(ContextCompat.getColor(this, R.color.amarillo))
        } else {
            txtPrioridad.text = "Baja"
            txtPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.verdeChip))
            txtPrioridad.setTextColor(ContextCompat.getColor(this, R.color.verdeTexto))
        }

        if (tarea.estado == "completada") {
            txtEstado.text = "Completada"
            txtEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.verdeChip))
            txtEstado.setTextColor(ContextCompat.getColor(this, R.color.verdeTexto))
        } else if (tarea.estado == "en progreso") {
            txtEstado.text = "En progreso"
            txtEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.verdeSuave))
            txtEstado.setTextColor(ContextCompat.getColor(this, R.color.verdeAzul))
        } else {
            txtEstado.text = "Pendiente"
            txtEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.amarilloClaro))
            txtEstado.setTextColor(ContextCompat.getColor(this, R.color.amarillo))
        }
    }

    fun mostrarSubtareas(subtareas: List<dataSubtareaDetalle>) {
        adapterSubtareas.actualizarLista(subtareas)
    }

    fun abrirArchivo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    fun descargarArchivo(url: String, nombre: String) {
        val peticion = DownloadManager.Request(Uri.parse(url))
        peticion.setTitle(nombre)
        peticion.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        peticion.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, nombre)
        val manager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(peticion)
        Toast.makeText(this, "Descargando " + nombre, Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun abrir(pantalla: Class<*>) {
        val intent = Intent(this, pantalla)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }
}