package com.example.sapiens.adaptadores_docente

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataTarea
import com.example.sapiens.R

class TareasDocenteAdapter(
    private var tareasAgrupadas: Map<String, List<dataTarea>> = emptyMap(),
    private val alSeleccionarTarea: (dataTarea) -> Unit
) : RecyclerView.Adapter<TareasDocenteAdapter.ViewHolder>() {

    private var listaProyectos = tareasAgrupadas.keys.toList()

    class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        val tvNombreProyecto: TextView = vista.findViewById(R.id.tvNombreProyectoGrupo)
        val contenedorTareas: LinearLayout = vista.findViewById(R.id.containerTareasGrupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_proyecto_grupo_tareas, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nombreProyecto = listaProyectos[position]
        holder.tvNombreProyecto.text = nombreProyecto

        val tareasDelProyecto = tareasAgrupadas[nombreProyecto] ?: emptyList()
        holder.contenedorTareas.removeAllViews()

        val inflador = LayoutInflater.from(holder.itemView.context)

        tareasDelProyecto.forEachIndexed { indice, tarea ->
            val filaTarea = inflador.inflate(R.layout.item_sub_tarea_simple, holder.contenedorTareas, false)

            val tvTitulo: TextView = filaTarea.findViewById(R.id.tvTituloSub)
            val tvPrioridad: TextView = filaTarea.findViewById(R.id.tvPrioridadSub)
            val tvEstado: TextView = filaTarea.findViewById(R.id.tvEstadoSub)
            val separador: View = filaTarea.findViewById(R.id.dividerSub)

            tvTitulo.text = tarea.titulo

            tvPrioridad.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_badge_prioridad)
            when (tarea.prioridad.lowercase()) {
                "alta" -> {
                    tvPrioridad.text = "Alta"
                    tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.rojoClaro))
                    tvPrioridad.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.rojoTexto))
                }
                "media" -> {
                    tvPrioridad.text = "Media"
                    tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
                    tvPrioridad.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
                }
                else -> {
                    tvPrioridad.text = "Baja"
                    tvPrioridad.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
                    tvPrioridad.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
                }
            }


            tvEstado.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_badge_estado)
            when (tarea.estado.lowercase()) {
                "completada", "completado" -> {
                    tvEstado.text = "Completada"
                    tvEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeChip))
                    tvEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeTexto))
                }
                "en progreso" -> {
                    tvEstado.text = "En progreso"
                    tvEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.verdeSuave))
                    tvEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.verdeAzul))
                }
                else -> {
                    tvEstado.text = "Pendiente"
                    tvEstado.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.context, R.color.amarilloClaro))
                    tvEstado.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.amarillo))
                }
            }

            separador.visibility = if (indice == tareasDelProyecto.size - 1) View.GONE else View.VISIBLE

            filaTarea.setOnClickListener { alSeleccionarTarea(tarea) }
            holder.contenedorTareas.addView(filaTarea)
        }
    }

    override fun getItemCount(): Int = listaProyectos.size

    fun actualizarTareas(tareas: List<dataTarea>) {
        tareasAgrupadas = tareas.groupBy { it.proyecto }
        listaProyectos = tareasAgrupadas.keys.toList()
        notifyDataSetChanged()
    }
}