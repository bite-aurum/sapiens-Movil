package com.example.sapiens.adaptadores_alumno

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataEquipoProyecto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sapiens.R
import android.content.res.ColorStateList


class EquipoProyectoAdaptador(
    private var listaEquipo: List<dataEquipoProyecto>
): RecyclerView.Adapter<EquipoProyectoAdaptador.EquipoViewHolder>(){

    class EquipoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreAlumno)
        val tvRol: TextView = itemView.findViewById(R.id.tvRolAlumno)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipo_proyecto, parent, false)
        return EquipoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val alumno = listaEquipo[position]

        holder.tvNombre.text = alumno.nombre
        holder.tvRol.text = alumno.rol

        if (alumno.rol == "Líder") {
            holder.tvRol.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
            holder.tvRol.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeAzul))
        } else {
            holder.tvRol.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.grisChip))
            holder.tvRol.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.grisMedio))
        }
    }

    override fun getItemCount(): Int {
        return listaEquipo.size
    }

    fun actualizarLista(nuevaLista: List<dataEquipoProyecto>) {
        listaEquipo = nuevaLista
        notifyDataSetChanged()
    }
}