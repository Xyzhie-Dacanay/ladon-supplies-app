package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.WebView

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CheckoutWebViewScreen(url: String, onCheckoutComplete: () -> Unit) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        url?.let {
                            if (it.contains("/mobile-success-page")) {
                                onCheckoutComplete()
                            }
                        }
                    }
                }
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

