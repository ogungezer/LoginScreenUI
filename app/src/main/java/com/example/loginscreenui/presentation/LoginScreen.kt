package com.example.loginscreenui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginscreenui.R
import com.example.loginscreenui.component.LoginTextField
import com.example.loginscreenui.ui.theme.Black
import com.example.loginscreenui.ui.theme.BlueGray
import com.example.loginscreenui.ui.theme.LightBlueWhite
import com.example.loginscreenui.ui.theme.dimens


@Composable
fun LoginScreen() {

    val viewModel : AppViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.getMode()
    }

    val uiColor = if (uiState.value.themeMode == "dark") Color.White else Black
    val backgroundColor = if(uiState.value.themeMode == "dark") Black else Color.White

    if(uiState.value.themeMode != " "){
        Surface {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
            ) {
                TopSection(){
                    viewModel.setMode(it)
                }
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = MaterialTheme.dimens.small3)) {
                    LoginTextField(
                        label = "Email",
                        trailing = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .padding(horizontal = MaterialTheme.dimens.small1),
                        viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
                    LoginTextField(
                        label = "Password",
                        trailing = "Forgot?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .padding(horizontal = MaterialTheme.dimens.small1),
                        viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small2))
                    LoginButton(viewModel = viewModel)
                }
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium3))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = MaterialTheme.dimens.small3)
                ) {
                    Text(
                        text = "Or Continue With",
                        style = MaterialTheme.typography.labelMedium,
                        color = uiColor.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SocialCard(
                            painter = R.drawable.google,
                            text = "Google",
                            uiColor = uiColor,
                            viewModel = viewModel
                        )
                        SocialCard(
                            painter = R.drawable.facebook,
                            text = "Facebook",
                            uiColor = uiColor,
                            viewModel = viewModel
                        )
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Don't Have An Account?",
                            style = MaterialTheme.typography.labelMedium,
                            color = uiColor.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Create now",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight(500),
                                color = uiColor
                            ),
                            modifier = Modifier.clickable { /* TODO */ }
                        )
                    }
                }
            }
        }
    }

}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SocialCard(
    painter: Int,
    text: String,
    uiColor: Color,
    viewModel: AppViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(
            containerColor = if (uiState.value.themeMode == "dark") Color.Transparent else LightBlueWhite,
            contentColor = uiColor.copy(alpha = 0.7f)
        ),
        modifier = Modifier
            .height(MaterialTheme.dimens.medium3)
            .width(140.dp),
        shape = RoundedCornerShape(4.dp),
        border = if (uiState.value.themeMode == "dark") BorderStroke(1.dp, BlueGray) else BorderStroke(
            0.dp,
            Color.Transparent
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = painter),
                contentDescription = text,
                modifier = Modifier.size(MaterialTheme.dimens.small3)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = uiColor.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun LoginButton(
    viewModel: AppViewModel
) {
    val uiState = viewModel.uiState.collectAsState()
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimens.small1),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (uiState.value.themeMode == "dark") BlueGray else Black,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Log in",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight(500))
        )
    }
}

@Composable
fun TopSection(onSwitched : (Boolean) -> Unit) {
    val viewModel : AppViewModel = viewModel()
    val state = viewModel.uiState.collectAsState()
    var switchState by remember { mutableStateOf(state.value.themeMode == "dark") }
    val uiColor = if (state.value.themeMode == "dark") Color.White else Black

    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            painter = if (state.value.themeMode == "dark") painterResource(id = R.drawable.shape_night) else painterResource(id = R.drawable.shape) ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f),
            contentScale = ContentScale.FillBounds
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 90.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(50.dp),
                tint = uiColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "The Tolet",
                    style = MaterialTheme.typography.headlineMedium,
                    color = uiColor
                )
                Text(
                    text = "Find Your House",
                    style = MaterialTheme.typography.titleMedium,
                    color = uiColor
                )
            }
        }

        Switch(
            checked = state.value.themeMode == "dark"/*switchState*/,
            onCheckedChange = {
                switchState = it
                //viewModel.setMode(it)
                onSwitched(it)
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
            thumbContent = {
                if(state.value.themeMode == "dark"){
                    Image(painter = painterResource(id = R.drawable.moon), contentDescription = "night mode", Modifier.size(30.dp))
                } else {
                    Image(painter = painterResource(id = R.drawable.sun), contentDescription = "light mode", Modifier.size(30.dp))
                }
            },
            colors = SwitchDefaults.colors(
                checkedTrackColor = Color(0xFF0E203D),
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent,
                uncheckedTrackColor = Color(0xFF5D9EF0)
            )
        )

        Text(
            text = "Login",
            style = MaterialTheme.typography.displayMedium,
            color = uiColor,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 18.dp)
        )
    }
}





