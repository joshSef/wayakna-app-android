package com.joshua.wayaknacatalog.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joshua.wayaknacatalog.data.ConsultingPackage
import com.joshua.wayaknacatalog.data.FavoritesStore
import com.joshua.wayaknacatalog.data.PackageRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackagesScreen(
    onBack: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenPackage: (String) -> Unit
) {
    val packages = PackageRepository.getAll()
    val tabs = listOf("Odoo Wayakna", "CorreoBox")
    var selectedTab by remember { mutableIntStateOf(0) }
    val filteredPackages = packages.filter { it.category == tabs[selectedTab] }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Portafolio Wayakna") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    TextButton(onClick = onOpenFavorites) {
                        Text("Guardados")
                    }
                }
            )
        }
    ) { pad ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    shape = RoundedCornerShape(26.dp),
                    colors = CardDefaults.cardColors(containerColor = WayaknaWhite)
                ) {
                    Column(
                        modifier = Modifier.padding(22.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "Portafolio de servicios",
                            style = MaterialTheme.typography.headlineSmall,
                            color = WayaknaNavy
                        )
                        Text(
                            text = "Explora paquetes de consultoria Odoo Wayakna y planes CorreoBox desde una sola experiencia.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item {
                PrimaryTabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }

            items(filteredPackages) { packageItem ->
                PackageCard(
                    packageItem = packageItem,
                    onClick = { onOpenPackage(packageItem.id) }
                )
            }
        }
    }
}

@Composable
fun PackageCard(
    packageItem: ConsultingPackage,
    onClick: () -> Unit
) {
    val favorite = FavoritesStore.isFavorite(packageItem.id)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BoxWithConstraints {
                val compact = maxWidth < 360.dp

                if (compact) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        CategoryPill(packageItem.category)
                        CategoryPill(packageItem.timeline, secondary = true)
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        CategoryPill(packageItem.category)
                        CategoryPill(packageItem.timeline, secondary = true)
                    }
                }
            }
            Text(
                text = packageItem.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = packageItem.summary,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = packageItem.valueProposition,
                style = MaterialTheme.typography.bodyMedium,
                color = WayaknaNavy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = packageItem.price,
                style = MaterialTheme.typography.titleMedium,
                color = WayaknaGreen.copy(alpha = 0.72f)
            )
            Text(
                text = packageItem.idealFor,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            TextButton(
                onClick = { FavoritesStore.toggle(packageItem.id) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (favorite) "Quitar de guardados" else "Guardar paquete")
            }
        }
    }
}

@Composable
private fun CategoryPill(
    text: String,
    secondary: Boolean = false
) {
    val background = if (secondary) {
        WayaknaMint.copy(alpha = 0.95f)
    } else {
        WayaknaCard
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(background)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = WayaknaNavy
        )
    }
}
