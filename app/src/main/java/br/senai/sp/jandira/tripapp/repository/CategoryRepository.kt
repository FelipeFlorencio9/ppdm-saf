package br.senai.sp.jandira.tripapp.repository


import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import br.senai.sp.jandira.tripapp.R
import br.senai.sp.jandira.tripapp.model.Category



class CategoryRepository {
   
    companion object{

        @Composable
        fun getCategories(): List<Category> {
            return listOf(
                Category(
                    id = 1,
                    categoryName = "Mountain",
                    categoryIcon = painterResource(id = R.drawable.mountain_figma)
                ),
                Category(
                    id = 2,
                    categoryName = "Snow",
                    categoryIcon = painterResource(id = R.drawable.ski_figma)
                ),
                Category(
                    id = 3,
                    categoryName = "Beach",
                    categoryIcon = painterResource(id = R.drawable.beach_figma)
                )
            )
            
        }
    }
}