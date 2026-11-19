package com.example.sapiens.adaptadores_alumno

import android.content.res.ColorStateList
import android.graphics.Color
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
            holder.tvPrioridadTarea.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FCE4E4"))
            holder.tvPrioridadTarea.setTextColor(Color.parseColor("#C93B3B"))
        } else if (prioridad == "media") {
            holder.tvPrioridadTarea.text = "Media"
            holder.tvPrioridadTarea.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
            holder.tvPrioridadTarea.setTextColor(Color.parseColor("#9C7414"))
        } else {
            holder.tvPrioridadTarea.text = "Baja"
            holder.tvPrioridadTarea.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            holder.tvPrioridadTarea.setTextColor(Color.parseColor("#1B7F4B"))
        }


        val estado = tarea.estado.lowercase()
        holder.tvEstadoTarea.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_badge_estado)
        if (estado == "completada") {
            holder.tvEstadoTarea.text = "Completada"
            holder.tvEstadoTarea.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            holder.tvEstadoTarea.setTextColor(Color.parseColor("#1B7F4B"))
        } else if (estado == "en progreso") {
            holder.tvEstadoTarea.text = "En progreso"
            holder.tvEstadoTarea.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E3F2EE"))
            holder.tvEstadoTarea.setTextColor(Color.parseColor("#0D7A6B"))
        } else {
            holder.tvEstadoTarea.text = "Pendiente"
            holder.tvEstadoTarea.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
            holder.tvEstadoTarea.setTextColor(Color.parseColor("#9C7414"))
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