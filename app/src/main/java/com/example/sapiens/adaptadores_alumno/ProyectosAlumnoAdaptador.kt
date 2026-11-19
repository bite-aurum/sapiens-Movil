package com.example.sapiens.adaptadores_alumno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.alumno.dataProyectosAlumno
import com.example.sapiens.R
import com.example.sapiens.Vista.alumno.proyectosAlumno

class ProyectosAlumnoAdaptador(
    private val activity: proyectosAlumno,
    private var listaProyectos: List<dataProyectosAlumno>
) : RecyclerView.Adapter<ProyectosAlumnoAdaptador.ProyectoViewHolder>() {

    class ProyectoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstado)
        val txtDocente: TextView = itemView.findViewById(R.id.txtDocente)
        val barraAvance: ProgressBar = itemView.findViewById(R.id.barraAvance)
        val txtAvance: TextView = itemView.findViewById(R.id.txtAvance)
        val linea: View = itemView.findViewById(R.id.linea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyectoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_proyecto_alumno, parent, false)
        return ProyectoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProyectoViewHolder, position: Int) {
        val proyecto = listaProyectos[position]

        holder.txtNombre.text = proyecto.nombre
        holder.txtEstado.text = proyecto.estado
        holder.txtDocente.text = "Docente: " + proyecto.docente
        holder.barraAvance.progress = proyecto.avance
        holder.txtAvance.text = proyecto.avance.toString() + "%"

        if (position == listaProyectos.size - 1) {
            holder.linea.visibility = View.GONE
        } else {
            holder.linea.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                activity.seleccionarProyecto(proyecto)
            }
        })
    }

    override fun getItemCount(): Int {
        return listaProyectos.size
    }

    fun actualizarLista(nuevaLista: List<dataProyectosAlumno>) {
        listaProyectos = nuevaLista
        notifyDataSetChanged()
    }
}