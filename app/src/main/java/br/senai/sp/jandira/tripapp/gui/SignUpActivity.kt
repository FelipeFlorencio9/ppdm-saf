package br.senai.sp.jandira.tripapp.gui


import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripapp.R
import br.senai.sp.jandira.tripapp.model.User
import br.senai.sp.jandira.tripapp.repository.UserRepository
import br.senai.sp.jandira.tripapp.ui.theme.*
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

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

fun saveUser(
    userName: String,
    phone: String,
    email: String,
    password: String,
    isOver18: Boolean,
    profilePhotoUri : String,
    context: Context,
) {
    
    //Criando um objeto User
    val newUser = User(
        id = 0,
        userName = userName,
        phone = phone,
        email = email,
        password = password,
        isOver18 = isOver18,
        profilePhoto = profilePhotoUri
    )

    //Criando uma instancia ado repositório

    val userRepository = UserRepository(context = context)

    //Verificar se o usuário já existe
    val user = userRepository.findUserByEmail(email)


    //Salvar o usuario
    if (user == null){
        val id = userRepository.save(newUser)
        Toast.makeText(
            context,
            "Created User #$id",
            Toast.LENGTH_SHORT
        ).show()
    } else{
        Toast.makeText(
            context,
            "User already exists!",
            Toast.LENGTH_SHORT
        ).show()
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreen() {

    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        photoUri = it
    }
    var painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(photoUri)
            .build()
    )
    //Body
    var userNameState by remember {
        mutableStateOf("")
    }
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    var phoneState by remember {
        mutableStateOf("")
    }
    var over18State by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        )
        {
            Card(
                modifier = Modifier.size(
                    height = 40.dp,
                    width = 120.dp
                ),
                backgroundColor = Color(
                    red = 207,
                    green = 6,
                    blue = 240
                ),
                shape = RoundedCornerShape(
                    bottomStart = 20.dp
                )
            ) {}
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
            Spacer(modifier = Modifier.height(32.dp))
            Box {
                Card(
                    modifier = Modifier.size(100.dp),
                    shape = CircleShape,
                ) {
                    Image(
                        painter = if(photoUri == null) painterResource(id = R.drawable.profile) else painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clickable {
                            launcher.launch("image/png")
                            var message = "nada"
                            Log.i(
                                "ds2m",
                                "URI: ${photoUri?.path ?: message}"
                            )
                        },
                    painter = painterResource(
                        id = R.drawable.baseline_add_a_photo_24),
                    contentDescription = null,
                )
            }
        }
        //Forms
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userNameState,
                onValueChange = {
                    userNameState = it
                },
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
                value = phoneState,
                onValueChange = {
                    phoneState = it
                },
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
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = emailState,
                onValueChange = { emailState = it },
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
                value = passwordState,
                onValueChange = { passwordState = it },
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation(),
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
                    checked = over18State,
                    onCheckedChange = {
                        over18State = it
                    },
                )
                Text(
                    text = stringResource(id = R.string.age_validation_check),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = PoppinsRegular
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {

            Button(
                onClick = {
                    saveUser(
                        userName = userNameState,
                        phone = phoneState,
                        email = emailState,
                        password = passwordState,
                        isOver18 = over18State,
                        profilePhotoUri = photoUri?.path ?: "",
                        context = context
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color(207, 6, 240))
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
            Row {
                Text(
                    text = stringResource(id = R.string.sign_in_info),
                    fontFamily = PoppinsRegular
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = stringResource(id = R.string.sing_in_button),
                    modifier = Modifier.clickable {

                    },
                    fontFamily = PoppinsRegular,
                    color = Color(207, 6, 240)
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
            Card(
                modifier = Modifier.size(
                    height = 40.dp,
                    width = 120.dp
                ),
                backgroundColor = Color(
                    red = 207,
                    green = 6,
                    blue = 240
                ),
                shape = RoundedCornerShape(
                    topEnd = 20.dp
                )
            ) {}
        }
    }
}