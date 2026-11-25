package com.example.sapiens.Vista.coordinador

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sapiens.R
import com.example.sapiens.Presentador.coordinador.pDocentesCoordinador
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.clsAjustesModelo
import com.example.sapiens.Modelo.coordinador.dataDocenteLista
import com.example.sapiens.Modelo.coordinador.dataResumenDocentes
import com.example.sapiens.adaptadores_coordinador.DocentesCoordinadorAdaptador

class docentesCoordinador : AppCompatActivity() {

    private lateinit var btnPerfil: ImageView
    private lateinit var tvCodigoVinculo: TextView
    private lateinit var tvDocentesRegistrados: TextView
    private lateinit var tvDocentesActivos: TextView
    private lateinit var tvProyectosAsignados: TextView
    private lateinit var navDashboard: TextView
    private lateinit var navProyectos: TextView
    private lateinit var recyclerDocentes: RecyclerView
    private lateinit var adapterDocentes: DocentesCoordinadorAdaptador
    private var idCoordinador: Int = 0

    private lateinit var presentador: pDocentesCoordinador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_docentes_coordinador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        clsAjustesModelo.cargarLogoEn(findViewById(R.id.ivLogoSapiens))

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idCoordinador = intent.getIntExtra("id_coordinador", prefs.getInt("id_usuario", 0))

        btnPerfil = findViewById(R.id.btnPerfil)
        tvCodigoVinculo = findViewById(R.id.tvCodigoVinculo)
        tvDocentesRegistrados = findViewById(R.id.tvDocentesRegistrados)
        tvDocentesActivos = findViewById(R.id.tvDocentesActivos)
        tvProyectosAsignados = findViewById(R.id.tvProyectosAsignados)
        navDashboard = findViewById(R.id.navDashboard)
        navProyectos = findViewById(R.id.navProyectos)

        recyclerDocentes = findViewById(R.id.rvDocentes)
        recyclerDocentes.layoutManager = LinearLayoutManager(this)
        adapterDocentes = DocentesCoordinadorAdaptador(this, emptyList())
        recyclerDocentes.adapter = adapterDocentes

        presentador = pDocentesCoordinador(this)

        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        navDashboard.setOnClickListener(this::ejecutarDashboard)
        navProyectos.setOnClickListener(this::ejecutarProyectos)

        presentador.iniciar(idCoordinador)
    }



    fun ejecutarPerfil(v: View) {
    }

    fun ejecutarDashboard(v: View) {
        val intent = Intent(this, dashboardCoordinador::class.java)
        intent.putExtra("id_coordinador", idCoordinador)
        startActivity(intent)
    }

    fun ejecutarProyectos(v: View) {
        val intent = Intent(this, proyectosCoordinador::class.java)
        intent.putExtra("id_coordinador", idCoordinador)
        startActivity(intent)
    }

    fun mostrarCodigo(codigo: String) {
        tvCodigoVinculo.text = codigo
    }

    fun mostrarResumen(resumen: dataResumenDocentes) {
        tvDocentesRegistrados.text = resumen.docentesRegistrados.toString()
        tvDocentesActivos.text = resumen.docentesActivos.toString()
        tvProyectosAsignados.text = resumen.proyectosAsignados.toString()
    }

    fun mostrarDocentes(docentes: List<dataDocenteLista>) {
        adapterDocentes.actualizarLista(docentes)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}