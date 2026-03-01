package com.joshua.wayaknacatalog.data

object PackageRepository {
    fun getAll(): List<ConsultingPackage> = listOf(
        ConsultingPackage(
            id = "odoo_starter",
            category = "Odoo",
            name = "Paquete Odoo Starter",
            summary = "Implementación base para PYMES",
            details = listOf(
                "Levantamiento de requerimientos",
                "Configuración inicial (CRM, Ventas, Inventario)",
                "Capacitación básica",
                "Acompañamiento de arranque"
            )
        ),
        ConsultingPackage(
            id = "correobox_growth",
            category = "Correobox",
            name = "Correobox Growth",
            summary = "Campañas de correo masivo y automatización",
            details = listOf(
                "Configuración de cuenta y dominio",
                "Plantillas base",
                "Segmentación inicial",
                "Reporte de métricas"
            )
        )
    )

    fun findById(id: String): ConsultingPackage? =
        getAll().firstOrNull { it.id == id }
}