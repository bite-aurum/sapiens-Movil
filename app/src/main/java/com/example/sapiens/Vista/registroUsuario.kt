package com.example.sapiens.Vista

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sapiens.R
import com.example.sapiens.Presentador.pRegistro
import android.widget.*
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.example.sapiens.Modelo.clsAjustesModelo

class registroUsuario : AppCompatActivity() {

    private lateinit var presenter: pRegistro
    private lateinit var spnRol: Spinner
    private lateinit var edtNombre: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var edtContrasena: EditText
    private lateinit var edtConfirmar: EditText
    private lateinit var imgVerContrasena: ImageView
    private lateinit var imgVerConfirmar: ImageView
    private lateinit var btnRegistrarme: Button
    private lateinit var tvIniciarSesion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        clsAjustesModelo.cargarLogoEn(findViewById(R.id.ivLogoSapiens))

        spnRol = findViewById(R.id.spnRol)
        edtNombre = findViewById(R.id.edtNombreCompleto)
        edtCorreo = findViewById(R.id.edtCorreoRegistro)
        edtContrasena = findViewById(R.id.edtContrasenaRegistro)
        edtConfirmar = findViewById(R.id.edtConfirmarContrasena)
        imgVerContrasena = findViewById(R.id.imgVerContrasenaRegistro)
        imgVerConfirmar = findViewById(R.id.imgVerConfirmarRegistro)
        btnRegistrarme = findViewById(R.id.btnRegistrarme)
        tvIniciarSesion = findViewById(R.id.tvIniciarSesion)

        presenter = pRegistro(this)

        btnRegistrarme.setOnClickListener(this::ejecutarRegistro)
        tvIniciarSesion.setOnClickListener { finish() }
        imgVerContrasena.setOnClickListener(this::ejecutarVerContrasena)
        imgVerConfirmar.setOnClickListener(this::ejecutarVerConfirmar)
    }

    fun ejecutarRegistro(v: View) {
        presenter.registrar(
            spnRol.selectedItem.toString().lowercase(),
            edtNombre.text.toString().trim(),
            edtCorreo.text.toString().trim(),
            edtContrasena.text.toString(),
            edtConfirmar.text.toString()
        )
    }

    fun ejecutarVerContrasena(v: View) {
        alternarContrasena(edtContrasena, imgVerContrasena)
    }

    fun ejecutarVerConfirmar(v: View) {
        alternarContrasena(edtConfirmar, imgVerConfirmar)
    }

    fun activarBoton(activar: Boolean) {
        btnRegistrarme.isEnabled = activar
    }

    fun registroExitoso(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun alternarContrasena(campo: EditText, icono: ImageView) {
        if (campo.transformationMethod == null) {
            campo.transformationMethod = PasswordTransformationMethod.getInstance()
            icono.setImageResource(R.drawable.ic_ver)
        } else {
            campo.transformationMethod = null
            icono.setImageResource(R.drawable.ic_ver_oculto)
        }
        campo.setSelection(campo.text.length)
    }
}
