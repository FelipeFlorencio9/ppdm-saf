package br.senai.sp.jandira.tripapp.model

import androidx.compose.ui.graphics.painter.Painter

data class Category(
    val id: Number,
    val name : String,
    val image : Painter
)
