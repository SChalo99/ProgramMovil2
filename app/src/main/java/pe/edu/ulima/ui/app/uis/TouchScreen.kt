package pe.edu.ulima.ui.app.uis

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.Math.sqrt
import kotlin.math.pow
import androidx.compose.foundation.gestures.detectVerticalDragGestures


@Preview
@Composable
public fun TouchScreenPreview(){
    TouchScreen()
}

@Composable
public fun TouchScreen(){
    val size = remember{mutableStateOf(200)}

    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .size(size.value.dp)
                .background(Color.Gray)
                .pointerInput(Unit){
                    val fingerCount = 2
                    var previousDistance = 0f
                    detectVerticalDragGestures {  _, dragAmount ->
                        val isUp = dragAmount < 0
                        Log.d("TOUCH_SCREEN", "+++++++++++++++++++++++++++++++++++")
                        Log.d("TOUCH_SCREEN", isUp.toString())
                    }
                    forEachGesture{
                        awaitPointerEventScope {
                            do{
                                val event = awaitPointerEvent()
                                if(event.changes.size == fingerCount){
                                    Log.d("TOUCH_SCREEN", "dos dedos")
                                    val firstX = event.changes[0].position.x
                                    val firstY = event.changes[0].position.y
                                    val secondX = event.changes[1].position.x
                                    val secondY = event.changes[1].position.y
                                    event.changes[0]
                                    val currentDistance = sqrt(
                                        ((secondX - firstX).pow(2) + (secondY - firstY).pow(2)).toDouble()
                                    )

                                    if(currentDistance - previousDistance > 10){
                                        Log.d("TOUCH_SCREEN", "bigger")
                                        if(size.value < 300){
                                            size.value += 3
                                        }
                                        previousDistance = currentDistance.toFloat()
                                    }else if(previousDistance - currentDistance > 10){
                                        Log.d("TOUCH_SCREEN", "smaller")
                                        if(size.value > 150){
                                            size.value -= 3
                                        }
                                        previousDistance = currentDistance.toFloat()
                                    }
                                }
                            }while(event.changes.any{
                                it.pressed
                            })
                        }
                    }
                }
        )
    }
}