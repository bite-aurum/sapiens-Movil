package com.example.sapiens.Modelo.alumno
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleProyecto(
    @SerialName("id_proyecto") val idProyecto: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("problematica") val problematica: String = "",
    @SerialName("objetivos") val objetivos: String = "",
    @SerialName("equipo") val equipo: List<dataEquipoProyecto> = emptyList(),
    @SerialName("entregas") val entregas: List<dataEntregaProyecto> = emptyList(),
    @SerialName("tareas") val tareas: List<dataTareaProyecto> = emptyList()
)

@Serializable
data class dataEquipoProyecto(
    @SerialName("id_alumno") val idAlumno: Int = 0,
    @SerialName("nombre") val nombre: String = "",
    @SerialName("rol") val rol: String = ""
)


@Serializable
data class dataEntregaProyecto(
    @SerialName("id_subtarea") val idSubtarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("estado") val estado: String = ""
)


@Serializable
data class dataTareaProyecto(
    @SerialName("id_tarea") val idTarea: Int = 0,
    @SerialName("titulo") val titulo: String = "",
    @SerialName("estado") val estado: String = ""
)