package com.joshua.wayaknacatalog.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class BrandKind {
    WAYAKNA,
    CORREOBOX,
    ODOO
}

@Composable
fun BrandBadge(
    brand: BrandKind,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(WayaknaCard)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BrandMark(brand = brand)
        Text(
            text = brandLabel(brand),
            style = MaterialTheme.typography.labelLarge,
            color = WayaknaNavy,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun BrandMark(brand: BrandKind) {
    Box(
        modifier = Modifier
            .size(26.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        when (brand) {
            BrandKind.WAYAKNA -> WayaknaMark()
            BrandKind.CORREOBOX -> CorreoboxMark()
            BrandKind.ODOO -> OdooMark()
        }
    }
}

@Composable
private fun WayaknaMark() {
    Canvas(modifier = Modifier.size(20.dp)) {
        drawCircle(
            color = WayaknaNavy,
            radius = size.minDimension / 2,
            style = Stroke(width = 2.3.dp.toPx())
        )
        drawArc(
            color = WayaknaGreen,
            startAngle = -30f,
            sweepAngle = 220f,
            useCenter = false,
            topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
            size = Size(size.width - 6.dp.toPx(), size.height - 6.dp.toPx()),
            style = Stroke(width = 2.5.dp.toPx())
        )
        drawCircle(
            color = WayaknaSky,
            radius = size.minDimension / 5,
            center = Offset(size.width * 0.43f, size.height * 0.46f)
        )
    }
}

@Composable
private fun CorreoboxMark() {
    Canvas(modifier = Modifier.size(20.dp)) {
        drawArc(
            color = Color(0xFF165E7A),
            startAngle = -90f,
            sweepAngle = 300f,
            useCenter = false,
            style = Stroke(width = 4.dp.toPx())
        )
        drawArc(
            color = Color(0xFF84B29A),
            startAngle = 70f,
            sweepAngle = 220f,
            useCenter = false,
            style = Stroke(width = 4.dp.toPx())
        )
    }
}

@Composable
private fun OdooMark() {
    Canvas(modifier = Modifier.size(20.dp)) {
        drawCircle(
            color = Color(0xFFC05C97),
            radius = size.minDimension / 2
        )
        drawCircle(
            color = Color.White,
            radius = size.minDimension / 4.1f
        )
    }
}

private fun brandLabel(brand: BrandKind): String = when (brand) {
    BrandKind.WAYAKNA -> "WAYAKNA"
    BrandKind.CORREOBOX -> "CorreoBox"
    BrandKind.ODOO -> "Odoo"
}
