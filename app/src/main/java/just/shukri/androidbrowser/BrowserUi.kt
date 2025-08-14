import android.annotation.SuppressLint
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BrowserUi(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
) {
    val defaultUrl = "https://www.google.com/"

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.mediaPlaybackRequiresUserGesture = false // Needed for WebRTC auto-play
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return true
                    }
                }
                webChromeClient = object : WebChromeClient() {
                    override fun onPermissionRequest(request: PermissionRequest) {
                        // Automatically grant camera & mic access
                        request.grant(request.resources)
                    }
                }
                loadUrl(defaultUrl)
            }
        }
    )
}
