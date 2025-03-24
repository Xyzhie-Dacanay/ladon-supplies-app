import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage


// Component for the actual product
@Composable
fun ProductCard(
    productName: String,
    productId: Int,
    productPrice: String,
    productImage: Int,
    navController: NavController,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,

    ) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .width(200.dp)
            .height(220.dp)
            .padding(8.dp)
            .clickable {

                navController.navigate("product_details/$productId")

            } ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Product Image
            AsyncImage(
                model = productImage,
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(top = 8.dp)
            )



            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                // Product Name
                Text(
                    text = productName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Product Price
                Text(
                    text = productPrice,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
        }
    }
}
