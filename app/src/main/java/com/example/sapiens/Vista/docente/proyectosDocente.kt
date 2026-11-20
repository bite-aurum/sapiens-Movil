package com.example.sapiens.Vista.docente
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataProyectosDocente
import com.example.sapiens.Presentador.docente.pProyectosDocente
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.adaptadores_docente.ProyectosDocenteAdaptador

class proyectosDocente : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var btnPerfil: ImageView
    private lateinit var btnNuevoProyecto: Button
    private lateinit var navInicio: TextView
    private lateinit var navTareas: TextView
    private lateinit var navReportes: TextView
    private lateinit var recyclerProyectos: RecyclerView
    private lateinit var adapterProyectos: ProyectosDocenteAdaptador
    private var idDocente: Int = 0
    //creamos nuestro objeto presentador
    private lateinit var presentador: pProyectosDocente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_proyectos_docente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        idDocente = intent.getIntExtra("id_docente", 0)

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        btnPerfil = findViewById(R.id.btnPerfil)
        btnNuevoProyecto = findViewById(R.id.btnNuevoProyecto)
        navInicio = findViewById(R.id.navInicio)
        navTareas = findViewById(R.id.navTareas)
        navReportes = findViewById(R.id.navReportes)

        recyclerProyectos = findViewById(R.id.rvProyectos)
        recyclerProyectos.layoutManager = LinearLayoutManager(this)
        adapterProyectos = ProyectosDocenteAdaptador(this, emptyList())
        recyclerProyectos.adapter = adapterProyectos


        presentador = pProyectosDocente(this)


        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        btnNuevoProyecto.setOnClickListener(this::ejecutarNuevoProyecto)
        navInicio.setOnClickListener(this::ejecutarInicio)
        navTareas.setOnClickListener(this::ejecutarTareas)
        navReportes.setOnClickListener(this::ejecutarReportes)

        presentador.iniciar(idDocente)
    }//cierra el oncreate

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

    fun ejecutarNuevoProyecto(v: View) {
        val intent = Intent(this, nuevoProyectoDocente::class.java)
        intent.putExtra("ID_DOCENTE", idDocente)
        startActivity(intent)
    }

    fun ejecutarInicio(v: View) {
        startActivity(Intent(this, dashboardDocente::class.java))
    }

    fun ejecutarTareas(v: View) {
        startActivity(Intent(this, tareasDocente::class.java))
    }

    fun ejecutarReportes(v: View) {
        val intent = Intent(this, reportesDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        startActivity(intent)
    }

    // Botones de la tarjeta (los llama el adaptador)
    fun seleccionarTareas(proyecto: dataProyectosDocente) {
        presentador.seleccionarTareas(proyecto)
    }

    fun seleccionarAlumnos(proyecto: dataProyectosDocente) {
        Toast.makeText(this, "Alumnos de " + proyecto.nombre, Toast.LENGTH_SHORT).show()
    }

    fun seleccionarEntregas(proyecto: dataProyectosDocente) {
        Toast.makeText(this, "Entregas de " + proyecto.nombre, Toast.LENGTH_SHORT).show()
    }

    //Salidas
    fun mostrarProyectos(proyectos: List<dataProyectosDocente>) {
        adapterProyectos.actualizarLista(proyectos)
    }

    fun abrirTareas(idProyecto: Int) {
        val intent = Intent(this, tareasDocente::class.java)
        intent.putExtra("id_docente", idDocente)
        intent.putExtra("id_proyecto", idProyecto)
        startActivity(intent)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}