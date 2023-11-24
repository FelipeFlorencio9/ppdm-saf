package br.senai.sp.jandira.tripapp

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.tripapp.ui.theme.GreyDefault
import br.senai.sp.jandira.tripapp.ui.theme.PoppinsBold
import br.senai.sp.jandira.tripapp.ui.theme.PoppinsRegular
import br.senai.sp.jandira.tripapp.ui.theme.GreenDefault
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.UUID

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
    var loginState by remember {
        mutableStateOf("")
    }
    var senhaState by remember {
        mutableStateOf("")
    }
   
    val context = LocalContext.current

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cadastro de Usuário",
                color = GreenDefault,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = PoppinsBold,
                lineHeight = 16.sp
            )
            Text(
                text = "Crie uma nova conta",
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
                        contentScale = ContentScale.Crop,
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
                value = loginState,
                onValueChange = {
                    loginState = it
                },
                shape = RoundedCornerShape(16.dp),
                label = {
                    Text(
                        text = "Login",
                        fontFamily = PoppinsRegular
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.user_24),
                        contentDescription = stringResource(id = R.string.username_outline),
                        tint = GreenDefault
                    )
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = senhaState,
                onValueChange = { senhaState = it },
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation(),
                label = {
                    Text(
                        text = "Senha",
                        fontFamily = PoppinsRegular
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.password_24),
                        contentDescription = stringResource(id = R.string.password_description),
                        tint = GreenDefault
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )


            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {

                Button(
                    onClick = {
                       salvarUsuario(
                           Usuario(
                               login = loginState,
                               senha = senhaState,
                               imagem = photoUri,
                           ),
                           context
                       )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(GreenDefault)
                ) {
                    Text(
                        text = "Criar conta",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = PoppinsBold
                    )
                }
            }
        }
    }
}
data class Usuario(
    val login : String,
    val senha: String,
    val imagem : Uri?
)

fun salvarUsuario(usuario: Usuario, context : Context){

    var fotoUrl = ""
    usuario.imagem?.let{
        uploadToStorage(uri = it, context = context, type = "image", onSaveComplete = {foto ->

            Toast.makeText(context, "Foto salva com sucesso! : ${it}", Toast.LENGTH_SHORT).show()

            val requisicao = UserRequest(
                imagem = foto,
                login = usuario.login,
                senha = usuario.senha
            )
            Log.i("FOTO", foto)
            cadastrarUsuarioNaApi(
                userRequest = requisicao,
                context = context
            )
            Toast.makeText(context, "Pronto para requisição: ${requisicao}", Toast.LENGTH_LONG).show()
        })

    }

    Toast.makeText(context, "Salvo com sucesso : ${usuario}", Toast.LENGTH_SHORT).show()


}
data class UserRequest(
    val login : String,
    val senha: String,
    val imagem: String,
)
fun salvarFotoNoFireBase(fotoUri : Uri?, context: Context, onSaveComplete : ( String) -> Unit){

}

fun uploadToStorage(
    uri: Uri,
    context: Context,
    type: String,
    onSaveComplete: (String) -> Unit
) {
    val storage = Firebase.storage

    var storageRef = storage.reference

    val randomUnique = UUID.randomUUID()
    var spaceRef: StorageReference

    if (type == "image"){
        spaceRef = storageRef.child("imagens/$randomUnique.jpg")
    }else{
        spaceRef = storageRef.child("videos/$randomUnique.mp4")
    }

    val byteArray: ByteArray? = context.contentResolver
        .openInputStream(uri)
        ?.use { it.readBytes() }

    byteArray?.let{

        var uploadTask = spaceRef.putBytes(byteArray)
        uploadTask.addOnFailureListener {
            Toast.makeText(
                context,
                "Falha ao salvar foto. Tente novamente.",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnSuccessListener { taskSnapshot ->
            spaceRef.downloadUrl.addOnSuccessListener { uri ->
                val downloadUrl = uri.toString()

                Toast.makeText(
                    context,
                    "Foto salva com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                onSaveComplete(downloadUrl)
                Log.i("URL-STORAGE-UTIL", downloadUrl)

            }.addOnFailureListener {
                Toast.makeText(
                    context,
                    "Falha ao baixar a imagem.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
