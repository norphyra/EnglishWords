package com.example.englishwordspetproject.piechart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import android.graphics.Paint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PieChart(radius: Float, innerRadius: Float, modifier: Modifier, textColor: Color, centerText: String, centerTextColor: Color, input: List<PieChartInput>) {
    Column {
        PieChartDrawer(
            modifier = modifier.size(radius.dp),
            radius = radius * 1.3f,
            innerRadius = innerRadius,
            centerText = centerText,
            input = input,
            centerTextColor = centerTextColor,
            textColor = textColor
        )
    }
}

@Composable
fun PieChartDrawer(
    modifier: Modifier,
    radius:Float,
    innerRadius:Float,
    transparentWidth:Float = 70f,
    input:List<PieChartInput>,
    centerText:String,
    centerTextColor: Color,
    textColor: Color
) {
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    val inputList by remember {
        mutableStateOf(input)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            val width = size.width
            val height = size.height
            circleCenter = Offset(x= width/2f,y= height/2f)

            val totalValue = input.sumOf {
                it.value
            }
            val anglePerValue = 360f/totalValue
            var currentStartAngle = 0f

            inputList.forEach { pieChartInput ->
                val scale = 1.0f
                val angleToDraw = pieChartInput.value * anglePerValue
                scale(scale){
                    drawArc(
                        color = pieChartInput.color,
                        startAngle = currentStartAngle,
                        sweepAngle = angleToDraw,
                        useCenter = true,
                        size = Size(
                            width = radius*2f,
                            height = radius*2f
                        ),
                        topLeft = Offset(
                            (width-radius*2f)/2f,
                            (height - radius*2f)/2f
                        )
                    )
                    currentStartAngle += angleToDraw
                }
                var rotateAngle = currentStartAngle-angleToDraw/2f-90f
                var factor = 1f
                if(rotateAngle>90f){
                    rotateAngle = (rotateAngle+180).mod(360f)
                    factor = -0.92f
                }

                val percentage = (pieChartInput.value/totalValue.toFloat()*100).toInt()

                drawContext.canvas.nativeCanvas.apply {
                    if(percentage>3){
                        rotate(rotateAngle){
                            drawText(
                                "$percentage %",
                                circleCenter.x,
                                circleCenter.y+(radius-(radius-innerRadius)/2f)*factor,
                                Paint().apply {
                                    textSize = 13.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = textColor.toArgb()
                                }
                            )
                        }
                    }
                }
            }
            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    circleCenter.x,
                    circleCenter.y,
                    innerRadius,
                    Paint().apply {
                        color = Color.White.copy(alpha = 0.9f).toArgb()
                        setShadowLayer(10f,0f,0f, Color.Gray.toArgb())
                    }
                )
            }

            drawCircle(
                color = Color.White.copy(0.3f),
                radius = innerRadius+transparentWidth/2f
            )

        }
        Text(
            centerText,
            modifier = Modifier
                .width(Dp(innerRadius / 1.5f))
                .padding(15.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = centerTextColor
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PieChartPreview() {
    PieChart(230f, 100f, Modifier, centerText = "Some text", centerTextColor = Color.Black, textColor = Color.Black,
        input = listOf(
            PieChartInput(
                color = Color.Green,
                value = 29,
                description = "Python"
            ),
            PieChartInput(
                color = Color.Gray,
                value = 21,
                description = "Swift"
            ),
            PieChartInput(
                color = Color.Yellow,
                value = 32,
                description = "JavaScript"
            )
        ))
}

data class PieChartInput(
    val color:Color,
    val value:Int,
    val description:String
)