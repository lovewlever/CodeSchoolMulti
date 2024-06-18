package ui.unions

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ui.theme.dividerColor
import ui.theme.textMainColor

@Composable
fun SignInDialog(modifier: Modifier = Modifier, showState: MutableState<Boolean>) {
    if (showState.value) {
        Dialog(onDismissRequest = { showState.value = false }) {
            Surface(modifier = modifier, shape = RoundedCornerShape(12.dp)) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "LOGIN",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CompositionLocalProvider(LocalContentAlpha provides 0.5F) {
                        Text(text = "Email or phone address：", fontSize = 13.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                    Box(
                        modifier = Modifier
                            .border(
                                width = 0.5.dp,
                                color = MaterialTheme.colors.dividerColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    ) {
                        BasicTextField(
                            value = "Email or phone address",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = MaterialTheme.colors.textMainColor)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CompositionLocalProvider(LocalContentAlpha provides 0.5F) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(text = "Password：", fontSize = 13.sp)
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = "Create an account.",
                                color = Color(0xFF42b983),
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                    Box(
                        modifier = Modifier
                            .border(
                                width = 0.5.dp,
                                color = MaterialTheme.colors.dividerColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    ) {
                        BasicTextField(
                            value = "Password",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = MaterialTheme.colors.textMainColor)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                        Text(text = "Signing in...")
                    }
                }
            }
        }
    }

}