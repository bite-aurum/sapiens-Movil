package com.example.sapiens.Vista.docente

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataTarea
import com.example.sapiens.Presentador.docente.pTareasDocente
import com.example.sapiens.R
import com.google.android.material.button.MaterialButton

class tareasDocente : AppCompatActivity() {

    private lateinit var presentador: pTareasDocente
    private lateinit var adapter: TareasDocenteAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var tvSinTareas: TextView
    private var idDocente: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tareas_docente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressBarTareas)
        tvSinTareas = findViewById(R.id.tvSinTareas)

        val btnNuevaTarea = findViewById<MaterialButton>(R.id.btnNuevaTarea)
        btnNuevaTarea.setOnClickListener {
            Toast.makeText(this, "Abrir Nueva Tarea", Toast.LENGTH_SHORT).show()
        }

        val rv = findViewById<RecyclerView>(R.id.rvTareasDocente)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = TareasDocenteAdapter(emptyList()) { tarea ->
            presentador.onTareaSeleccionada(tarea.idTarea)
        }
        rv.adapter = adapter

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idDocente = intent.getIntExtra("id_docente", prefs.getInt("id_usuario", 1))

        presentador = pTareasDocente(this)
    }

    override fun onResume() {
        super.onResume()
        if (idDocente != 0) {
            presentador.cargarTareas(idDocente)
        }
    }

    fun mostrarCargando(mostrar: Boolean) {
        progressBar.visibility = if (mostrar) View.VISIBLE else View.GONE
    }

    fun mostrarTareas(tareas: List<dataTarea>) {
        if (tareas.isEmpty()) {
            tvSinTareas.visibility = View.VISIBLE
            adapter.actualizarLista(emptyList())
        } else {
            tvSinTareas.visibility = View.GONE
            adapter.actualizarLista(tareas)
        }
    }

    fun navegarADetalleTarea(idTarea: Int) {
        Toast.makeText(this, "Detalle tarea ID: $idTarea", Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}