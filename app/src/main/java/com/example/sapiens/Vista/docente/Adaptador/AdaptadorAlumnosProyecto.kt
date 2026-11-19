package com.example.sapiens.Vista.docente.Adaptador

import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataAlumno
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.view.ViewGroup
import com.example.sapiens.R
import com.example.sapiens.Vista.docente.nuevoProyectoDocente



class AdaptadorAlumnosProyecto (
    private var activity: nuevoProyectoDocente,
    private var listaAlumnos: List<dataAlumno>
): RecyclerView.Adapter<AdaptadorAlumnosProyecto.AlumnoViewHolder>() {

    class AlumnoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreAlumno)
        val tvCorreo: TextView = itemView.findViewById(R.id.tvCorreoAlumno)
        val btnQuitar: ImageButton = itemView.findViewById(R.id.btnQuitarAlumno)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alumno_proyecto, parent, false)
        return AlumnoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val alumno = listaAlumnos[position]

        holder.tvNombre.text = "${alumno.nombre} ${alumno.apellidoPaterno} ${alumno.apellidoMaterno}"
        holder.tvCorreo.text = alumno.correo

        holder.btnQuitar.setOnClickListener {
            activity.quitarAlumno(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return listaAlumnos.size
    }

    fun actualizarLista(nuevaLista: List<dataAlumno>) {
        listaAlumnos = nuevaLista
        notifyDataSetChanged()
    }
}