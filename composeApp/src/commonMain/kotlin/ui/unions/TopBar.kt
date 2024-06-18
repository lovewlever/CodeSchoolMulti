package ui.unions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import codeschoolmulti.composeapp.generated.resources.Res
import codeschoolmulti.composeapp.generated.resources.code_school
import codeschoolmulti.composeapp.generated.resources.compose_multiplatform
import ui.navigation.NavGraph
import org.jetbrains.compose.resources.painterResource
import ui.navigation.navigateHomeLabelPage
import ui.theme.LocalSignInDialogShowState
import ui.theme.textHintColor

@Composable
fun TopBar(navController: NavController) {
    val dialogState = LocalSignInDialogShowState.current
    var showCountries by remember { mutableStateOf(false) }
    Surface(elevation = 6.dp) {
        Row(modifier = Modifier
            .height(50.dp)
            .background(brush =
                if (isSystemInDarkTheme())
                    Brush.verticalGradient(colors = listOf(Color(0xFF2E3540), Color.Transparent))
                else
                    Brush.verticalGradient(colors = listOf(Color(0xFFC4DBFE), Color.Transparent))
            )
            .padding(horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(Res.drawable.code_school), modifier = Modifier.height(24.dp), contentDescription = "")
            Spacer(modifier = Modifier.width(16.dp))
            TextButton(colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.textHintColor),
                onClick = {
                    navController.navigate(NavGraph.NavHome.route)
            }, content = {
                Text(text = "Search", fontSize = 16.sp)
            })
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.textHintColor),
                onClick = {
                    navController.navigateHomeLabelPage()
                }, content = {
                Text(text = "Label", fontSize = 16.sp)
            })
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.textHintColor),
                onClick = {
                    navController.navigate(NavGraph.HomeLinkPage.route)
                }, content = {
                Text(text = "Link", fontSize = 16.sp)
            })
            Spacer(modifier = Modifier.width(8.dp))
            TextButton(colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.textHintColor),
                onClick = {
                navController.navigate(NavGraph.WindowsAdbHelp.route)
            }, content = {
                Text(text = "ADB", fontSize = 16.sp)
            })
            Spacer(modifier = Modifier.weight(1f))

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    showCountries = true
                }, content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(Res.drawable.compose_multiplatform), modifier = Modifier.size(24.dp).clip(
                            CircleShape), contentDescription = "")
                        Spacer(Modifier.width(4.dp))
                        Text(text = "GQ", fontSize = 14.sp)
                    }
                })
                DropdownMenu(modifier = Modifier.clip(RoundedCornerShape(12.dp)), expanded = showCountries, onDismissRequest = { showCountries = false }) {
                    listOf(
                        "Signed In As AG" to { dialogState.value = true },
                        "AddCodeBlock" to {},
                        "AddLinks" to {},
                        "Settings" to { navController.navigate(NavGraph.NavSettings.route)},
                        "Sign Out" to {}).forEach {
                        DropdownMenuItem(onClick = {
                            it.second()
                            showCountries = false
                        }) {
                            Text(text = it.first)
                        }
                    }
                }
            }
        }
    }

}