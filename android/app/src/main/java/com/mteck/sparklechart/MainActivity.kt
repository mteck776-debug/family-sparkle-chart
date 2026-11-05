package com.mteck.sparklechart

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.webkit.WebViewAssetLoader
import java.util.Locale

class MainActivity : Activity() {
    private lateinit var web: WebView
    private var tts: TextToSpeech? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("en", "AU")
                tts?.setPitch(1.25f)
            }
        }

        val assets = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        web = WebView(this)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.allowFileAccess = false
        web.settings.allowContentAccess = false
        web.addJavascriptInterface(Bridge(), "AndroidChart")
        web.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? =
                assets.shouldInterceptRequest(request.url)

            // Only the chart runs in the app. Links, like the GitHub token page, open in the browser.
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                if (request.url.host == HOST) return false
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(request.url.toString())))
                } catch (_: ActivityNotFoundException) {
                }
                return true
            }
        }
        setContentView(web)
        web.loadUrl("https://$HOST/assets/index.html")
    }

    override fun onResume() {
        super.onResume()
        web.onResume()
    }

    override fun onPause() {
        web.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        tts?.shutdown()
        web.destroy()
        super.onDestroy()
    }

    /** Android's WebView has no built-in speech, so the voice cheer goes through the phone's text-to-speech. */
    private inner class Bridge {
        @JavascriptInterface
        fun speak(text: String) {
            tts?.speak(text.take(200), TextToSpeech.QUEUE_FLUSH, null, "cheer")
        }
    }

    private companion object {
        const val HOST = "appassets.androidplatform.net"
    }
}
