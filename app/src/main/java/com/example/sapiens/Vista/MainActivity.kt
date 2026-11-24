package com.example.sapiens.Vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sapiens.Modelo.LoginResponse
import com.example.sapiens.Presentador.sapiensPresentador
import com.example.sapiens.R
import com.example.sapiens.Vista.alumno.dashboardAlumno
import com.example.sapiens.Vista.coordinador.dashboardCoordinador
import com.example.sapiens.Vista.docente.dashboardDocente
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var cbRecordar: CheckBox
    private lateinit var btnIniciarSesion: MaterialButton
    private lateinit var tvCrearCuenta: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var presentador: sapiensPresentador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE)
        if (prefs.getBoolean("recordar_sesion", false)) {
            val rolGuardado = prefs.getString("rol", "") ?: ""
            val idGuardado = prefs.getInt("id_usuario", 0)
            if (rolGuardado.isNotEmpty() && idGuardado > 0) {
                abrirPanelPorRol(rolGuardado, idGuardado)
                return
            }
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etCorreo = findViewById(R.id.etCorreoLogin)
        etContrasena = findViewById(R.id.etContrasenaLogin)
        cbRecordar = findViewById(R.id.cbRecordarSesion)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        tvCrearCuenta = findViewById(R.id.tvCrearCuenta)
        progressBar = findViewById(R.id.progressBarLogin)

        presentador = sapiensPresentador(this)

        btnIniciarSesion.setOnClickListener(this::ejecutarIniciarSesion)
        tvCrearCuenta.setOnClickListener(this::ejecutarCrearCuenta)
    }

    fun ejecutarIniciarSesion(v: View) {
        val correo = etCorreo.text.toString().trim()
        val contrasena = etContrasena.text.toString().trim()
        presentador.iniciarSesion(correo, contrasena)
    }

    fun ejecutarCrearCuenta(v: View) {
        startActivity(Intent(this, registroUsuario::class.java))
    }

    fun mostrarCargando(mostrar: Boolean) {
        progressBar.visibility = if (mostrar) View.VISIBLE else View.GONE
        btnIniciarSesion.isEnabled = !mostrar
    }

    fun procesarRespuestaLogin(respuesta: LoginResponse) {
        if (respuesta.exito) {
            val prefs = getSharedPreferences("SapiensPrefs", MODE_PRIVATE).edit()
            prefs.putInt("id_usuario", respuesta.id)
            prefs.putString("rol", respuesta.rol)
            prefs.putString("nombre", respuesta.nombre)
            prefs.putBoolean("recordar_sesion", cbRecordar.isChecked)
            prefs.apply()

            abrirPanelPorRol(respuesta.rol, respuesta.id)
        } else {
            mostrarError(respuesta.mensaje.ifEmpty { "Correo o contraseña incorrectos" })
        }
    }

    private fun abrirPanelPorRol(rol: String, idUsuario: Int) {
        val rolMinusculas = rol.lowercase()

        if (rolMinusculas == "alumno" || rolMinusculas == "estudiante") {
            val intent = Intent(this, dashboardAlumno::class.java)
            intent.putExtra("id_alumno", idUsuario)
            startActivity(intent)
            finish()
        } else if (rolMinusculas == "docente" || rolMinusculas == "profesor") {
            val intent = Intent(this, dashboardDocente::class.java)
            intent.putExtra("id_docente", idUsuario)
            startActivity(intent)
            finish()
        } else if (rolMinusculas == "coordinador" || rolMinusculas == "admin" || rolMinusculas == "administrador") {
            val intent = Intent(this, dashboardCoordinador::class.java)
            intent.putExtra("id_coordinador", idUsuario)
            startActivity(intent)
            finish()
        } else {
            mostrarError("Rol no reconocido: " + rol)
        }
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}