package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewAddressScreen(
    navController: NavHostController,
    onSaveAddress: (String) -> Unit
) {
    var barangay by remember { mutableStateOf("") }
    var municipality by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }
    var setAsDefault by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Address", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Address Information", fontWeight = FontWeight.Bold)

            OutlinedTextField(
                value = barangay,
                onValueChange = { barangay = it },
                label = { Text("Barangay") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = municipality,
                onValueChange = { municipality = it },
                label = { Text("Municipality") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = province,
                onValueChange = { province = it },
                label = { Text("Province") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Settings", fontWeight = FontWeight.Bold)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Set as default")
                Switch(checked = setAsDefault, onCheckedChange = { setAsDefault = it })
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val fullAddress = "$barangay, $municipality, $province"
                    onSaveAddress(fullAddress)
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2D55))
            ) {
                Text("Save", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Text(
                text = "By clicking Save, you acknowledge that you have read the Privacy Policy.",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
