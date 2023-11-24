package br.senai.sp.jandira.tripapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.tripapp.R
import br.senai.sp.jandira.tripapp.ui.theme.PoppinsRegular
import br.senai.sp.jandira.tripapp.ui.theme.GreenDefault


@Composable
fun TextField(){


    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = {

        },
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
                focusedBorderColor = GreenDefault,
                unfocusedBorderColor = GreenDefault
            )
    )
}



@Preview
@Composable
fun TextFieldPreview(){
    TextField()
}