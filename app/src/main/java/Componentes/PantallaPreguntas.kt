package Componentes

import Preguntas.Preguntas
import android.content.Context
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica2aplicacionpreguntas.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random.Default.nextInt


@Composable
fun Juego() {
    // Definición de las preguntas y respuestas
    val listaPreguntas = remember {
        arrayListOf(
            Preguntas("¿La madre de Homer era hippie?", R.drawable.mona, true),
            Preguntas("¿Es Homer el dueño de la Central Nuclear?", R.drawable.nuclear, false),
            Preguntas("¿Disparó Maggie al Sr Burns?", R.drawable.maguie, true),
            Preguntas("¿Es Barnie el padre de Nelson?", R.drawable.nelsoon, false),
            Preguntas("¿Fue el abuelo Simpson un gran militar?", R.drawable.abuelo, true),
            Preguntas("¿Seymour Skinner es su verdadero nombre?", R.drawable.skiner, false),
            Preguntas("¿Fit Tonny reemplazó a Fat Tonny?", R.drawable.toni, true),
            Preguntas("¿Consiguió Homer salvar a Li, la hija de Selma?", R.drawable.buda, true),
            Preguntas("¿Marge tiene 3 hermanas?", R.drawable.hermana, false),
            Preguntas("¿Apu se va de Los Simpsons?", R.drawable.apuu, false)
        )
    }

    // Variables de estado para el juego
    var retroalimentacionTexto by remember { mutableStateOf("") }
    var retroalimentacionColor by remember { mutableStateOf(Color.Transparent) }
    var Pregunta by remember { mutableStateOf(Random().nextInt(10)) }
    val funcionDelay = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar pregunta
        Text(
            text = listaPreguntas[Pregunta].pregunta,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )

        // Mostrar imagen asociada a la pregunta
        Image(
            painter = painterResource(id = listaPreguntas[Pregunta].imagen),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de retroalimentación
        Text(
            text = retroalimentacionTexto,
            color = retroalimentacionColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp),
            fontSize = 20.sp
        )

        // Botones de respuesta
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {Button(
            onClick = {
                if (listaPreguntas[Pregunta].respuesta) {
                    retroalimentacionTexto = "De Locoossss"
                    retroalimentacionColor = Color.Green
                } else {
                    retroalimentacionTexto = "Naaah Washoo"
                    retroalimentacionColor = Color.Red
                }
                funcionDelay.launch(Dispatchers.Main){
                    delay(2000)
                    Pregunta = if (Pregunta < listaPreguntas.size - 1) Pregunta + 1 else 0
                    retroalimentacionTexto= ""
                }
            },
            //ponemos un modificador para que tenga un padding entre botones,
            // para que no esten pegados
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text("Si")
        }
            //Lo mismo que el boton de arriba, pero el boton de al lado
            Button(
                onClick = {
                    if (!listaPreguntas[Pregunta].respuesta) {
                        retroalimentacionTexto = "De Locoossss"
                        retroalimentacionColor = Color.Green
                    } else {
                        retroalimentacionTexto = "Naaah Washoo"
                        retroalimentacionColor = Color.Red
                    }
                    funcionDelay.launch(Dispatchers.Main){
                        delay(2000)
                        Pregunta = if (Pregunta < listaPreguntas.size - 1) Pregunta + 1 else 0
                        retroalimentacionTexto= ""
                    }
                },
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text("No")
            }
        }

        // Botones PREV, RANDOM, NEXT
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            BotonNavegacion("PREV") {
                retroalimentacionTexto = ""
                retroalimentacionColor = Color.Transparent
                Pregunta = if (Pregunta > 0) Pregunta - 1 else listaPreguntas.size - 1
            }

            BotonNavegacion("RANDOM") {
                Pregunta = Random().nextInt(11)
                retroalimentacionTexto= ""
            }

            BotonNavegacion("NEXT") {
                retroalimentacionTexto = ""
                retroalimentacionColor = Color.Transparent
                Pregunta = if (Pregunta < listaPreguntas.size - 1) Pregunta + 1 else 0
            }
        }
    }
}


@Composable
fun BotonNavegacion(texto: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = texto)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDelJuego() {
    Juego()
}



