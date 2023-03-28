package br.senai.sp.jandira.tripapp.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomShape() {
    Card (
        modifier = Modifier.size(
            height = 40.dp,
            width = 120.dp),
        backgroundColor = Color(
            red = 207,
            green = 6,
            blue =  240),
        shape = RoundedCornerShape(
            topEnd = 20.dp)
    ){}
}

@Preview
@Composable
fun BottomShapePreview() {
    BottomShape()
}