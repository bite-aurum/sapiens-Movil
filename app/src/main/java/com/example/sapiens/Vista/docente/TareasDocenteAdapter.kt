package com.example.sapiens.Vista.docente

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataTarea
import com.example.sapiens.R

class TareasDocenteAdapter(
    private var lista: List<dataTarea>,
    private val onTareaClick: (dataTarea) -> Unit
) : RecyclerView.Adapter<TareasDocenteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitulo: TextView = view.findViewById(R.id.tvTituloItemTarea)
        val tvSubtitulo: TextView = view.findViewById(R.id.tvSubtituloItemTarea)
        val tvPrioridad: TextView = view.findViewById(R.id.tvPrioridadItemTarea)
        val tvEstado: TextView = view.findViewById(R.id.tvEstadoItemTarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea_docente, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        val context = holder.itemView.context

        holder.tvTitulo.text = item.titulo
        holder.tvSubtitulo.text = if (item.fechaLimite.isNotEmpty()) {
            "${item.proyecto} · Vence ${item.fechaLimite}"
        } else {
            item.proyecto
        }

        holder.tvPrioridad.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_prioridad)
        when (item.prioridad.lowercase()) {
            "alta" -> {
                holder.tvPrioridad.text = "Alta"
                holder.tvPrioridad.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FCE4E4"))
                holder.tvPrioridad.setTextColor(Color.parseColor("#C93B3B"))
            }
            "media" -> {
                holder.tvPrioridad.text = "Media"
                holder.tvPrioridad.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
                holder.tvPrioridad.setTextColor(Color.parseColor("#9C7414"))
            }
            else -> {
                holder.tvPrioridad.text = "Baja"
                holder.tvPrioridad.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
                holder.tvPrioridad.setTextColor(Color.parseColor("#1B7F4B"))
            }
        }

        holder.tvEstado.background = ContextCompat.getDrawable(context, R.drawable.bg_badge_estado)
        when (item.estado.lowercase()) {
            "completada", "completado" -> {
                holder.tvEstado.text = "Completada"
                holder.tvEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
                holder.tvEstado.setTextColor(Color.parseColor("#1B7F4B"))
            }
            "en progreso" -> {
                holder.tvEstado.text = "En progreso"
                holder.tvEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E3F2EE"))
                holder.tvEstado.setTextColor(Color.parseColor("#0D7A6B"))
            }
            else -> {
                holder.tvEstado.text = "Pendiente"
                holder.tvEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
                holder.tvEstado.setTextColor(Color.parseColor("#9C7414"))
            }
        }

        holder.itemView.setOnClickListener { onTareaClick(item) }
    }

    override fun getItemCount(): Int = lista.size

    fun actualizarLista(nuevaLista: List<dataTarea>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}