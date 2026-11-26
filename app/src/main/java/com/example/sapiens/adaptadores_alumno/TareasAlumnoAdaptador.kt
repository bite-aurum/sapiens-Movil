package com.example.sapiens.adaptadores_alumno

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataTareaAlumno
import com.example.sapiens.R

class TareasAlumnoAdaptador(private var listaTareas: List<dataTareaAlumno>) : RecyclerView.Adapter<TareasAlumnoAdaptador.TareaViewHolder>() {

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val divisor: View = itemView.findViewById(R.id.divisor)
        val tvTituloTarea: TextView = itemView.findViewById(R.id.tvTituloTarea)
        val tvSubtituloTarea: TextView = itemView.findViewById(R.id.tvSubtituloTarea)
        val tvPrioridadTarea: TextView = itemView.findViewById(R.id.tvPrioridadTarea)
        val tvEstadoTarea: TextView = itemView.findViewById(R.id.tvEstadoTarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea_alumno, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listaTareas[position]

        holder.tvTituloTarea.text = tarea.titulo

        holder.tvSubtituloTarea.text = tarea.subtitulo


        if (position == 0) {
            holder.divisor.visibility = View.GONE
        } else {
            holder.divisor.visibility = View.VISIBLE
        }


        val prioridad = tarea.prioridad.lowercase()
        holder.tvPrioridadTarea.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_badge_prioridad)
        if (prioridad == "alta") {
            holder.tvPrioridadTarea.text = "Alta"
            holder.tvPrioridadTarea.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.rojoClaro))
            holder.tvPrioridadTarea.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.rojoTexto))
        } else if (prioridad == "media") {
            holder.tvPrioridadTarea.text = "Media"
            holder.tvPrioridadTarea.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
            holder.tvPrioridadTarea.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
        } else {
            holder.tvPrioridadTarea.text = "Baja"
            holder.tvPrioridadTarea.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
            holder.tvPrioridadTarea.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
        }


        val estado = tarea.estado.lowercase()
        holder.tvEstadoTarea.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_badge_estado)
        if (estado == "completada") {
            holder.tvEstadoTarea.text = "Completada"
            holder.tvEstadoTarea.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
            holder.tvEstadoTarea.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
        } else if (estado == "en progreso") {
            holder.tvEstadoTarea.text = "En progreso"
            holder.tvEstadoTarea.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeSuave))
            holder.tvEstadoTarea.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeAzul))
        } else {
            holder.tvEstadoTarea.text = "Pendiente"
            holder.tvEstadoTarea.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
            holder.tvEstadoTarea.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
        }
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }

    fun actualizarLista(nuevaLista: List<dataTareaAlumno>) {
        listaTareas = nuevaLista
        notifyDataSetChanged()
    }
}