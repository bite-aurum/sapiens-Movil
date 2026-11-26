package com.example.sapiens.adaptadores_alumno

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataTareaProyecto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sapiens.R
import android.content.res.ColorStateList

class TareasProyectoAdaptador (
    private var listaTareas: List<dataTareaProyecto>
): RecyclerView.Adapter<TareasProyectoAdaptador.TareaViewHolder>(){

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloFila)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstadoFila)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estado_proyecto, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listaTareas[position]

        holder.tvTitulo.text = tarea.titulo
        holder.tvEstado.text = tarea.estado

        if (tarea.estado.lowercase().startsWith("complet")) {
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
            holder.tvEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
        } else {
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
            holder.tvEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
        }
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }

    fun actualizarLista(nuevaLista: List<dataTareaProyecto>) {
        listaTareas = nuevaLista
        notifyDataSetChanged()
    }
}