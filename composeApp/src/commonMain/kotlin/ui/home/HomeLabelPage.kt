package ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.theme.dividerColor
import viewmodel.LabelLinkViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeLabelPage(
    searchContent: String,
    labelLinkViewModel: LabelLinkViewModel = viewModel { LabelLinkViewModel() }
) {
    LaunchedEffect(true) {
        labelLinkViewModel.queryClassifyList()
    }
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        labelLinkViewModel.labelDataStateList.forEach { lData ->
            Text(text = "${lData.classificationName}(${lData.refTcbLabelEntities?.size})", style = MaterialTheme.typography.h5)
            Spacer(Modifier.height(4.dp))
            FlowColumn(
                modifier = Modifier.border(
                    width = 0.5.dp,
                    color = MaterialTheme.colors.dividerColor,
                    shape = RoundedCornerShape(8.dp)
                ).width(500.dp)
                    .padding(12.dp)
            ) {
                lData.refTcbLabelEntities?.forEach {
                    it?.let {
                        OutlinedButton(onClick = {}) {
                            Text(text = it.labelName ?: "")
                        }
                    }
                }
            }
        }
    }
}