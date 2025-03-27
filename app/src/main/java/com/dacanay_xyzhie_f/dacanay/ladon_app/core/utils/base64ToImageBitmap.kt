import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import java.io.ByteArrayInputStream

fun base64ToImageBitmap(context: Context, base64String: String): ImageBitmap {
    return try {
        val pureBase64 = base64String.substringAfter(",") // Remove "data:image/png;base64," if present
        val decodedBytes = Base64.decode(pureBase64, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(decodedBytes))
        bitmap.asImageBitmap()
    } catch (e: Exception) {
        // Fallback placeholder
        BitmapFactory.decodeResource(
            context.resources,
            R.drawable.placeholder
        ).asImageBitmap()
    }
}