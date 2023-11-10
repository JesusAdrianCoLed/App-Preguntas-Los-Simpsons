package Preguntas

import android.media.Image
import androidx.compose.runtime.Composable

//Esta clase es para crear el array con el contenido de las preguntas
data class Preguntas(val pregunta: String, val imagen: Int, val respuesta: Boolean)