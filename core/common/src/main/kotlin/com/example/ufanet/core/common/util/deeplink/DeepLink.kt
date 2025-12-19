package com.example.ufanet.core.common.util.deeplink

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import javax.inject.Inject

class DeepLink @Inject constructor(
    private val context: Context,
) {
    fun openWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        intent.resolveActivity(context.packageManager)?.let {
            context.startActivity(intent)
        } ?: run {
            val webIntent = Intent(Intent.ACTION_VIEW, url.toUri())
            webIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(webIntent)
        }
    }
}