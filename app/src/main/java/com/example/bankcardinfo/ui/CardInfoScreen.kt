package com.example.bankcardinfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankcardinfo.presentation.CardInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardInfoScreen() {
    val viewModel: CardInfoViewModel = koinViewModel()
    val binInput = remember { mutableStateOf("") }
    val binInfo = viewModel.binInfo.observeAsState().value
    val isLoading = viewModel.isLoading.observeAsState().value ?: false
    val error = viewModel.error.observeAsState().value


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = binInput.value,
            onValueChange = { binInput.value = it },
            label = { Text("Enter BIN") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.getCardInfo(binInput.value) }, enabled = binInput.value.length==6) {
            Text("Get Card Info")
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {
            CircularProgressIndicator()
        }

        error?.let { errorMessage ->
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        binInfo?.let { cardInfo ->
            Text(
                "Card Details:",
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            cardInfo.scheme?.let {
                Text(
                    "Scheme: $it",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            cardInfo.type?.let {
                Text(
                    "Type: $it",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            cardInfo.brand?.let {
                Text(
                    "Brand: $it",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            cardInfo.prepaid?.let {
                Text(
                    "Prepaid: $it",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            cardInfo.country?.let { country ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Country:",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                country.name?.let {
                    Text(
                        "Name: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                country.alpha2?.let {
                    Text(
                        "Code: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                country.currency?.let {
                    Text(
                        "Currency: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                country.emoji?.let {
                    Text(
                        "Emoji: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                country.latitude?.let {
                    Text(
                        "Latitude: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                country.longitude?.let {
                    Text(
                        "Longitude: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            cardInfo.bank?.let { bank ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Bank:",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                bank.name?.let {
                    Text(
                        "Name: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                bank.url?.let {
                    Text(
                        "URL: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                bank.phone?.let {
                    Text(
                        "Phone: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                bank.city?.let {
                    Text(
                        "City: $it",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}