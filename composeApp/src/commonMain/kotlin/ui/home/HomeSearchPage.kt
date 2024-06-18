package ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import codeschoolmulti.composeapp.generated.resources.Res
import codeschoolmulti.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ui.theme.textHintColor
import viewmodel.AppViewModel

@Composable
fun HomeSearchPage(
    appViewModel: AppViewModel = viewModel { AppViewModel() }
) {

    LaunchedEffect(true) {
        appViewModel.queryAppInfo()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Row {
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.textHintColor),
                    shape = CircleShape, onClick = {}, content = {
                    Text("Select Classify")
                })
                Spacer(Modifier.width(12.dp))
                OutlinedButton(colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colors.textHintColor),
                    shape = CircleShape, onClick = {}, content = {
                    Text("Select Label")
                })
            }
            Spacer(Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = true, onCheckedChange = {}, colors = CheckboxDefaults.colors())
                Text("Match Title")
                Spacer(Modifier.width(20.dp))
                Checkbox(checked = true, onCheckedChange = {})
                Text("Match Description Information")
                Spacer(Modifier.width(20.dp))
                Checkbox(checked = true, onCheckedChange = {})
                Text("Match Code Block")
            }
            Spacer(Modifier.height(2.dp))

            Row(
                modifier = Modifier.height(50.dp).widthIn(max = 650.dp).border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ButtonDefaults.OutlinedBorderOpacity),
                    shape = CircleShape
                ).padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(Modifier.width(14.dp))
                Divider(
                    modifier = Modifier.height(20.dp).width(1.dp),
                    color = MaterialTheme.colors.onSurface.copy(alpha = ButtonDefaults.OutlinedBorderOpacity)
                )
                Spacer(Modifier.width(14.dp))
                BasicTextField(value = appViewModel.searchContent,
                    onValueChange = { appViewModel.searchContent = it },
                    modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = ButtonDefaults.OutlinedBorderOpacity),
                    shape = CircleShape
                ).padding(horizontal = 24.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                SearchItem(
                    resource = Res.drawable.compose_multiplatform,
                    text = "Baidu"
                )
                Spacer(Modifier.width(16.dp))
                SearchItem(
                    resource = Res.drawable.compose_multiplatform,
                    text = "Google"
                )
            }
            Spacer(Modifier.height(16.dp))
            Text(appViewModel.todayWink, modifier = Modifier.widthIn(max = 650.dp))
            Spacer(Modifier.height(200.dp))
        }
    }
}

@Composable
private fun SearchItem(modifier: Modifier = Modifier, resource: DrawableResource, text: String) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(resource),
            contentDescription = "",
            modifier = Modifier.size(40.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text)
    }
}