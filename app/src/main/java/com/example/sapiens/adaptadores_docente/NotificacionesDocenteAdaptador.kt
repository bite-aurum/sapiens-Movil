package com.example.sapiens.Vista.docente.Adaptador

import androidx.core.content.ContextCompat
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataNotificacionesEnviadas
import com.example.sapiens.R
import com.example.sapiens.Vista.docente.notificacionesDocente

class NotificacionesDocenteAdaptador(
    private var activity: notificacionesDocente,
    private var listaNotificaciones: List<dataNotificacionesEnviadas>
) : RecyclerView.Adapter<NotificacionesDocenteAdaptador.NotificacionViewHolder>() {

    class NotificacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBadge: TextView = itemView.findViewById(R.id.tvBadgeTipoNotif)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstadoNotif)
        val tvMensaje: TextView = itemView.findViewById(R.id.tvMensajeNotif)
        val tvDetalle: TextView = itemView.findViewById(R.id.tvDetalleNotif)
        val separador: View = itemView.findViewById(R.id.separadorNotif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notificacion_docente, parent, false)
        return NotificacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificacionViewHolder, position: Int) {
        val item = listaNotificaciones[position]

        val tipoMinus = item.tipo.lowercase()
        if (tipoMinus == "recordatorio") {
            holder.tvBadge.text = "Recordatorio"
            holder.tvBadge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
            holder.tvBadge.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
        } else if (tipoMinus == "entrega") {
            holder.tvBadge.text = "Entrega"
            holder.tvBadge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
            holder.tvBadge.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
        } else if (tipoMinus == "alerta") {
            holder.tvBadge.text = "Alerta"
            holder.tvBadge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.rojoClaro))
            holder.tvBadge.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.rojoTexto))
        } else {
            holder.tvBadge.text = item.tipo.ifEmpty { "Aviso" }
            holder.tvBadge.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.fondoChip))
            holder.tvBadge.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.grisOscuro))
        }

        holder.tvEstado.text = "Enviado"
        holder.tvMensaje.text = item.mensaje
        holder.tvDetalle.text = "Para: " + item.alumno + " · " + item.fecha

        if (position == listaNotificaciones.size - 1) {
            holder.separador.visibility = View.GONE
        } else {
            holder.separador.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = listaNotificaciones.size

    fun actualizarLista(nuevaLista: List<dataNotificacionesEnviadas>) {
        listaNotificaciones = nuevaLista
        notifyDataSetChanged()
    }
}