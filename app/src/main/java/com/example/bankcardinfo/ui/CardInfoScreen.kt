package com.example.bankcardinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankcardinfo.presentation.CardInfoViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.text.input.KeyboardCapitalization.Companion as KeyboardCapitalization1

@Composable
fun CardInfoScreen(onHistoryClick:()->Unit ) {
    val viewModel: CardInfoViewModel = koinViewModel()
    val binInput = viewModel.binInput.observeAsState().value?:""
    val binInfo = viewModel.binInfo.observeAsState().value
    val isLoading = viewModel.isLoading.observeAsState().value ?: false
    val error = viewModel.error.observeAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = binInput,
            onValueChange = { viewModel.onInputChange(it) },
            label = { Text("Enter BIN") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization1.None,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(onNext = { keyboardController?.hide()
            if(binInput.length==6)viewModel.getCardInfo(binInput)})
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.getCardInfo(binInput) }, enabled = binInput.length==6) {
            Text("Get Card Info")
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        if(!error.isNullOrEmpty()) {
            error.let { errorMessage ->
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }else {
            binInfo?.let { cardInfo ->
                CardInfoItem(cardInfo)
            }
        }
        Button(onClick = { onHistoryClick() }) {
            Text("History")
        }
    }
}