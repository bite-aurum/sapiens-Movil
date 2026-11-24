package com.example.sapiens.adaptadores_alumno

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataSubtareaAlumno
import com.example.sapiens.R

class SubtareasAlumnoAdaptador(
    private var listaSubtareas: List<dataSubtareaAlumno>
) : RecyclerView.Adapter<SubtareasAlumnoAdaptador.SubtareaViewHolder>() {

    class SubtareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTituloSubtarea)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstadoSubtarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subtarea_alumno, parent, false)
        return SubtareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubtareaViewHolder, position: Int) {
        val subtarea = listaSubtareas[position]

        holder.txtTitulo.text = subtarea.titulo
        holder.txtEstado.text = subtarea.estado

        if (subtarea.estado == "Completada") {
            holder.txtEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            holder.txtEstado.setTextColor(Color.parseColor("#1B7F4B"))
        } else {
            holder.txtEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
            holder.txtEstado.setTextColor(Color.parseColor("#9C7414"))
        }
    }

    override fun getItemCount(): Int {
        return listaSubtareas.size
    }

    fun actualizarLista(nuevaLista: List<dataSubtareaAlumno>) {
        listaSubtareas = nuevaLista
        notifyDataSetChanged()
    }
}