package com.example.nexusplannerandroid.utils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexusplannerandroid.ui.theme.lightGreen
import com.example.nexusplannerandroid.ui.theme.lightYellow

@Composable
fun CardComponent(
    titulo: String,
    footer: String? = null,
    status: Int = 0,
    mensaje: String,
    onClick: () -> Unit? = {}
) {
    var colores = hashMapOf<Int, Color>(
        Pair(3, lightGreen),
        Pair(4, lightYellow),
        Pair(5, MaterialTheme.colorScheme.secondary)
    )

    var color = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onPrimary
    )

    colores[status]?.let {
        color = CardDefaults.cardColors(
            containerColor = it
        )
    }

    Card(
        border = BorderStroke(2.dp, Color(221, 221, 221)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = color
    ) {
        CardHeaderComponent(titulo)

        CardBodyComponent(status, mensaje)

        if (!footer.isNullOrEmpty())
            CardFooterComponent(footer)
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun CardHeaderComponent(titulo: String) {
    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp, 16.dp, 8.dp)
    ) {
        Text(
            text = titulo,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CardBodyComponent(status: Int, mensaje: String) {
    var estados = HashMap<Int, String>()
    estados[1] = "En espera"
    estados[3] = "Terminado"
    estados[4] = "En proceso"
    estados[5] = "Pausado"

    Column(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
    ) {
        estados[status]?.let {
            Text(
                fontSize = 20.sp,
                text = it
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            text = mensaje,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CardFooterComponent(footer: String) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp, 16.dp, 16.dp)

    ) {
        Text(
            text = footer
        )
    }
}
