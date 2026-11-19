package com.example.sapiens.adaptadores_docente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.SubtareaTemporal
import com.example.sapiens.R
import com.example.sapiens.Vista.docente.nuevaTareaDocente

class SubtareasAgregadasAdaptador(
    private var activity: nuevaTareaDocente,
    private var listaSubtareas: List<SubtareaTemporal>
) : RecyclerView.Adapter<SubtareasAgregadasAdaptador.SubtareaViewHolder>() {

    class SubtareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloSubtareaItem)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionSubtareaItem)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminarSubtareaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subtarea_agregada, parent, false)
        return SubtareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubtareaViewHolder, position: Int) {
        val subtarea = listaSubtareas[position]

        holder.tvTitulo.text = subtarea.titulo
        if (subtarea.descripcion.isEmpty()) {
            holder.tvDescripcion.visibility = View.GONE
        } else {
            holder.tvDescripcion.visibility = View.VISIBLE
            holder.tvDescripcion.text = subtarea.descripcion
        }

        holder.btnEliminar.setOnClickListener {
            activity.eliminarSubtarea(position)
        }
    }

    override fun getItemCount(): Int = listaSubtareas.size

    fun actualizarLista(nuevaLista: List<SubtareaTemporal>) {
        listaSubtareas = nuevaLista
        notifyDataSetChanged()
    }
}