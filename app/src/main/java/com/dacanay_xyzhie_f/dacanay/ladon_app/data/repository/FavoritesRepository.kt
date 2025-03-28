import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.FavoriteResponse
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager

class FavoritesRepository(private val tokenManager: TokenManager) {

    suspend fun getFavorites(): List<FavoriteResponse> {
        val token = tokenManager.getToken() ?: return emptyList()
        return RetrofitInstance.api.getFavorites("Bearer $token")
    }

    suspend fun addFavorite(productId: Int) {
        val token = tokenManager.getToken() ?: return
        RetrofitInstance.api.addFavorite("Bearer $token", mapOf("product_id" to productId))
    }

    suspend fun removeFavorite(productId: Int) {
        val token = tokenManager.getToken() ?: return
        RetrofitInstance.api.removeFavorite("Bearer $token", mapOf("product_id" to productId))
    }
}

