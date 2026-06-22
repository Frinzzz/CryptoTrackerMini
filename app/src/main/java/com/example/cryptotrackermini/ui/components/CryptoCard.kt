package com.example.cryptotrackermini.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cryptotrackermini.domain.model.Crypto

@Composable
fun CryptoCard(
    crypto: Crypto,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    notAvailableText: String = "N/D"
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(crypto.id) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = crypto.imageUrl,
                contentDescription = crypto.name,
                modifier = Modifier.size(42.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = crypto.name, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = crypto.symbol, style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Center) {
                Text(text = crypto.currentPrice?.let { "€ %.2f".format(it) } ?: notAvailableText)
                val change = crypto.priceChangePercentage24h
                Text(
                    text = change?.let { "%+.2f%%".format(it) } ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
