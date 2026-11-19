package com.example.sapiens.Vista

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sapiens.R
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import android.text.method.PasswordTransformationMethod
import com.example.sapiens.Presentador.pPerfil
import com.example.sapiens.Vista.alumno.dashboardAlumno
import com.example.sapiens.Vista.alumno.misTareasAlumno
import com.example.sapiens.Vista.alumno.proyectosAlumno
import com.example.sapiens.Vista.coordinador.dashboardCoordinador
import com.example.sapiens.Vista.coordinador.docentesCoordinador
import com.example.sapiens.Vista.coordinador.proyectosCoordinador
import com.example.sapiens.Vista.docente.dashboardDocente
import com.example.sapiens.Vista.docente.notificacionesDocente
import com.example.sapiens.Vista.docente.proyectosDocente
import com.example.sapiens.Vista.docente.reportesDocente
import com.example.sapiens.Vista.docente.tareasDocente

class perfilUsuario : AppCompatActivity() {
    private lateinit var btnNotificaciones: ImageView
    private lateinit var txtNombrePerfil: TextView
    private lateinit var txtCambiarFoto: TextView
    private lateinit var edtNombre: EditText
    private lateinit var edtApellidos: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtContrasenaActual: EditText
    private lateinit var edtNuevaContrasena: EditText
    private lateinit var edtConfirmar: EditText
    private lateinit var imgVerActual: ImageView
    private lateinit var imgVerNueva: ImageView
    private lateinit var imgVerConfirmar: ImageView
    private lateinit var btnActualizar: Button
    private lateinit var chkTemaOscuro: CheckBox
    private lateinit var btnCerrarSesion: Button
    private lateinit var btnGuardarCambios: Button

    private lateinit var barraDocente: LinearLayout
    private lateinit var barraAlumno: LinearLayout
    private lateinit var barraCoordinador: LinearLayout
    private lateinit var navDocInicio: TextView
    private lateinit var navDocProyectos: TextView
    private lateinit var navDocTareas: TextView
    private lateinit var navDocReportes: TextView
    private lateinit var navAluInicio: TextView
    private lateinit var navAluProyectos: TextView
    private lateinit var navAluTareas: TextView
    private lateinit var navCooDashboard: TextView
    private lateinit var navCooDocentes: TextView
    private lateinit var navCooProyectos: TextView

    private var idUsuario: Int = 0
    private var rol: String = ""
    private lateinit var presentador: pPerfil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        idUsuario = prefs.getInt("id_usuario", 0)
        rol = prefs.getString("rol", "") ?: ""

