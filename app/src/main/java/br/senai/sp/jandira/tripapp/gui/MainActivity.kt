package br.senai.sp.jandira.tripapp.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripapp.R
import br.senai.sp.jandira.tripapp.components.BottomShape
import br.senai.sp.jandira.tripapp.components.TopShape
import br.senai.sp.jandira.tripapp.ui.theme.*

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

    val context = LocalContext.current

    var emailState = remember {
        mutableStateOf("")
    }
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
                TopShape()
            } // fim do header
            //Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
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
                        fontWeight = FontWeight.Bold,
                        fontFamily = PoppinsBold
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
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = PoppinsRegular
                    )
                } // Fim do Login
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp))
                //Inputs
                Column(

                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        shape = RoundedCornerShape(16.dp),
                        label = {
                                Text(
                                    text = stringResource(id = R.string.outline_email),
                                    fontFamily = PoppinsRegular
                                )
                        },
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.email_24),
                                contentDescription = stringResource(id = R.string.email_description),
                                tint = Color(207, 1, 248)
                            )
                        },
                        colors = TextFieldDefaults
                            .outlinedTextFieldColors(
                                focusedBorderColor = RoseDefault,
                                unfocusedBorderColor = RoseDefault
                            )
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        shape = RoundedCornerShape(16.dp),
                        label = {
                            Text(
                                text = stringResource(id = R.string.outline_password),
                                fontFamily = PoppinsRegular
                                )
                        },
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.password_24),
                                contentDescription = stringResource(id = R.string.password_description),
                                tint = Color(207, 1, 248)
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = RoseDefault,
                            unfocusedBorderColor = RoseDefault
                        )
                    )

                } // Fim dos inputs
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp))
                //User
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(Color(207,6,240))
                    ) {
                        Row(){
                            Text(
                                text = stringResource(id = R.string.sing_in_button).uppercase(),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontFamily = PoppinsBold
                                )
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_forward_24),
                                contentDescription = stringResource(id = R.string.outline_email),
                                tint = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp))
                    Row(){
                        Text(
                            text = stringResource(id = R.string.sign_in_info),
                            fontFamily = PoppinsRegular
                        )
                        Spacer(
                            modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.sign_up_title),
                            modifier = Modifier.clickable {
                                val intent = Intent(context, SignUpActivity::class.java)
                                context.startActivity(intent)
                            },
                            fontFamily = PoppinsRegular,
                            color = Color(207,6,240)
                        )
                    }
                } // Fim do User
            } // Fim do Form
            //Footer
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                BottomShape()
            } // fim do Footer
        } // fim do Body
    }
}


