package com.joshua.wayaknacatalog.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf

object QuoteStore {
    private const val PREFS_NAME = "wayakna_quote"
    private const val KEY_IDS = "quote_ids"

    private val packageIds = mutableStateListOf<String>()
    private var initialized = false
    private lateinit var appContext: Context

    fun init(context: Context) {
        if (initialized) return
        appContext = context.applicationContext
        val saved = appContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getStringSet(KEY_IDS, emptySet())
            .orEmpty()
            .sorted()
        packageIds.clear()
        packageIds.addAll(saved)
        initialized = true
    }

    fun packages(): List<ConsultingPackage> =
        PackageRepository.getAll().filter { packageIds.contains(it.id) }

    fun contains(packageId: String): Boolean = packageIds.contains(packageId)

    fun add(packageId: String) {
        if (!packageIds.contains(packageId)) {
            packageIds.add(packageId)
            persist()
        }
    }

    fun remove(packageId: String) {
        if (packageIds.remove(packageId)) {
            persist()
        }
    }

    fun toggle(packageId: String) {
        if (contains(packageId)) remove(packageId) else add(packageId)
    }

    fun clear() {
        packageIds.clear()
        persist()
    }

    fun count(): Int = packageIds.size

    fun subtotalOneTime(): Double =
        packages()
            .filter { it.billingType == BillingType.ONE_TIME }
            .sumOf { it.priceAmount }

    fun subtotalMonthly(): Double =
        packages()
            .filter { it.billingType == BillingType.MONTHLY }
            .sumOf { it.priceAmount }

    private fun persist() {
        if (!initialized) return
        appContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(KEY_IDS, packageIds.toSet())
            .apply()
    }
}