        btnNotificaciones = findViewById(R.id.btnNotificaciones)
        txtNombrePerfil = findViewById(R.id.txtNombrePerfil)
        txtCambiarFoto = findViewById(R.id.txtCambiarFoto)
        edtNombre = findViewById(R.id.edtNombre)
        edtApellidos = findViewById(R.id.edtApellidos)
        edtCorreo = findViewById(R.id.edtCorreo)
        edtContrasenaActual = findViewById(R.id.edtContrasenaActual)
        edtNuevaContrasena = findViewById(R.id.edtNuevaContrasena)
        edtConfirmar = findViewById(R.id.edtConfirmar)
        imgVerActual = findViewById(R.id.imgVerActual)
        imgVerNueva = findViewById(R.id.imgVerNueva)
        imgVerConfirmar = findViewById(R.id.imgVerConfirmar)
        btnActualizar = findViewById(R.id.btnActualizar)
        chkTemaOscuro = findViewById(R.id.chkTemaOscuro)
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios)

        barraDocente = findViewById(R.id.barraDocente)
        barraAlumno = findViewById(R.id.barraAlumno)
        barraCoordinador = findViewById(R.id.barraCoordinador)
        navDocInicio = findViewById(R.id.navDocInicio)
        navDocProyectos = findViewById(R.id.navDocProyectos)
        navDocTareas = findViewById(R.id.navDocTareas)
        navDocReportes = findViewById(R.id.navDocReportes)
        navAluInicio = findViewById(R.id.navAluInicio)
        navAluProyectos = findViewById(R.id.navAluProyectos)
        navAluTareas = findViewById(R.id.navAluTareas)
        navCooDashboard = findViewById(R.id.navCooDashboard)
        navCooDocentes = findViewById(R.id.navCooDocentes)
        navCooProyectos = findViewById(R.id.navCooProyectos)

        presentador = pPerfil(this)

        btnNotificaciones.setOnClickListener(this::ejecutarNotificaciones)
        txtCambiarFoto.setOnClickListener(this::ejecutarCambiarFoto)
        imgVerActual.setOnClickListener(this::ejecutarVerContrasenaActual)
        imgVerNueva.setOnClickListener(this::ejecutarVerNuevaContrasena)
        imgVerConfirmar.setOnClickListener(this::ejecutarVerConfirmar)
        btnActualizar.setOnClickListener(this::ejecutarActualizar)
        chkTemaOscuro.setOnClickListener(this::ejecutarTemaOscuro)
        btnCerrarSesion.setOnClickListener(this::ejecutarCerrarSesion)
        btnGuardarCambios.setOnClickListener(this::ejecutarGuardarCambios)

        navDocInicio.setOnClickListener(this::ejecutarInicioDocente)
        navDocProyectos.setOnClickListener(this::ejecutarProyectosDocente)
        navDocTareas.setOnClickListener(this::ejecutarTareasDocente)
        navDocReportes.setOnClickListener(this::ejecutarReportesDocente)
        navAluInicio.setOnClickListener(this::ejecutarInicioAlumno)
        navAluProyectos.setOnClickListener(this::ejecutarProyectosAlumno)
        navAluTareas.setOnClickListener(this::ejecutarTareasAlumno)
        navCooDashboard.setOnClickListener(this::ejecutarDashboardCoordinador)
        navCooDocentes.setOnClickListener(this::ejecutarDocentesCoordinador)
        navCooProyectos.setOnClickListener(this::ejecutarProyectosCoordinador)

        chkTemaOscuro.isChecked = prefs.getBoolean("tema_oscuro", false)

        mostrarBarraPorRol()
        presentador.iniciar(rol, idUsuario)
    }

    fun ejecutarNotificaciones(v: View) {
        if (esDocente()) {
            abrir(notificacionesDocente::class.java)
        } else {
            Toast.makeText(this, "Notificaciones pendiente", Toast.LENGTH_SHORT).show()
        }
    }

    fun ejecutarCambiarFoto(v: View) {
        Toast.makeText(this, "Cambiar foto pendiente", Toast.LENGTH_SHORT).show()
    }


    fun ejecutarVerContrasenaActual(v: View) {
        alternarContrasena(edtContrasenaActual)
    }

    fun ejecutarVerNuevaContrasena(v: View) {
        alternarContrasena(edtNuevaContrasena)
    }

    fun ejecutarVerConfirmar(v: View) {
        alternarContrasena(edtConfirmar)
    }

    fun ejecutarGuardarCambios(v: View) {
        presentador.guardarCambios(
            rol,
            idUsuario,
            edtNombre.text.toString().trim(),
            edtApellidos.text.toString().trim(),
            edtCorreo.text.toString().trim()
        )
    }

    fun ejecutarActualizar(v: View) {
        presentador.cambiarContrasena(
            rol,
            idUsuario,
            edtNombre.text.toString().trim(),
            edtApellidos.text.toString().trim(),
            edtCorreo.text.toString().trim(),
            edtContrasenaActual.text.toString(),
            edtNuevaContrasena.text.toString(),
            edtConfirmar.text.toString()
        )
    }

    fun ejecutarTemaOscuro(v: View) {
        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE).edit()
        prefs.putBoolean("tema_oscuro", chkTemaOscuro.isChecked)
        prefs.apply()

        if (chkTemaOscuro.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun ejecutarCerrarSesion(v: View) {
        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE).edit()
        prefs.putBoolean("recordar_sesion", false)
        prefs.remove("id_usuario")
        prefs.remove("rol")
        prefs.remove("nombre")
        prefs.apply()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    fun ejecutarInicioDocente(v: View) {
        abrir(dashboardDocente::class.java)
    }

    fun ejecutarProyectosDocente(v: View) {
        abrir(proyectosDocente::class.java)
    }

    fun ejecutarTareasDocente(v: View) {
        abrir(tareasDocente::class.java)
    }

    fun ejecutarReportesDocente(v: View) {
        abrir(reportesDocente::class.java)
    }


    fun ejecutarInicioAlumno(v: View) {
        abrir(dashboardAlumno::class.java)
    }

    fun ejecutarProyectosAlumno(v: View) {
        abrir(proyectosAlumno::class.java)
    }

    fun ejecutarTareasAlumno(v: View) {
        abrir(misTareasAlumno::class.java)
    }


    fun ejecutarDashboardCoordinador(v: View) {
        abrir(dashboardCoordinador::class.java)
    }

    fun ejecutarDocentesCoordinador(v: View) {
        abrir(docentesCoordinador::class.java)
    }

    fun ejecutarProyectosCoordinador(v: View) {
        abrir(proyectosCoordinador::class.java)
    }


    fun mostrarPerfil(nombre: String, apellidos: String, correo: String) {
        txtNombrePerfil.text = nombre
        edtNombre.setText(nombre)
        edtApellidos.setText(apellidos)
        edtCorreo.setText(correo)
    }

    fun perfilActualizado(mensaje: String, nombre: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE).edit()
        prefs.putString("nombre", nombre)
        prefs.apply()

        txtNombrePerfil.text = nombre
        edtContrasenaActual.setText("")
        edtNuevaContrasena.setText("")
        edtConfirmar.setText("")
    }

    fun activarBotones(activar: Boolean) {
        btnActualizar.isEnabled = activar
        btnGuardarCambios.isEnabled = activar
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }


    private fun alternarContrasena(campo: EditText) {
        if (campo.transformationMethod == null) {
            campo.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            campo.transformationMethod = null
        }
        campo.setSelection(campo.text.length)
    }

    private fun esAlumno(): Boolean {
        val rolMinusculas = rol.lowercase()
        return rolMinusculas == "alumno" || rolMinusculas == "estudiante"
    }

    private fun esDocente(): Boolean {
        val rolMinusculas = rol.lowercase()
        return rolMinusculas == "docente" || rolMinusculas == "profesor"
    }

    private fun mostrarBarraPorRol() {
        if (esAlumno()) {
            barraAlumno.visibility = View.VISIBLE
        } else if (esDocente()) {
            barraDocente.visibility = View.VISIBLE
        } else {
            barraCoordinador.visibility = View.VISIBLE
        }
    }

    private fun abrir(destino: Class<*>) {
        val intent = Intent(this, destino)
        intent.putExtra("id_alumno", idUsuario)
        intent.putExtra("id_docente", idUsuario)
        intent.putExtra("id_coordinador", idUsuario)
        startActivity(intent)
    }
}