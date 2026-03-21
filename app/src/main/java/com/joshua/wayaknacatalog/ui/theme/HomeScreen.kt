package com.joshua.wayaknacatalog.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onViewPackages: () -> Unit,
    onViewFavorites: () -> Unit,
    onContact: () -> Unit
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WayaknaWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            HeaderBar()
            HeroSection(
                onViewPackages = onViewPackages,
                onViewFavorites = onViewFavorites,
                onContact = onContact
            )
            ServicePreviewSection()
            BrandLinksSection(
                onOpenWayakna = { uriHandler.openUri("https://wayakna.com") },
                onOpenCorreobox = { uriHandler.openUri("https://correobox.mx") },
                onOpenOdoo = { uriHandler.openUri("https://odoo.com") }
            )
        }
    }
}

@Composable
private fun HeaderBar() {
    BoxWithConstraints {
        val compact = maxWidth < 380.dp

        Surface(
            shape = RoundedCornerShape(28.dp),
            color = WayaknaWhite,
            shadowElevation = 8.dp,
            tonalElevation = 2.dp
        ) {
            if (compact) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    BrandBadge(brand = BrandKind.WAYAKNA)
                    BrandBadge(brand = BrandKind.ODOO)
                    BrandBadge(brand = BrandKind.CORREOBOX)
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BrandBadge(brand = BrandKind.WAYAKNA)
                    BrandBadge(brand = BrandKind.ODOO)
                    BrandBadge(brand = BrandKind.CORREOBOX)
                }
            }
        }
    }
}

@Composable
private fun HeroSection(
    onViewPackages: () -> Unit,
    onViewFavorites: () -> Unit,
    onContact: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = WayaknaWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Capaz de impulsarte con tecnologia, confianza y acompanamiento.",
                style = MaterialTheme.typography.displayLarge,
                color = WayaknaNavy
            )
            Text(
                text = "Una experiencia movil para explorar soluciones de Wayakna, paquetes Odoo y planes de correo empresarial.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            BoxWithConstraints {
                val compact = maxWidth < 420.dp

                if (compact) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(onClick = onViewPackages, modifier = Modifier.fillMaxWidth()) {
                            Text("Ver paquetes")
                        }
                        OutlinedButton(onClick = onViewFavorites, modifier = Modifier.fillMaxWidth()) {
                            Text("Guardados")
                        }
                        OutlinedButton(onClick = onContact, modifier = Modifier.fillMaxWidth()) {
                            Text("Contactanos")
                        }
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = onViewPackages) {
                            Text("Ver paquetes")
                        }
                        OutlinedButton(onClick = onViewFavorites) {
                            Text("Guardados")
                        }
                        OutlinedButton(onClick = onContact) {
                            Text("Contactanos")
                        }
                    }
                }
            }
            BoxWithConstraints {
                val compact = maxWidth < 360.dp

                if (compact) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        MiniStat(title = "3", body = "Paquetes Odoo", modifier = Modifier.fillMaxWidth())
                        MiniStat(title = "3", body = "Planes CorreoBox", modifier = Modifier.fillMaxWidth())
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MiniStat(title = "3", body = "Paquetes Odoo")
                        MiniStat(title = "3", body = "Planes CorreoBox")
                    }
                }
            }
        }
    }
}

@Composable
private fun ServicePreviewSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionCard(
            title = "Odoo Wayakna",
            description = "Paquetes Via, Rastro y Sendero con una presentacion clara de alcance, valor comercial y precio por horas."
        )
        SectionCard(
            title = "CorreoBox",
            description = "Planes Flex 5G, 25G y 100G para comunicar capacidad, proteccion e infraestructura de correo empresarial."
        )
    }
}

@Composable
private fun BrandLinksSection(
    onOpenWayakna: () -> Unit,
    onOpenCorreobox: () -> Unit,
    onOpenOdoo: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = WayaknaMint)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Conecta con cada marca",
                style = MaterialTheme.typography.titleLarge,
                color = WayaknaNavy
            )
            Text(
                text = "Accede a los sitios oficiales para conocer mas sobre los servicios, planes y ecosistema de cada solucion.",
                style = MaterialTheme.typography.bodyMedium,
                color = WayaknaNavy.copy(alpha = 0.82f)
            )
            BoxWithConstraints {
                val compact = maxWidth < 420.dp

                if (compact) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedButton(onClick = onOpenWayakna, modifier = Modifier.fillMaxWidth()) {
                            Text("Wayakna")
                        }
                        OutlinedButton(onClick = onOpenCorreobox, modifier = Modifier.fillMaxWidth()) {
                            Text("CorreoBox")
                        }
                        OutlinedButton(onClick = onOpenOdoo, modifier = Modifier.fillMaxWidth()) {
                            Text("Odoo")
                        }
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        OutlinedButton(onClick = onOpenWayakna) {
                            Text("Wayakna")
                        }
                        OutlinedButton(onClick = onOpenCorreobox) {
                            Text("CorreoBox")
                        }
                        OutlinedButton(onClick = onOpenOdoo) {
                            Text("Odoo")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MiniStat(
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = WayaknaCard)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                color = WayaknaNavy
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    description: String
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = WayaknaWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = WayaknaNavy
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
