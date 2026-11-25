package com.example.sapiens.Modelo

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sapiens.Network.RetrofitCliente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsAjustesModelo {

    companion object {
        private var logoCache: String? = null

        fun cargarLogoEn(imageView: ImageView) {
            val cache = logoCache
            if (!cache.isNullOrEmpty()) {
                Glide.with(imageView.context)
                    .load(RetrofitCliente.SERVIDOR + "img/" + cache)
                    .into(imageView)
                return
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val nombreLogo = RetrofitCliente.apiService.obtenerAjustes().logo
                    if (nombreLogo.isNotEmpty()) {
                        logoCache = nombreLogo
                        Glide.with(imageView.context)
                            .load(RetrofitCliente.SERVIDOR + "img/" + nombreLogo)
                            .into(imageView)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    suspend fun obtenerLogoApp(): String {
        val respuesta = RetrofitCliente.apiService.obtenerAjustes()
        logoCache = respuesta.logo
        return respuesta.logo
    }
}