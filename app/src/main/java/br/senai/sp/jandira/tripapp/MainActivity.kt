package br.senai.sp.jandira.tripapp

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripapp.ui.theme.TripAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripAppTheme {
                TripMainScreen()
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun TripMainScreen(){

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        //Body
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //Header
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Card (
                    modifier = Modifier.size(
                        height = 50.dp,
                        width = 120.dp),
                    backgroundColor = Color(
                        red = 207,
                        green = 6,
                        blue = 240),
                    shape = RoundedCornerShape(
                        bottomStart = 20.dp
                    )
                ){

                }
            }
            //Form
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            ) {
                //Login
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.login),
                        color = colorResource(id = R.color.purple_default),
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.login_info),
                        color = colorResource(id = R.color.grey_default),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp))
                //Inputs
                Column(

                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = stringResource(id = R.string.outline_email),
                        onValueChange = {},
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = {
                            Image(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.email),
                                contentDescription = ""
                            )
                        }
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = stringResource(id = R.string.outline_password),
                        onValueChange = {},
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = {
                            Image(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.password),
                                contentDescription = ""
                            )
                        }
                    )

                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp))
                //User
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Text(text = stringResource(id = R.string.sing_in_button))

                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp))
                    Row(){
                        Text(
                            text = stringResource(id = R.string.sign_in_info)
                        )
                        Spacer(
                            modifier = Modifier.width(6.dp))

                        Text(
                            text = stringResource(id = R.string.sign_up_link)
                        )
                    }


                }
            }
            //Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Card (
                    modifier = Modifier.size(
                        height = 50.dp,
                        width = 120.dp),
                    backgroundColor = Color(
                        red = 207,
                        green = 6,
                        blue =  240),
                    shape = RoundedCornerShape(
                        topEnd = 20.dp)
                ){}
            }
        }
    }
}


