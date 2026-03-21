package com.joshua.wayaknacatalog.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf

object FavoritesStore {
    private const val PREFS_NAME = "wayakna_favorites"
    private const val KEY_IDS = "favorite_ids"

    private val favoriteIds = mutableStateListOf<String>()
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
        favoriteIds.clear()
        favoriteIds.addAll(saved)
        initialized = true
    }

    fun ids(): List<String> = favoriteIds.toList()

    fun isFavorite(packageId: String): Boolean = favoriteIds.contains(packageId)

    fun toggle(packageId: String) {
        if (favoriteIds.contains(packageId)) {
            favoriteIds.remove(packageId)
        } else {
            favoriteIds.add(packageId)
        }
        persist()
    }

    fun favoritePackages(): List<ConsultingPackage> =
        PackageRepository.getAll().filter { favoriteIds.contains(it.id) }

    private fun persist() {
        if (!initialized) return
        appContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(KEY_IDS, favoriteIds.toSet())
            .apply()
    }
}
