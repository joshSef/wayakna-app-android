package com.joshua.wayaknacatalog.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joshua.wayaknacatalog.data.FavoritesStore
import com.joshua.wayaknacatalog.data.PackageRepository
import com.joshua.wayaknacatalog.data.QuoteStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageDetailScreen(
    packageId: String,
    onBack: () -> Unit,
    onOpenQuote: () -> Unit,
    onContact: () -> Unit
) {
    val pkg = PackageRepository.findById(packageId)
    val isOdooPackage = pkg?.category == "Odoo Wayakna"
    val favorite = pkg?.let { FavoritesStore.isFavorite(it.id) } == true
    val quoted = pkg?.let { QuoteStore.contains(it.id) } == true

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Detalle del paquete") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    TextButton(onClick = onOpenQuote) {
                        Text("Cotizador")
                    }
                }
            )
        }
    ) { pad ->
        if (pkg == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pad)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("No encontramos este paquete.", style = MaterialTheme.typography.headlineSmall)
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BoxWithConstraints {
                    val compact = maxWidth < 620.dp

                    if (isOdooPackage) {
                        if (compact) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                OdooSummaryCard(
                                    name = pkg.name,
                                    summary = pkg.summary,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                PriceCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    metric = pkg.timeline,
                                    highlight = pkg.valueProposition,
                                    price = pkg.price,
                                    accent = odooAccentColor(pkg.id)
                                )
                            }
                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                OdooSummaryCard(
                                    name = pkg.name,
                                    summary = pkg.summary,
                                    modifier = Modifier.weight(1.2f)
                                )
                                PriceCard(
                                    modifier = Modifier.weight(0.9f),
                                    metric = pkg.timeline,
                                    highlight = pkg.valueProposition,
                                    price = pkg.price,
                                    accent = odooAccentColor(pkg.id)
                                )
                            }
                        }
                    } else {
                        Card(
                            shape = RoundedCornerShape(28.dp),
                            colors = CardDefaults.cardColors(containerColor = WayaknaMint)
                        ) {
                            Column(
                                modifier = Modifier.padding(22.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    text = pkg.name,
                                    style = MaterialTheme.typography.displaySmall,
                                    color = WayaknaNavy
                                )
                                Text(
                                    text = pkg.summary,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = WayaknaNavy.copy(alpha = 0.88f)
                                )
                                Text(
                                    text = pkg.price,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = WayaknaNavy
                                )
                            }
                        }
                    }
                }
            }

            item {
                InsightCard(
                    title = "Propuesta principal",
                    body = pkg.valueProposition
                )
            }

            item {
                BoxWithConstraints {
                    val compact = maxWidth < 560.dp

                    if (compact) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            InsightCard(
                                modifier = Modifier.fillMaxWidth(),
                                title = "Ideal para",
                                body = pkg.idealFor
                            )
                            InsightCard(
                                modifier = Modifier.fillMaxWidth(),
                                title = "Tiempo estimado",
                                body = pkg.timeline
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            InsightCard(
                                modifier = Modifier.weight(1f),
                                title = "Ideal para",
                                body = pkg.idealFor
                            )
                            InsightCard(
                                modifier = Modifier.weight(1f),
                                title = "Tiempo estimado",
                                body = pkg.timeline
                            )
                        }
                    }
                }
            }

            item {
                SectionCard(title = "Incluye") {
                    pkg.details.forEach { detail ->
                        BulletLine(text = detail)
                    }
                }
            }

            item {
                SectionCard(title = "Resultados esperados") {
                    pkg.outcomes.forEach { outcome ->
                        OutcomePill(text = outcome)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            item {
                TextButton(
                    onClick = { FavoritesStore.toggle(pkg.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (favorite) "Quitar de guardados" else "Guardar paquete")
                }
            }

            item {
                TextButton(
                    onClick = { QuoteStore.toggle(pkg.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (quoted) "Quitar del cotizador" else "Agregar al cotizador")
                }
            }

            item {
                Button(
                    onClick = onContact,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Solicitar informacion")
                }
            }
        }
    }
}

@Composable
private fun OdooSummaryCard(
    name: String,
    summary: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = WayaknaInk)
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.displaySmall,
                color = WayaknaWhite
            )
            Text(
                text = summary,
                style = MaterialTheme.typography.bodyLarge,
                color = WayaknaWhite.copy(alpha = 0.88f)
            )
        }
    }
}

@Composable
private fun InsightCard(
    modifier: Modifier = Modifier,
    title: String,
    body: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = WayaknaNavy
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = WayaknaCard)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = WayaknaNavy
            )
            content()
        }
    }
}

@Composable
private fun BulletLine(text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier
                .padding(top = 7.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(WayaknaGreen)
                .height(8.dp)
                .fillMaxWidth(0.03f)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OutcomePill(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun PriceCard(
    modifier: Modifier = Modifier,
    metric: String,
    highlight: String,
    price: String,
    accent: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = accent)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 22.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = metric,
                style = MaterialTheme.typography.titleMedium,
                color = WayaknaNavy
            )
            Text(
                text = highlight,
                style = MaterialTheme.typography.titleLarge,
                color = WayaknaNavy
            )
            Text(
                text = price,
                style = MaterialTheme.typography.headlineSmall,
                color = WayaknaNight
            )
        }
    }
}

private fun odooAccentColor(packageId: String) = when (packageId) {
    "via" -> WayaknaSilver
    "rastro" -> WayaknaSky
    else -> WayaknaMintStrong
}
