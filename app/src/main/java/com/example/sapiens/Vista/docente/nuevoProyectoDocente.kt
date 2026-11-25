package com.example.sapiens.Vista.docente

import com.example.sapiens.Modelo.clsAjustesModelo
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.adaptadores_docente.AdaptadorAlumnosProyecto
import com.example.sapiens.Modelo.docente.dataAlumno
import com.example.sapiens.Presentador.docente.pNuevoProyectoDocente
import com.example.sapiens.R
import java.util.Calendar

class nuevoProyectoDocente : AppCompatActivity() {

    private lateinit var presenter: pNuevoProyectoDocente
    private lateinit var adaptador: AdaptadorAlumnosProyecto

    private lateinit var edtNombre: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var edtProblematica: EditText
    private lateinit var edtObjetivos: EditText
    private lateinit var edtFechaInicio: EditText
    private lateinit var edtFechaFin: EditText
    private lateinit var edtCorreoAlumno: EditText
    private lateinit var btnAgregarAlumno: Button
    private lateinit var rvAlumnos: RecyclerView
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button


    private var idDocente: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nuevo_proyecto_docente)
        clsAjustesModelo.cargarLogoEn(findViewById(R.id.ivLogoSapiens))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNombre = findViewById(R.id.edtNombreProyecto)
        edtDescripcion = findViewById(R.id.edtDescripcion)
        edtProblematica = findViewById(R.id.edtProblematica)
        edtObjetivos = findViewById(R.id.edtObjetivos)
        edtFechaInicio = findViewById(R.id.edtFechaInicio)
        edtFechaFin = findViewById(R.id.edtFechaFin)
        edtCorreoAlumno = findViewById(R.id.edtCorreoAlumno)
        btnAgregarAlumno = findViewById(R.id.btnBuscarAlumno)
        rvAlumnos = findViewById(R.id.rvAlumnos)
        btnGuardar = findViewById(R.id.btnGuardarProyecto)
        btnCancelar = findViewById(R.id.btnCancelar)

        rvAlumnos.layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorAlumnosProyecto(this, emptyList())
        rvAlumnos.adapter = adaptador

        presenter = pNuevoProyectoDocente(this)

        idDocente = intent.getIntExtra("ID_DOCENTE", 0)

        btnAgregarAlumno.setOnClickListener(this::ejecutarBuscarAlumno)
        btnGuardar.setOnClickListener(this::ejecutarGuardar)
        btnCancelar.setOnClickListener { finish() }
        edtFechaInicio.setOnClickListener { mostrarDatePicker(edtFechaInicio) }
        edtFechaFin.setOnClickListener { mostrarDatePicker(edtFechaFin) }
    }

    fun ejecutarBuscarAlumno(v: View) {
        presenter.buscarAlumno(edtCorreoAlumno.text.toString().trim())
    }

    fun ejecutarGuardar(v: View) {
        presenter.guardarProyecto(
            idDocente,
            edtNombre.text.toString().trim(),
            edtDescripcion.text.toString().trim(),
            edtProblematica.text.toString().trim(),
            edtObjetivos.text.toString().trim(),
            edtFechaInicio.text.toString().trim(),
            edtFechaFin.text.toString().trim()
        )
    }


    fun quitarAlumno(posicion: Int) {
        presenter.quitarAlumno(posicion)
    }

    fun mostrarAlumnos(alumnos: List<dataAlumno>) {
        adaptador.actualizarLista(alumnos)
    }

    fun limpiarCorreo() {
        edtCorreoAlumno.setText("")
    }

    fun activarBotones(activar: Boolean) {
        btnGuardar.isEnabled = activar
        btnAgregarAlumno.isEnabled = activar
    }

    fun proyectoGuardado(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        setResult(Activity.RESULT_OK)
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun mostrarDatePicker(campo: EditText) {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val dialogo = DatePickerDialog(this, { _, year, month, day ->

            val fecha = String.format("%04d-%02d-%02d", year, month + 1, day)
            campo.setText(fecha)
        }, anio, mes, dia)
        dialogo.show()
    }
}
