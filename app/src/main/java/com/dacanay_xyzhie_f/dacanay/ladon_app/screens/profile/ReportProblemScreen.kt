package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.profile

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.TicketRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodels.TicketViewModel
import kotlinx.coroutines.launch
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportProblemScreen(
    navController: NavController,
    tokenManager: TokenManager,
    ticketViewModel: TicketViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val ticketOptions = listOf(
        "Report a Problem" to "report_problem",
        "Feature Request" to "feature_request",
        "Comment" to "comment",
        "Other" to "other"
    )
    var selectedDisplay by remember { mutableStateOf(ticketOptions[0].first) }
    var selectedEnum by remember { mutableStateOf(ticketOptions[0].second) }

    var subject by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var screenshot by remember { mutableStateOf<String?>(null) }
    var previewBitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

    val ticketState by ticketViewModel.ticketState.collectAsState()

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val inputStream: InputStream? = context.contentResolver.openInputStream(it)
            val bytes = inputStream?.readBytes()
            inputStream?.close()
            if (bytes != null) {
                val base64 = Base64.encodeToString(bytes, Base64.NO_WRAP)
                screenshot = "data:image/png;base64,$base64"
                previewBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            }
        }
    }

    LaunchedEffect(ticketState) {
        ticketState?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            if (it.contains("success", ignoreCase = true)) {
                navController.popBackStack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report a Problem", fontWeight = FontWeight.Bold, fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.8f)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFEAF4FA))
            )
        },
        containerColor = Color(0xFFEAF4FA)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Subject", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    placeholder = { Text("e.g. App crashes on login") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = colorResource(id = com.dacanay_xyzhie_f.dacanay.ladon_app.R.color.black),
                        containerColor = colorResource(id = R.color.tfBackground),
                    ),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions.Default,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Ticket Type", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                var expanded by remember { mutableStateOf(false) }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedDisplay,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .menuAnchor(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.LightGray,
                            cursorColor = colorResource(id = R.color.black),
                            containerColor = colorResource(id = R.color.tfBackground),
                        ),
                        shape = RoundedCornerShape(20.dp),
                        keyboardOptions = KeyboardOptions.Default,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) }
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        ticketOptions.forEach { (label, value) ->
                            DropdownMenuItem(
                                text = { Text(label) },
                                onClick = {
                                    selectedDisplay = label
                                    selectedEnum = value
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Attach a Screenshot (Optional)", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clickable {
                            filePickerLauncher.launch("image/*")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (previewBitmap != null) {
                        Image(
                            bitmap = previewBitmap!!.asImageBitmap(),
                            contentDescription = "Selected image",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.UploadFile, contentDescription = "Upload", modifier = Modifier.size(40.dp))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("IMPORT A FILE", color = Color.Black)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Additional Details", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("Please describe your issue...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = colorResource(id = R.color.black),
                        containerColor = colorResource(id = R.color.tfBackground),
                    ),
                    shape = RoundedCornerShape(20.dp),
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            val token = tokenManager.getToken()
                            if (token != null) {
                                val request = TicketRequest(
                                    subject = subject,
                                    description = description,
                                    ticket_type = selectedEnum,
                                    screenshot = screenshot
                                )
                                ticketViewModel.submitTicket(token, request)
                            } else {
                                Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF3498DB)),
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("SUBMIT", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

    }
}
