package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders

import androidx.compose.foundation.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.NavBar
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.OrderViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes

private val PrimaryColor = Color(0xFF0080FF)

@Composable
fun OrderScreen(navController: NavHostController) {
    val viewModel: OrderViewModel = viewModel()
    val orders by viewModel.orders.collectAsState()
    var expandedOrderId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchOrders()
    }

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
                    IconButton(onClick = {
                        navController.navigate(Routes.AddtoCartScreen)
                    }) {
                        Icon(Icons.Outlined.ShoppingCart,
                            contentDescription = "Cart",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(onClick = { /* Notifications */ }) {
                        Icon(Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Black)
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
                Text("Total", color = PrimaryColor, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }

            Divider(color = Color.LightGray, thickness = 1.dp)

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(orders) { order ->
                    OrderRow(
                        orderId = order.order_id,
                        status = order.status,
                        total = order.total,
                        date = order.date,
                        paymentMethod = order.payment_method,
                        items = order.items,
                        isExpanded = expandedOrderId == order.order_id,
                        onToggleExpand = {
                            expandedOrderId = if (expandedOrderId == order.order_id) null else order.order_id
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun OrderRow(
    orderId: String,
    status: String,
    total: String,
    date: String,
    paymentMethod: String,
    items: List<String>,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(orderId, modifier = Modifier.weight(1f))
            Text(status, modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(total)
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
            Text("• Order Date: $date", fontSize = 13.sp, color = Color.Gray)
            Text("• Payment Method: $paymentMethod", fontSize = 13.sp, color = Color.Gray)
            Text("• Products:", fontSize = 13.sp, color = Color.Gray)
            items.forEach { item ->
                Text("   - $item", fontSize = 13.sp, color = Color.DarkGray)
            }
        }
    }
}
