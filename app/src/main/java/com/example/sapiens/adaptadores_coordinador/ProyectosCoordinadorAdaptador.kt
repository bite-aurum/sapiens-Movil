package com.example.sapiens.adaptadores_coordinador

import androidx.core.content.ContextCompat
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.coordinador.dataProyectoCoordinador
import com.example.sapiens.R

class ProyectosCoordinadorAdaptador(
    private var listaProyectos: List<dataProyectoCoordinador>
) : RecyclerView.Adapter<ProyectosCoordinadorAdaptador.ProyectoViewHolder>() {

    class ProyectoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtDocente: TextView = itemView.findViewById(R.id.txtDocente)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyectoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_proyecto_coordinador, parent, false)
        return ProyectoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProyectoViewHolder, position: Int) {
        val proyecto = listaProyectos[position]

        holder.txtNombre.text = proyecto.nombre
        holder.txtDocente.text = "Docente: " + proyecto.docente
        holder.txtEstado.text = proyecto.estado

        if (proyecto.estado == "En retraso") {
            holder.txtEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.rojoClaro))
            holder.txtEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.rojoTexto))
        } else {
            holder.txtEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
            holder.txtEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
        }
    }

    override fun getItemCount(): Int {
        return listaProyectos.size
    }

    fun actualizarLista(nuevaLista: List<dataProyectoCoordinador>) {
        listaProyectos = nuevaLista
        notifyDataSetChanged()
    }
}