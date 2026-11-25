package com.example.sapiens.Vista.coordinador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.clsAjustesModelo
import com.example.sapiens.Modelo.coordinador.dataDocenteRendimiento
import com.example.sapiens.Presentador.coordinador.pDashboardCoordinador
import com.example.sapiens.R
import com.example.sapiens.Vista.perfilUsuario
import com.example.sapiens.adaptadores_coordinador.RendimientoDocentesAdaptador

class dashboardCoordinador : AppCompatActivity() {

    private lateinit var btnPerfil: ImageView
    private lateinit var tvBienvenida: TextView
    private lateinit var tvTotalProyectos: TextView
    private lateinit var tvTotalDocentes: TextView
    private lateinit var tvEntregasATiempo: TextView
    private lateinit var tvProyectosRetraso: TextView
    private lateinit var tvSinDocentes: TextView
    private lateinit var navDocentes: LinearLayout
    private lateinit var navProyectos: LinearLayout
    private lateinit var recyclerDocentes: RecyclerView
    private lateinit var adapterDocentes: RendimientoDocentesAdaptador
    private var idCoordinador: Int = 0
    private lateinit var presentador: pDashboardCoordinador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard_coordinador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        clsAjustesModelo.cargarLogoEn(findViewById(R.id.ivLogoSapiens))

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idCoordinador = intent.getIntExtra("id_coordinador", prefs.getInt("id_usuario", 0))


        btnPerfil = findViewById(R.id.btnPerfil)
        tvBienvenida = findViewById(R.id.tvBienvenida)
        tvTotalProyectos = findViewById(R.id.tvTotalProyectos)
        tvTotalDocentes = findViewById(R.id.tvTotalDocentes)
        tvEntregasATiempo = findViewById(R.id.tvEntregasATiempo)
        tvProyectosRetraso = findViewById(R.id.tvProyectosRetraso)
        tvSinDocentes = findViewById(R.id.tvSinDocentes)
        navDocentes = findViewById(R.id.navDocentes)
        navProyectos = findViewById(R.id.navProyectos)

        recyclerDocentes = findViewById(R.id.rvRendimientoDocentes)
        recyclerDocentes.layoutManager = LinearLayoutManager(this)
        adapterDocentes = RendimientoDocentesAdaptador(emptyList())
        recyclerDocentes.adapter = adapterDocentes

        presentador = pDashboardCoordinador(this)


        btnPerfil.setOnClickListener(this::ejecutarPerfil)
        navDocentes.setOnClickListener(this::ejecutarDocentes)
        navProyectos.setOnClickListener(this::ejecutarProyectos)
    }

    override fun onResume() {
        super.onResume()
        presentador.iniciar(idCoordinador)
    }

    fun ejecutarPerfil(v: View) {
        val intent = Intent(this, perfilUsuario::class.java)
        intent.putExtra("id_coordinador", idCoordinador)
        startActivity(intent)
    }

    fun ejecutarDocentes(v: View) {
        presentador.seleccionarDocentes()
    }

    fun ejecutarProyectos(v: View) {
        presentador.seleccionarProyectos()
    }


    fun mostrarBienvenida(nombre: String) {
        tvBienvenida.text = "Bienvenid@, " + nombre
    }

    fun mostrarResumen(totalProyectos: Int, totalDocentes: Int, porcentajeATiempo: Int, enRetraso: Int) {
        tvTotalProyectos.text = totalProyectos.toString()
        tvTotalDocentes.text = totalDocentes.toString()
        tvEntregasATiempo.text = porcentajeATiempo.toString() + "%"
        tvProyectosRetraso.text = enRetraso.toString()
    }

    fun mostrarRendimientoDocentes(docentes: List<dataDocenteRendimiento>) {
        if (docentes.isEmpty()) {
            tvSinDocentes.visibility = View.VISIBLE
        } else {
            tvSinDocentes.visibility = View.GONE
        }
        adapterDocentes.actualizarLista(docentes)
    }

    fun abrirDocentes() {
        val intent = Intent(this, docentesCoordinador::class.java)
        intent.putExtra("id_coordinador", idCoordinador)
        startActivity(intent)
    }

    fun abrirProyectos() {
        val intent = Intent(this, proyectosCoordinador::class.java)
        intent.putExtra("id_coordinador", idCoordinador)
        startActivity(intent)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}