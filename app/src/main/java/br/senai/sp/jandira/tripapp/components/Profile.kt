package br.senai.sp.jandira.tripapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.tripapp.R

@Composable
fun Profile() {
    Box(modifier = Modifier
        .size(64.dp)){
        Card(
            modifier = Modifier
                .size(12.dp,24.dp),
            backgroundColor = Color.Magenta
        ) {

        }
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.susanna_hoffs),
            contentDescription = stringResource(id = R.string.user_susanna),
        )
    }

}

@Preview
@Composable
fun ProfilePreview() {
    Profile()
}