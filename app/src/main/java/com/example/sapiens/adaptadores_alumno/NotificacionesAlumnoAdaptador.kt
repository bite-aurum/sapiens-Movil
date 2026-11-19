package com.example.sapiens.adaptadores_alumno

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataNotificacionesAlumno
import com.example.sapiens.R

class NotificacionesAlumnoAdaptador(private var listaNotificaciones: List<dataNotificacionesAlumno>) : RecyclerView.Adapter<NotificacionesAlumnoAdaptador.NotificacionViewHolder>() {

    class NotificacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val divisor: View = itemView.findViewById(R.id.divisor)
        val tvTipoNotificacion: TextView = itemView.findViewById(R.id.tvTipoNotificacion)
        val tvMensajeNotificacion: TextView = itemView.findViewById(R.id.tvMensajeNotificacion)
        val tvDetalleNotificacion: TextView = itemView.findViewById(R.id.tvDetalleNotificacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificacionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notificacion_alumno, parent, false)
        return NotificacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificacionViewHolder, position: Int) {
        val notificacion = listaNotificaciones[position]

        holder.tvMensajeNotificacion.text = notificacion.mensaje


        val fecha = formatearFecha(notificacion.fecha)
        if (notificacion.remitente == "") {
            holder.tvDetalleNotificacion.text = fecha
        } else {
            holder.tvDetalleNotificacion.text = "De: " + notificacion.remitente + " · " + fecha
        }


        if (position == 0) {
            holder.divisor.visibility = View.GONE
        } else {
            holder.divisor.visibility = View.VISIBLE
        }

        val tipo = notificacion.tipo.lowercase()
        holder.tvTipoNotificacion.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_badge_prioridad)
        if (tipo == "recordatorio") {
            holder.tvTipoNotificacion.text = "Recordatorio"
            holder.tvTipoNotificacion.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
            holder.tvTipoNotificacion.setTextColor(Color.parseColor("#9C7414"))
        } else if (tipo == "entrega") {
            holder.tvTipoNotificacion.text = "Entrega"
            holder.tvTipoNotificacion.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            holder.tvTipoNotificacion.setTextColor(Color.parseColor("#1B7F4B"))
        } else {
            holder.tvTipoNotificacion.text = "Aviso"
            holder.tvTipoNotificacion.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F3EFE6"))
            holder.tvTipoNotificacion.setTextColor(Color.parseColor("#1F7A63"))
        }
    }

    override fun getItemCount(): Int {
        return listaNotificaciones.size
    }

    fun actualizarLista(nuevaLista: List<dataNotificacionesAlumno>) {
        listaNotificaciones = nuevaLista
        notifyDataSetChanged()
    }


    private fun formatearFecha(fecha: String): String {
        val meses = listOf("ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic")
        val partes = fecha.split("-")
        if (partes.size < 3) {
            return fecha
        }
        val mes = partes[1].toInt()
        if (mes < 1 || mes > 12) {
            return fecha
        }
        return partes[2] + " " + meses[mes - 1]
    }
}