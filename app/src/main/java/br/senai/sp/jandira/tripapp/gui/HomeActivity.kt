package br.senai.sp.jandira.tripapp.gui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripapp.R
import br.senai.sp.jandira.tripapp.model.Category
import br.senai.sp.jandira.tripapp.model.Trip
import br.senai.sp.jandira.tripapp.repository.CategoryRepository
import br.senai.sp.jandira.tripapp.repository.TripRepository
import br.senai.sp.jandira.tripapp.ui.theme.TripAppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("ds2m", intent.extras.toString())

        setContent {
            TripAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    HomeScreen(
                        CategoryRepository.getCategories(),
                        TripRepository.getTrips()
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    categories : List<Category>,
    trips: List<Trip>
) {
    Column (modifier = Modifier.fillMaxSize()){
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RectangleShape
                ){
            Image(
                painter = painterResource(R.drawable.paris2),
                contentDescription = "logo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = stringResource(id = R.string.categories),
            color = Color(56,54,54),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ){
            items(categories){
                Card (
                    modifier = Modifier
                        .size(
                            width = 109.dp,
                            height = 64.dp
                        )
                        .padding(
                            vertical = 8.dp,
                            horizontal = 4.dp
                        ),
                    backgroundColor = Color(207,6,240)
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = it.categoryIcon,
                            contentDescription = it.categoryName
                        )
                        Text(
                            text = it.categoryName,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }


                }
            }
        }

        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.search))
            },
            trailingIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "")
                }
            }
        )

        Text(
            text = stringResource(
                id = R.string.past_trips
            ),
            fontSize = 14.sp,
            color = Color(86,84,84),
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(){
            items(trips){
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_photography_24),
                            contentDescription = "")
                        Text(text = "${it.location}, ${it.startDate.year}")
                        Text(text = "${it.description}")
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${it.startDate} - ${it.endDate}",
                            textAlign = TextAlign.End
                        )

                    }
                }
            }
        }
    }
}

