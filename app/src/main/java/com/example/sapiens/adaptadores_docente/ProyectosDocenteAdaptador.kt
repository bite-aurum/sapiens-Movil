package com.example.sapiens.adaptadores_docente

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataProyectosDocente
import com.example.sapiens.R
import com.example.sapiens.Vista.docente.proyectosDocente

class ProyectosDocenteAdaptador(private val activity: proyectosDocente, private var listaProyectos: List<dataProyectosDocente>): RecyclerView.Adapter<ProyectosDocenteAdaptador.ProyectoViewHolder>() {
    class ProyectoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstado)
        val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val barraAvance: ProgressBar = itemView.findViewById(R.id.barraAvance)
        val txtAvance: TextView = itemView.findViewById(R.id.txtAvance)
        val btnAlumnos: TextView = itemView.findViewById(R.id.btnAlumnos)
        val btnTareas: TextView = itemView.findViewById(R.id.btnTareas)
        val btnEntregas: TextView = itemView.findViewById(R.id.btnEntregas)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProyectoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_proyecto_docente, parent, false)
        return ProyectoViewHolder(view)
    }
    override fun onBindViewHolder(holder: ProyectoViewHolder, position: Int) {
        val proyecto = listaProyectos[position]

        holder.txtNombre.text = proyecto.nombre
        holder.txtEstado.text = proyecto.estado
        holder.txtDescripcion.text = proyecto.descripcion
        holder.barraAvance.progress = proyecto.avance
        holder.txtAvance.text = proyecto.avance.toString() + "%"

        holder.btnAlumnos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                activity.seleccionarAlumnos(proyecto)
            }
        })
        holder.btnTareas.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                activity.seleccionarTareas(proyecto)
            }
        })
        holder.btnEntregas.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                activity.seleccionarEntregas(proyecto)
            }
        })
    }

    override fun getItemCount(): Int {
        return listaProyectos.size
    }

    fun actualizarLista(nuevaLista: List<dataProyectosDocente>) {
        listaProyectos = nuevaLista
        notifyDataSetChanged()
    }
}