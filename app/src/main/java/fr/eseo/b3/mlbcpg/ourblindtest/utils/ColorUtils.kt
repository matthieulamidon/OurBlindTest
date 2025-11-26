package fr.eseo.b3.mlbcpg.ourblindtest.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.collection.lruCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class DynamicColors(val background: Color, val onBackground: Color)

private val colorCache = lruCache<String, DynamicColors>(10)

suspend fun getDominantColor(context: Context, imageUrl: String): DynamicColors? {
    colorCache.get(imageUrl)?.let { return it }

    return withContext(Dispatchers.IO) {
        try {
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .size(Size(128, 128)) // Sample down the image for faster processing
                .allowHardware(false) // Palette requires software bitmaps
                .build()

            val imageLoader = context.imageLoader
            val result = imageLoader.execute(request).drawable
            val bitmap = result?.toBitmap(config = Bitmap.Config.ARGB_8888)

            bitmap?.let {
                val palette = Palette.from(it).generate()
                val dominantSwatch = palette.dominantSwatch

                dominantSwatch?.let {
                    val dynamicColors = DynamicColors(
                        background = Color(it.rgb),
                        onBackground = Color(it.bodyTextColor)
                    )
                    colorCache.put(imageUrl, dynamicColors)
                    return@withContext dynamicColors
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        null
    }
}