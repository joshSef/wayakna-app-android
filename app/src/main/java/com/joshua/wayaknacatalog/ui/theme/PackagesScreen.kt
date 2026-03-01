package com.joshua.wayaknacatalog.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joshua.wayaknacatalog.data.PackageRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackagesScreen(
    onBack: () -> Unit,
    onOpenPackage: (String) -> Unit
) {
    val packages = PackageRepository.getAll()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Paquetes") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { pad ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(pad),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(packages) { p ->
                Card(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .clickable { onOpenPackage(p.id) }
                ) {
                    Column(Modifier.padding(14.dp)) {
                        Text(p.category, style = MaterialTheme.typography.labelLarge)
                        Text(p.name, style = MaterialTheme.typography.titleLarge)
                        Text(p.summary, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}