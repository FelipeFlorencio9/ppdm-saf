package br.senai.sp.jandira.tripapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripapp.ui.theme.*

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TripAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   SignUpScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreen() {
    //Body
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        )
        {
            Card (
                modifier = Modifier.size(
                    height = 40.dp,
                    width = 120.dp),
                backgroundColor = Color(
                    red = 207,
                    green = 6,
                    blue = 240),
                shape = RoundedCornerShape(
                    bottomStart = 20.dp
                )
            ){}
        }
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_title),
                color = RoseDefault,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = PoppinsBold,
                lineHeight = 16.sp
            )
            Text(
                text = stringResource(id = R.string.sign_up_link),
                color = GreyDefault,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = PoppinsRegular,
                lineHeight = 18.sp
            )
        }
        //Forms
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.username_outline),
                        fontFamily = PoppinsRegular
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.user_24),
                        contentDescription = stringResource(id = R.string.username_outline),
                        tint = Color(207, 1, 248)
                    )
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.phone_outline),
                        fontFamily = PoppinsRegular
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.phone_android_24),
                        contentDescription = stringResource(id = R.string.phone_outline),
                        tint = Color(207, 1, 248)
                    )
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.email_outline),
                        fontFamily = PoppinsRegular
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.email_24),
                        contentDescription = stringResource(id = R.string.email_outline),
                        tint = Color(207, 1, 248)
                    )
                }
            )
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
                }
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            //Age
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
                Text(
                    text = stringResource(id = R.string.age_validation_check),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = PoppinsRegular
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color(207,6,240))
            ) {
                Text(
                        text = stringResource(id = R.string.create_account).uppercase(),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = PoppinsBold
                )
            }
            Spacer(
                modifier = Modifier
                    .height(32.dp)
            )
            Row(){
                Text(
                    text = stringResource(id = R.string.sign_in_info),
                    fontFamily = PoppinsRegular
                )
                Spacer(
                    modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.sing_in_button),
                    modifier = Modifier.clickable {

                    },
                    fontFamily = PoppinsRegular,
                    color = Color(207,6,240)
                )
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
    }
}