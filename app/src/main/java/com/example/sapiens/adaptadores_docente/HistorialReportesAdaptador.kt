package com.example.sapiens.adaptadores_docente

import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataReporteDocente
import com.example.sapiens.Vista.docente.reportesDocente
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import com.example.sapiens.R


class HistorialReportesAdaptador(
    private var activity: reportesDocente,
    private var listaReportes: List<dataReporteDocente>
) : RecyclerView.Adapter<HistorialReportesAdaptador.ReporteViewHolder>(){

    class ReporteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloReporte)
        val tvSubtitulo: TextView = itemView.findViewById(R.id.tvSubtituloReporte)
        val linea: View = itemView.findViewById(R.id.lineaReporte)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reporte_historial, parent, false)
        return ReporteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        val reporte = listaReportes[position]

        holder.tvTitulo.text = "Reporte de " + reporte.tipo

        var proyecto = reporte.proyecto ?: ""
        if (proyecto == "") {
            proyecto = "Todos los proyectos"
        }
        holder.tvSubtitulo.text = proyecto + " · " + formatearFecha(reporte.fecha)

        if (position == listaReportes.size - 1) {
            holder.linea.visibility = View.GONE
        } else {
            holder.linea.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener { activity.seleccionarReporte(reporte) }
    }

    override fun getItemCount(): Int {
        return listaReportes.size
    }

    fun actualizarLista(nuevaLista: List<dataReporteDocente>) {
        listaReportes = nuevaLista
        notifyDataSetChanged()
    }

    private fun formatearFecha(fecha: String): String {
        val partes = fecha.split("-")
        if (partes.size < 3) return fecha

        val meses = listOf("ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic")
        val mes = partes[1].toIntOrNull() ?: return fecha
        if (mes < 1 || mes > 12) return fecha

        return partes[2].take(2) + " " + meses[mes - 1]
    }
}