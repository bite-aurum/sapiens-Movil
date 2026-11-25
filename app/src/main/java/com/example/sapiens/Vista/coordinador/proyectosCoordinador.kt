package com.example.sapiens.Vista.coordinador

import android.content.Intent
import android.os.Bundle
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
import com.example.sapiens.Modelo.clsAjustesModelo
import com.example.sapiens.Modelo.coordinador.dataProyectoCoordinador
import com.example.sapiens.Modelo.coordinador.dataResumenCoordinador
import com.example.sapiens.Presentador.coordinador.pProyectosCordinador
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.adaptadores_coordinador.ProyectosCoordinadorAdaptador

class proyectosCoordinador : AppCompatActivity() {

    private lateinit var btnPerfil: ImageView
    private lateinit var txtProyectosTotales: TextView
    private lateinit var txtDocentesAsignados: TextView
    private lateinit var txtTasaEntrega: TextView
    private lateinit var txtAvisosPendientes: TextView
    private lateinit var navDashboard: TextView
    private lateinit var navDocentes: TextView
    private lateinit var recyclerProyectos: RecyclerView
    private lateinit var adapterProyectos: ProyectosCoordinadorAdaptador
    private var idCoordinador: Int = 0
    private lateinit var presentador: pProyectosCordinador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_proyectos_coordinador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        idCoordinador = intent.getIntExtra("id_coordinador", 0)

        btnPerfil = findViewById(R.id.btnPerfil)
        txtProyectosTotales = findViewById(R.id.txtProyectosTotales)
        txtDocentesAsignados = findViewById(R.id.txtDocentesAsignados)
        txtTasaEntrega = findViewById(R.id.txtTasaEntrega)
        txtAvisosPendientes = findViewById(R.id.txtAvisosPendientes)
        navDashboard = findViewById(R.id.navDashboard)
        navDocentes = findViewById(R.id.navDocentes)

        recyclerProyectos = findViewById(R.id.rvProyectos)
        recyclerProyectos.layoutManager = LinearLayoutManager(this)
        adapterProyectos = ProyectosCoordinadorAdaptador(emptyList())
        recyclerProyectos.adapter = adapterProyectos

        presentador = pProyectosCordinador(this)


        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        navDashboard.setOnClickListener(this::ejecutarDashboard)
        navDocentes.setOnClickListener(this::ejecutarDocentes)

        presentador.iniciar(idCoordinador)

        clsAjustesModelo.cargarLogoEn(findViewById(R.id.ivLogoSapiens))
    }



    fun ejecutarPerfil(v: View) {
        abrir(perfilUsuario::class.java)
    }

    fun ejecutarDashboard(v: View) {
        abrir(dashboardCoordinador::class.java)
    }

    fun ejecutarDocentes(v: View) {
        abrir(docentesCoordinador::class.java)
    }

    fun mostrarResumen(resumen: dataResumenCoordinador) {
        txtProyectosTotales.text = resumen.proyectosTotales.toString()
        txtDocentesAsignados.text = resumen.docentesAsignados.toString()
        txtTasaEntrega.text = resumen.tasaEntrega.toString() + "%"
        txtAvisosPendientes.text = resumen.avisosPendientes.toString()
    }

    fun mostrarProyectos(proyectos: List<dataProyectoCoordinador>) {
        adapterProyectos.actualizarLista(proyectos)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun abrir(pantalla: Class<*>) {
        val intent = Intent(this, pantalla)
        intent.putExtra("id_coordinador", idCoordinador)
        startActivity(intent)
    }
}