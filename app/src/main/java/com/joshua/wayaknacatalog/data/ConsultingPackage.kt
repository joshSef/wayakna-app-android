package com.joshua.wayaknacatalog.data

data class ConsultingPackage(
    val id: String,
    val category: String,
    val name: String,
    val summary: String,
    val details: List<String>
)