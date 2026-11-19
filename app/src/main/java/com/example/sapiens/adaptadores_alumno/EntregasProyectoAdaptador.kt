package com.example.sapiens.adaptadores_alumno

import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataEntregaProyecto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sapiens.R
import android.content.res.ColorStateList
import android.graphics.Color

class EntregasProyectoAdaptador(
    private var listaEntregas: List<dataEntregaProyecto>
): RecyclerView.Adapter<EntregasProyectoAdaptador.EntregaViewHolder>(){

    class EntregaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloFila)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstadoFila)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntregaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estado_proyecto, parent, false)
        return EntregaViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntregaViewHolder, position: Int) {
        val entrega = listaEntregas[position]

        holder.tvTitulo.text = entrega.titulo
        holder.tvEstado.text = entrega.estado

        if (entrega.estado == "Entregado") {
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            holder.tvEstado.setTextColor(Color.parseColor("#1B7F4B"))
        } else {
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
            holder.tvEstado.setTextColor(Color.parseColor("#9C7414"))
        }
    }

    override fun getItemCount(): Int {
        return listaEntregas.size
    }

    fun actualizarLista(nuevaLista: List<dataEntregaProyecto>) {
        listaEntregas = nuevaLista
        notifyDataSetChanged()
    }
}