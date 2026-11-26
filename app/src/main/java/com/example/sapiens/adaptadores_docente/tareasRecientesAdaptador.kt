package com.example.sapiens.adaptadores_docente

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataTareaReciente
import com.example.sapiens.R

class tareasRecientesAdaptador(private var listaTareas: List<dataTareaReciente>) : RecyclerView.Adapter<tareasRecientesAdaptador.TareaViewHolder>() {

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val divisor: View = itemView.findViewById(R.id.divisor)
        val tvTituloTarea: TextView = itemView.findViewById(R.id.tvTituloTarea)
        val tvFechaLimite: TextView = itemView.findViewById(R.id.tvFechaLimite)
        val tvPrioridad: TextView = itemView.findViewById(R.id.tvPrioridad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea_reciente, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listaTareas[position]

        holder.tvTituloTarea.text = tarea.titulo
        holder.tvFechaLimite.text = formatearFecha(tarea.fechaLimite)


        if (position == 0) {
            holder.divisor.visibility = View.GONE
        } else {
            holder.divisor.visibility = View.VISIBLE
        }


        holder.tvPrioridad.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_badge_prioridad)
        when (tarea.prioridad.lowercase()) {
            "alta" -> {
                holder.tvPrioridad.text = "Alta"
                holder.tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.rojoClaro))
                holder.tvPrioridad.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.rojoTexto))
            }
            "media" -> {
                holder.tvPrioridad.text = "Media"
                holder.tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
                holder.tvPrioridad.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
            }
            else -> {
                holder.tvPrioridad.text = "Baja"
                holder.tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
                holder.tvPrioridad.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
            }
        }
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }

    fun actualizarLista(nuevaLista: List<dataTareaReciente>) {
        listaTareas = nuevaLista
        notifyDataSetChanged()
    }


    private fun formatearFecha(fecha: String): String {
        val meses = listOf("ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic")
        val partes = fecha.split("-")
        if (partes.size < 3) {
            return fecha
        }
        val mes = partes[1].toInt()
        val dia = partes[2].toInt()
        return "Vence " + dia + " " + meses[mes - 1]
    }
}