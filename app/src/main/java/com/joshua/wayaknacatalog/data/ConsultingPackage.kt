package com.joshua.wayaknacatalog.data

data class ConsultingPackage(
    val id: String,
    val category: String,
    val name: String,
    val summary: String,
    val price: String,
    val idealFor: String,
    val timeline: String,
    val valueProposition: String,
    val details: List<String>,
    val outcomes: List<String>
)
