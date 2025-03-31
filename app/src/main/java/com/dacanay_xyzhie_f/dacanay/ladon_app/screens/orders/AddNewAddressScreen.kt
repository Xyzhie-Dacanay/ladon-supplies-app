package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import android.widget.Toast
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.AddressRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.ViewModel.AddressViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewAddressScreen(
    navController: NavHostController,
    onSaveAddress: (String) -> Unit,
    addressViewModel: AddressViewModel = viewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var street by remember { mutableStateOf("") }
    var barangay by remember { mutableStateOf("") }
    var municipality by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }

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
                value = street,
                onValueChange = { street = it },
                label = { Text("Street") },
                modifier = Modifier.fillMaxWidth()
            )

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

            if (isSaving) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val fullAddress = "$street, $barangay, $municipality, $province"

                    if (street.isBlank() || barangay.isBlank() || municipality.isBlank() || province.isBlank()) {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    isSaving = true
                    val addressRequest = AddressRequest(street, barangay, municipality, province)

                    addressViewModel.addAddress(addressRequest) { success, message ->
                        isSaving = false
                        if (success) {
                            Toast.makeText(context, "Address saved", Toast.LENGTH_SHORT).show()
                            onSaveAddress(fullAddress)
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Failed: $message", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                enabled = !isSaving,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35AEFF))
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
