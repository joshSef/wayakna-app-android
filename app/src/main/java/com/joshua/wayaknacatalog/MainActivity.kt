package com.joshua.wayaknacatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.joshua.wayaknacatalog.data.FavoritesStore
import com.joshua.wayaknacatalog.nav.AppNav
import com.joshua.wayaknacatalog.ui.theme.WayaknaCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FavoritesStore.init(applicationContext)
        enableEdgeToEdge()
        setContent {
            WayaknaCatalogTheme {
                AppNav()
            }
        }
    }
}
