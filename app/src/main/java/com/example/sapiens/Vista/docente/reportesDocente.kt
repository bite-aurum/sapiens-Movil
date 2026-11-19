package com.example.sapiens.Vista.docente

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sapiens.R
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataProyectoReporte
import com.example.sapiens.Presentador.docente.pReportesDocente
import java.text.SimpleDateFormat
import java.util.*
import com.example.sapiens.Modelo.docente.dataReporteDocente
import com.example.sapiens.adaptadores_docente.HistorialReportesAdaptador

class reportesDocente : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var spnTipo: Spinner
    private lateinit var spnProyecto: Spinner
    private lateinit var tvFecha: TextView
    private lateinit var btnGenerar: Button
    private lateinit var tvVistaPrevia: TextView
    private lateinit var btnDescargar: Button
    private lateinit var rvHistorial: RecyclerView
    private lateinit var navInicio: TextView
    private lateinit var navProyectos: TextView
    private lateinit var navTareas: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: pReportesDocente
    private lateinit var adaptador: HistorialReportesAdaptador

    private var idDocente: Int = 0
    private var contenidoReporte: String = ""
    private var listaIdProyectos = mutableListOf<Int>()

    private val guardarArchivo = registerForActivityResult(
        ActivityResultContracts.CreateDocument("text/plain")
    ) { uri ->
        if (uri != null) {
            val salida = contentResolver.openOutputStream(uri)
            salida?.write(contenidoReporte.toByteArray())
            salida?.close()
            Toast.makeText(this, "Reporte guardado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reportes_docente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idDocente = intent.getIntExtra("id_docente", prefs.getInt("id_usuario", 0))

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        spnTipo = findViewById(R.id.spnTipoReporte)
        spnProyecto = findViewById(R.id.spnProyectoReporte)
        tvFecha = findViewById(R.id.tvFechaGeneracion)
        btnGenerar = findViewById(R.id.btnGenerarReporte)
        tvVistaPrevia = findViewById(R.id.tvVistaPrevia)
        btnDescargar = findViewById(R.id.btnDescargarReporte)
        rvHistorial = findViewById(R.id.rvHistorial)
        navInicio = findViewById(R.id.navInicio)
        navProyectos = findViewById(R.id.navProyectos)
        navTareas = findViewById(R.id.navTareas)
        progressBar = findViewById(R.id.progressBarReportes)

        rvHistorial.layoutManager = LinearLayoutManager(this)
        adaptador = HistorialReportesAdaptador(this, emptyList())
        rvHistorial.adapter = adaptador

        tvFecha.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        presenter = pReportesDocente(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        btnGenerar.setOnClickListener(this::ejecutarGenerar)
        btnDescargar.setOnClickListener(this::ejecutarDescargar)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
        navTareas.setOnClickListener(this::ejecutarTareas)

        presenter.iniciar(idDocente)
    }

    fun ejecutarNotificaciones(v: View) {
    }

    fun ejecutarPerfil(v: View) {
    }

    fun ejecutarGenerar(v: View) {
        var tipo = "avance"
        if (spnTipo.selectedItemPosition == 1) {
            tipo = "entregas"
        } else if (spnTipo.selectedItemPosition == 2) {
            tipo = "alumnos"
        }

        var idProyecto = 0
        if (listaIdProyectos.size > spnProyecto.selectedItemPosition) {
            idProyecto = listaIdProyectos[spnProyecto.selectedItemPosition]
        }

        presenter.generarReporte(idDocente, tipo, idProyecto)
    }

    fun ejecutarDescargar(v: View) {
        if (contenidoReporte == "") {
            mostrarError("Primero genera un reporte")
            return
        }
        guardarArchivo.launch("reporte.txt")
    }

    fun ejecutarInicio(v: View) {
        val intent = Intent(this, dashboardDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun ejecutarProyectos(v: View) {
        val intent = Intent(this, proyectosDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    fun ejecutarTareas(v: View) {
        val intent = Intent(this, tareasDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }


    fun seleccionarReporte(reporte: dataReporteDocente) {
        mostrarVistaPrevia(reporte.contenido)
    }


    fun llenarSpinnerProyectos(proyectos: List<dataProyectoReporte>) {
        val nombres = mutableListOf("Todos mis proyectos")
        listaIdProyectos = mutableListOf(0)

        for (p in proyectos) {
            nombres.add(p.nombre)
            listaIdProyectos.add(p.idProyecto)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnProyecto.adapter = adapter
    }

    fun mostrarHistorial(reportes: List<dataReporteDocente>) {
        adaptador.actualizarLista(reportes)
    }

    fun mostrarVistaPrevia(contenido: String) {
        contenidoReporte = contenido
        tvVistaPrevia.text = contenido
    }

    fun mostrarCargando(mostrar: Boolean) {
        if (mostrar) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
        btnGenerar.isEnabled = !mostrar
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}