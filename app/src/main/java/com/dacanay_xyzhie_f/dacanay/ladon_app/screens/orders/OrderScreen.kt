package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.NavBar

private val PrimaryColor = Color(0xFF0080FF)

data class OrderData(
    val id: String,
    val status: String,
    val total: String,
    val date: String,
    val items: List<String>,
    val paymentMethod: String
)

@Composable
fun OrderScreen(navController: NavHostController) {
    var expandedOrderId by remember { mutableStateOf<String?>(null) }

    val sampleOrders = listOf(
        OrderData(
            id = "ORDER1234",
            status = "Processing",
            total = "₱499.00",
            date = "March 25, 2025",
            items = listOf("Notebook x2", "Eraser", "Ballpen x3"),
            paymentMethod = "GCash"
        ),
        OrderData(
            id = "ORDER1235",
            status = "Shipped",
            total = "₱1,249.00",
            date = "March 26, 2025",
            items = listOf("Crayons", "Scissors", "Ruler", "Glue", "Notebook x5"),
            paymentMethod = "COD"
        ),
        OrderData(
            id = "ORDER1236",
            status = "Delivered",
            total = "₱329.00",
            date = "March 24, 2025",
            items = listOf("Pencil Case", "Markers x2"),
            paymentMethod = "PayPal"
        )
    )

    Scaffold(
        bottomBar = { NavBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE6F8FF))
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.primary),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(100.dp)
                )
                Row {
                    IconButton(onClick = { }) {
                        Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart", tint = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(onClick = { }) {
                        Icon(Icons.Outlined.Notifications, contentDescription = "Notifications", tint = Color.Black)
                    }
                }
            }

            // Table Headers
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Order ID", color = PrimaryColor, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Status", color = PrimaryColor, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Total Amount", color = PrimaryColor, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(sampleOrders) { order ->
                    OrderRow(
                        order = order,
                        isExpanded = expandedOrderId == order.id,
                        onToggleExpand = {
                            expandedOrderId = if (expandedOrderId == order.id) null else order.id
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun OrderRow(
    order: OrderData,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(order.id, modifier = Modifier.weight(1f))
            Text(order.status, modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(order.total)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onToggleExpand() }
                )
            }
        }

        if (isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("• Order Date: ${order.date}", fontSize = 13.sp, color = Color.Gray)
            Text("• Payment Method: ${order.paymentMethod}", fontSize = 13.sp, color = Color.Gray)
            Text("• Products:", fontSize = 13.sp, color = Color.Gray)
            order.items.forEach { item ->
                Text("   - $item", fontSize = 13.sp, color = Color.DarkGray)
            }
        }
    }
}
