package com.example.sapiens.adaptadores_coordinador

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.coordinador.dataDocenteLista
import com.example.sapiens.R
import com.example.sapiens.Vista.coordinador.docentesCoordinador

class DocentesCoordinadorAdaptador(
    private val activity: docentesCoordinador,
    private var listaDocentes: List<dataDocenteLista>
) : RecyclerView.Adapter<DocentesCoordinadorAdaptador.DocenteViewHolder>() {

    class DocenteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombreDocente)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstadoDocente)
        val linea: View = itemView.findViewById(R.id.lineaDocente)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocenteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_docente_coordinador, parent, false)
        return DocenteViewHolder(view)
    }

    override fun onBindViewHolder(holder: DocenteViewHolder, position: Int) {
        val docente = listaDocentes[position]

        holder.tvNombre.text =
            "${docente.nombre} ${docente.apellidoPaterno} ${docente.apellidoMaterno}".trim()
        holder.tvEstado.text = docente.estado

        if (docente.estado == "Activo") {
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            holder.tvEstado.setTextColor(Color.parseColor("#1B7F4B"))
        } else {
            holder.tvEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ECE9E1"))
            holder.tvEstado.setTextColor(Color.parseColor("#6B706B"))
        }

        if (position == listaDocentes.size - 1) {
            holder.linea.visibility = View.GONE
        } else {
            holder.linea.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return listaDocentes.size
    }

    fun actualizarLista(nuevaLista: List<dataDocenteLista>) {
        listaDocentes = nuevaLista
        notifyDataSetChanged()
    }
}
