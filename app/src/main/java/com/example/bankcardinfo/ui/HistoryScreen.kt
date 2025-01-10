package com.example.bankcardinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankcardinfo.presentation.HistoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen() {
    val viewModel: HistoryViewModel = koinViewModel()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.history.observeAsState().value?.forEach {
            Text(
                "BIN: ${it.bin}",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                color = Color.Cyan,
                modifier = Modifier.fillMaxWidth()
            )
            CardInfoItem(it)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}