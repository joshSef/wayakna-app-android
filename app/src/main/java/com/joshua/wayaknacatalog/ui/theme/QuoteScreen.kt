package com.joshua.wayaknacatalog.ui.theme

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.joshua.wayaknacatalog.data.BillingType
import com.joshua.wayaknacatalog.data.ConsultingPackage
import com.joshua.wayaknacatalog.data.QuoteStore
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen(
    onBack: () -> Unit,
    onOpenPackage: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    val packages = QuoteStore.packages()
    val oneTimeSubtotal = QuoteStore.subtotalOneTime()
    val monthlySubtotal = QuoteStore.subtotalMonthly()
    val summaryMessage = buildQuoteSummary(packages, oneTimeSubtotal, monthlySubtotal)

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Cotizador") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    if (packages.isNotEmpty()) {
                        TextButton(onClick = { QuoteStore.clear() }) {
                            Text("Limpiar")
                        }
                    }
                }
            )
        }
    ) { pad ->
        if (packages.isEmpty()) {
            QuoteEmptyState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pad),
                onBack = onBack
            )
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = WayaknaWhite)
                ) {
                    Column(
                        modifier = Modifier.padding(22.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Resumen comercial",
                            style = MaterialTheme.typography.headlineSmall,
                            color = WayaknaNavy
                        )
                        Text(
                            text = "Selecciona los servicios de interes y genera una solicitud lista para contacto por WhatsApp o correo.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            items(packages) { packageItem ->
                QuoteItemCard(
                    packageItem = packageItem,
                    onOpenPackage = { onOpenPackage(packageItem.id) },
                    onRemove = { QuoteStore.remove(packageItem.id) }
                )
            }

            item {
                QuoteSummaryCard(
                    packageCount = QuoteStore.count(),
                    oneTimeSubtotal = oneTimeSubtotal,
                    monthlySubtotal = monthlySubtotal
                )
            }

            item {
                BoxWithConstraints {
                    val compact = maxWidth < 420.dp

                    if (compact) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Button(
                                onClick = {
                                    uriHandler.openUri("https://wa.me/529992519748?text=${Uri.encode(summaryMessage)}")
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Enviar por WhatsApp")
                            }
                            OutlinedButton(
                                onClick = {
                                    uriHandler.openUri(
                                        "mailto:contacto@wayakna.com?subject=${Uri.encode("Solicitud de cotizacion Wayakna")}&body=${Uri.encode(summaryMessage)}"
                                    )
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Enviar por correo")
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = {
                                    uriHandler.openUri("https://wa.me/529992519748?text=${Uri.encode(summaryMessage)}")
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Enviar por WhatsApp")
                            }
                            OutlinedButton(
                                onClick = {
                                    uriHandler.openUri(
                                        "mailto:contacto@wayakna.com?subject=${Uri.encode("Solicitud de cotizacion Wayakna")}&body=${Uri.encode(summaryMessage)}"
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Enviar por correo")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuoteEmptyState(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    Box(
        modifier = modifier.padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(colors = CardDefaults.cardColors(containerColor = WayaknaWhite)) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tu cotizador esta vacio.",
                    style = MaterialTheme.typography.titleLarge,
                    color = WayaknaNavy
                )
                Text(
                    text = "Agrega paquetes desde el catalogo, detalle o guardados para construir una propuesta comercial.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Button(onClick = onBack) {
                    Text("Explorar servicios")
                }
            }
        }
    }
}

@Composable
private fun QuoteItemCard(
    packageItem: ConsultingPackage,
    onOpenPackage: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = packageItem.name,
                style = MaterialTheme.typography.titleLarge,
                color = WayaknaNavy
            )
            Text(
                text = packageItem.category,
                style = MaterialTheme.typography.labelLarge,
                color = WayaknaSlate
            )
            Text(
                text = packageItem.price,
                style = MaterialTheme.typography.titleMedium,
                color = WayaknaGreen.copy(alpha = 0.72f)
            )
            Text(
                text = packageItem.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            BoxWithConstraints {
                val compact = maxWidth < 420.dp

                if (compact) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = onOpenPackage, modifier = Modifier.fillMaxWidth()) {
                            Text("Ver detalle")
                        }
                        TextButton(onClick = onRemove, modifier = Modifier.fillMaxWidth()) {
                            Text("Quitar del cotizador")
                        }
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        TextButton(onClick = onOpenPackage) {
                            Text("Ver detalle")
                        }
                        TextButton(onClick = onRemove) {
                            Text("Quitar del cotizador")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QuoteSummaryCard(
    packageCount: Int,
    oneTimeSubtotal: Double,
    monthlySubtotal: Double
) {
    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = WayaknaCard)
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Resumen de cotizacion",
                style = MaterialTheme.typography.titleLarge,
                color = WayaknaNavy
            )
            Text(
                text = "Paquetes seleccionados: $packageCount",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Subtotal Odoo Wayakna: ${formatCurrency(oneTimeSubtotal, BillingType.ONE_TIME)}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Subtotal CorreoBox mensual: ${formatCurrency(monthlySubtotal, BillingType.MONTHLY)}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Los importes se muestran por separado para no mezclar servicios de implementacion con planes mensuales.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun buildQuoteSummary(
    packages: List<ConsultingPackage>,
    oneTimeSubtotal: Double,
    monthlySubtotal: Double
): String {
    val lines = buildList {
        add("Solicitud de cotizacion - Wayakna Catalog")
        add("")
        add("Paquetes seleccionados:")
        packages.forEach { add("- ${it.name} (${it.price})") }
        add("")
        add("Subtotal Odoo Wayakna: ${formatCurrency(oneTimeSubtotal, BillingType.ONE_TIME)}")
        add("Subtotal CorreoBox mensual: ${formatCurrency(monthlySubtotal, BillingType.MONTHLY)}")
        add("")
        add("Nota: los conceptos se muestran por separado para no mezclar implementacion y mensualidades.")
    }
    return lines.joinToString("\n")
}

private fun formatCurrency(amount: Double, billingType: BillingType): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    return if (billingType == BillingType.MONTHLY) {
        val format = DecimalFormat("#,##0.00", symbols)
        "$${format.format(amount)} MXN / mes"
    } else {
        val format = DecimalFormat("#,##0", symbols)
        "$${format.format(amount)} MXN"
    }
}
