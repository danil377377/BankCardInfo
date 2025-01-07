package com.example.bankcardinfo.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankcardinfo.domain.model.CardInfo

@Composable
fun CardInfoItem(cardInfo: CardInfo) {
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