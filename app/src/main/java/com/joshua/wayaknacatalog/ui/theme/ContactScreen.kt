package com.joshua.wayaknacatalog.ui.theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacto") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            Text("WAYAKNA S.A. DE C.V.", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(10.dp))
            Text("Canales digitales:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text("• LinkedIn")
            Text("• Facebook / Instagram / TikTok")
            Text("• WhatsApp (Canal)")
            Spacer(Modifier.height(16.dp))
            Text("Siguiente iteración: botones para abrir WhatsApp/correo con Intent.")
        }
    }
}