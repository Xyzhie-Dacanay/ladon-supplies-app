package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import base64ToImageBitmap
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.CartViewModel
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import kotlinx.coroutines.launch

data class AddressEntry(
    val address: String,
    val isDefault: Boolean = false
)

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddtoCartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    savedAddresses: SnapshotStateList<AddressEntry>,
    selectedAddress: String,
    onAddressSelected: (String) -> Unit
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var showAddressModal by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        cartViewModel.fetchCartItems()
    }

    val allChecked = remember(cartItems) {
        mutableStateListOf<Boolean>().apply {
            addAll(List(cartItems.size) { true })
        }
    }

    val localQuantities = remember { mutableStateMapOf<Int, Int>() }

    // 🔁 Sync localQuantities whenever cartItems change
    LaunchedEffect(cartItems) {
        localQuantities.clear()
        cartItems.forEach { localQuantities[it.product_id] = it.quantity }
    }

    val totalPrice = cartItems.mapIndexed { index, item ->
        if (allChecked.getOrNull(index) == true) {
            val qty = localQuantities[item.product_id] ?: item.quantity
            item.product_price * qty
        } else 0.0
    }.sum()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Shopping cart (${cartItems.size})", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Row(
                            modifier = Modifier.clickable { showAddressModal = true },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.AddLocation, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = selectedAddress.take(30) + if (selectedAddress.length > 30) "..." else "",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color(0xFFE6F8FF)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            if (cartItems.isEmpty()) {
                Text(
                    text = "Your cart is empty",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                cartItems.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(2.dp, Color(0xFF0080FF), RoundedCornerShape(12.dp))
                            .background(Color(0xFFD3ECFF))
                            .padding(12.dp)
                            .padding(bottom = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = allChecked[index],
                                onCheckedChange = { allChecked[index] = it }
                            )

                            Spacer(Modifier.width(8.dp))

                            Image(
                                bitmap = base64ToImageBitmap(context, item.product_image ?: ""),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.White),
                                contentScale = ContentScale.Fit
                            )

                            Spacer(Modifier.width(12.dp))

                            Column(Modifier.weight(1f)) {
                                Text(item.product_name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("₱${item.product_price}", fontSize = 14.sp, color = Color(0xFF27AE60))
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    val currentQty = localQuantities[item.product_id] ?: item.quantity
                                    if (currentQty > 1) {
                                        localQuantities[item.product_id] = currentQty - 1
                                        cartViewModel.updateCartQuantity(item.product_id, -1) // ➖ Delta
                                    }
                                }) {
                                    Text("-", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                }

                                Text(localQuantities[item.product_id]?.toString() ?: item.quantity.toString(), fontSize = 18.sp)

                                IconButton(onClick = {
                                    val currentQty = localQuantities[item.product_id] ?: item.quantity
                                    if (currentQty < item.stock) {
                                        localQuantities[item.product_id] = currentQty + 1
                                        cartViewModel.updateCartQuantity(item.product_id, 1) // ➕ Delta
                                    } else {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Reached max stock limit.")
                                        }
                                    }
                                }) {
                                    Text("+", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                }

                            }

                            IconButton(onClick = {
                                cartViewModel.removeFromCart(item.product_id)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Item removed from cart")
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color(0xFF0080FF), RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = allChecked.all { it },
                        onCheckedChange = { check ->
                            for (i in allChecked.indices) allChecked[i] = check
                        }
                    )
                    Text("All", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Total: ₱${"%.2f".format(totalPrice)}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = { navController.navigate("checkout") },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35AEFF)),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text("Check out", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // 📦 Address modal...
            if (showAddressModal) {
                ModalBottomSheet(
                    onDismissRequest = { showAddressModal = false },
                    sheetState = sheetState,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Select address", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        savedAddresses.forEach { entry ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onAddressSelected(entry.address)
                                        showAddressModal = false
                                    }
                                    .padding(vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(Modifier.weight(1f)) {
                                    Text("Xyzhie Dacanay", fontWeight = FontWeight.Bold)
                                    Text("(+63)09********94", fontSize = 13.sp)
                                    Text(entry.address, fontSize = 13.sp)
                                    if (entry.isDefault) Text("Default", fontSize = 12.sp, color = Color.Gray)
                                }
                                RadioButton(selected = selectedAddress == entry.address, onClick = null)
                            }
                            Divider()
                        }

                        Button(
                            onClick = {
                                showAddressModal = false
                                navController.navigate(Routes.AddNewAddressScreen)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35AEFF))
                        ) {
                            Text("Add new address", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}




