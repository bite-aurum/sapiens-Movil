package com.example.sapiens.adaptadores_docente

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sapiens.Modelo.docente.dataSubtareaDetalle
import com.example.sapiens.R
import com.example.sapiens.Vista.docente.detalleTareaDocente

class SubtareasDocenteAdaptador(
    private val activity: detalleTareaDocente,
    private var listaSubtareas: List<dataSubtareaDetalle>
) : RecyclerView.Adapter<SubtareasDocenteAdaptador.SubtareaViewHolder>() {

    class SubtareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTituloSubtarea)
        val txtResponsable: TextView = itemView.findViewById(R.id.txtResponsable)
        val txtEstado: TextView = itemView.findViewById(R.id.txtEstadoSubtarea)
        val txtSinEvidencia: TextView = itemView.findViewById(R.id.txtSinEvidencia)
        val layoutArchivo: LinearLayout = itemView.findViewById(R.id.layoutArchivo)
        val txtNombreArchivo: TextView = itemView.findViewById(R.id.txtNombreArchivo)
        val txtTipoArchivo: TextView = itemView.findViewById(R.id.txtTipoArchivo)
        val btnVer: Button = itemView.findViewById(R.id.btnVer)
        val btnDescargar: Button = itemView.findViewById(R.id.btnDescargar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subtarea_docente, parent, false)
        return SubtareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubtareaViewHolder, position: Int) {
        val subtarea = listaSubtareas[position]

        holder.txtTitulo.text = subtarea.titulo

        // Estado con su color
        if (subtarea.estado == "completada") {
            holder.txtEstado.text = "Completada"
            holder.txtEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDF6E8"))
            holder.txtEstado.setTextColor(Color.parseColor("#1B7F4B"))
        } else if (subtarea.estado == "en progreso") {
            holder.txtEstado.text = "En progreso"
            holder.txtEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E3F2EE"))
            holder.txtEstado.setTextColor(Color.parseColor("#0D7A6B"))
        } else {
            holder.txtEstado.text = "Pendiente"
            holder.txtEstado.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FDF2CE"))
            holder.txtEstado.setTextColor(Color.parseColor("#9C7414"))
        }

        if (subtarea.archivos.isEmpty()) {
            holder.txtResponsable.text = subtarea.responsable ?: "Sin responsable"
            holder.txtSinEvidencia.visibility = View.VISIBLE
            holder.layoutArchivo.visibility = View.GONE
        } else {
            val archivo = subtarea.archivos[0]
            holder.txtResponsable.text = (subtarea.responsable ?: "") + " · " + (archivo.fechaSubida ?: "")
            holder.txtSinEvidencia.visibility = View.GONE
            holder.layoutArchivo.visibility = View.VISIBLE
            holder.txtNombreArchivo.text = archivo.nombreArchivo
            holder.txtTipoArchivo.text = archivo.nombreArchivo.substringAfterLast(".").uppercase()

            holder.btnVer.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    activity.seleccionarVer(archivo)
                }
            })
            holder.btnDescargar.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    activity.seleccionarDescargar(archivo)
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return listaSubtareas.size
    }

    fun actualizarLista(nuevaLista: List<dataSubtareaDetalle>) {
        listaSubtareas = nuevaLista
        notifyDataSetChanged()
    }
}