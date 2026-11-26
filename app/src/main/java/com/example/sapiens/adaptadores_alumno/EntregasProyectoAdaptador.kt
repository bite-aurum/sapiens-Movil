package com.example.sapiens.adaptadores_alumno

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataEntregaProyecto
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sapiens.R
import android.content.res.ColorStateList

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
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
            holder.tvEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
        } else {
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
            holder.tvEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
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