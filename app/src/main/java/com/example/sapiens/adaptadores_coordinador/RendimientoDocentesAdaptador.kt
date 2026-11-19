package com.example.sapiens.adaptadores_coordinador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.coordinador.dataDocenteRendimiento
import com.example.sapiens.R

class RendimientoDocentesAdaptador(private var listaDocentes: List<dataDocenteRendimiento>) : RecyclerView.Adapter<RendimientoDocentesAdaptador.DocenteViewHolder>() {

    class DocenteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val divisor: View = itemView.findViewById(R.id.divisor)
        val tvNombreDocente: TextView = itemView.findViewById(R.id.tvNombreDocente)
        val tvRendimiento: TextView = itemView.findViewById(R.id.tvRendimiento)
        val barraRendimiento: ProgressBar = itemView.findViewById(R.id.barraRendimiento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocenteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_docente_coordinador, parent, false)
        return DocenteViewHolder(view)
    }

    override fun onBindViewHolder(holder: DocenteViewHolder, position: Int) {
        val docente = listaDocentes[position]

        holder.tvNombreDocente.text = docente.nombre + " " + docente.apellidoPaterno
        holder.tvRendimiento.text = docente.rendimiento.toString() + "%"
        holder.barraRendimiento.progress = docente.rendimiento

        if (position == 0) {
            holder.divisor.visibility = View.GONE
        } else {
            holder.divisor.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return listaDocentes.size
    }

    fun actualizarLista(nuevaLista: List<dataDocenteRendimiento>) {
        listaDocentes = nuevaLista
        notifyDataSetChanged()
    }
}