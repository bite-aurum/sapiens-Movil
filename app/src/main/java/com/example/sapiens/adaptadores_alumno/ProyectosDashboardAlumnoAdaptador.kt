package com.example.sapiens.adaptadores_alumno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataProyectosAlumno
import com.example.sapiens.R
import com.example.sapiens.Vista.alumno.dashboardAlumno

class ProyectosDashboardAlumnoAdaptador(
    private var activity: dashboardAlumno,
    private var listaProyectos: List<dataProyectosAlumno>
) : RecyclerView.Adapter<ProyectosDashboardAlumnoAdaptador.ProyectoDashboardViewHolder>() {

    class ProyectoDashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreProyecto: TextView = itemView.findViewById(R.id.tvNombreProyectoDashboard)
        val pbProgreso: ProgressBar = itemView.findViewById(R.id.pbProgresoProyectoDashboard)
        val tvPorcentaje: TextView = itemView.findViewById(R.id.tvPorcentajeProyectoDashboard)
        val separador: View = itemView.findViewById(R.id.separadorProyectoDashboard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyectoDashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_proyecto_dashboard_alumno, parent, false)
        return ProyectoDashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProyectoDashboardViewHolder, position: Int) {
        val proyecto = listaProyectos[position]

        holder.tvNombreProyecto.text = proyecto.nombre
        holder.pbProgreso.progress = proyecto.avance
        holder.tvPorcentaje.text = proyecto.avance.toString() + "%"

        if (position == listaProyectos.size - 1) {
            holder.separador.visibility = View.GONE
        } else {
            holder.separador.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener {
            activity.seleccionarProyecto(proyecto)
        }
    }

    override fun getItemCount(): Int = listaProyectos.size

    fun actualizarLista(nuevaLista: List<dataProyectosAlumno>) {
        listaProyectos = nuevaLista
        notifyDataSetChanged()
    }
}