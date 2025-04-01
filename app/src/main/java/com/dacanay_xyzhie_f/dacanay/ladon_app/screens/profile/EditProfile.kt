import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.UpdateProfileRequest
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import java.io.ByteArrayOutputStream
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


fun resizeBitmap(bitmap: Bitmap, maxSize: Int): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val ratio = width.toFloat() / height.toFloat()
    return if (ratio > 1) {
        Bitmap.createScaledBitmap(bitmap, maxSize, (maxSize / ratio).toInt(), true)
    } else {
        Bitmap.createScaledBitmap(bitmap, (maxSize * ratio).toInt(), maxSize, true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    tokenManager: TokenManager
) {
    var username by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var imageBase64 by remember { mutableStateOf(authViewModel.profileImageBase64) }
    var isLoading by remember { mutableStateOf(false) }

    // Field error states
    var usernameError by remember { mutableStateOf(false) }
    var fullnameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var contactError by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        username = authViewModel.loggedInUserName
        email = authViewModel.email
        contact = authViewModel.contactNumber
    }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            snackbarMessage = null
        }
    }

    LaunchedEffect(Unit) {
        authViewModel.profileImageBase64?.let { encoded ->
            val cleanBase64 = encoded.substringAfter("base64,", encoded)
            try {
                val decodedBytes = Base64.decode(cleanBase64, Base64.DEFAULT)
                imageBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size).asImageBitmap()
            } catch (e: Exception) {
                imageBitmap = null
            }
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                val stream = context.contentResolver.openInputStream(uri)
                stream?.use {
                    val originalBitmap = BitmapFactory.decodeStream(it)
                    val resized = resizeBitmap(originalBitmap, 512)
                    val outputStream = ByteArrayOutputStream()
                    resized.compress(Bitmap.CompressFormat.JPEG, 40, outputStream)
                    val byteArray = outputStream.toByteArray()

                    imageBase64 = Base64.encodeToString(byteArray, Base64.NO_WRAP)
                    imageBitmap = resized.asImageBitmap()
                }
            }
        }
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Profile",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE6F8FF))
            )
        },
        containerColor = Color(0xFFE6F8FF)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 48.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Profile Image
                    Box(contentAlignment = Alignment.Center) {
                        Box(
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageBitmap != null) {
                                Image(bitmap = imageBitmap!!, contentDescription = "Profile", modifier = Modifier.fillMaxSize())
                            } else {
                                Icon(Icons.Default.Person, contentDescription = "Profile", modifier = Modifier.size(80.dp))
                            }
                        }

                        IconButton(
                            onClick = { launcher.launch("image/*") },
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = 1.dp, y = 1.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CameraAlt,
                                contentDescription = "Edit Profile",
                                tint = Color.White,
                                modifier = Modifier
                                    .background(Color(0xFF35AEFF), shape = CircleShape)
                                    .padding(4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(36.dp))

                    // Username
                    LabelText("Username")
                    UserNameEdit(username, onValueChange = {
                        username = it
                        usernameError = false
                    }, error = usernameError)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Full Name
                    LabelText("Full Name")
                    FullNameEdit(fullname, onValueChange = {
                        fullname = it
                        fullnameError = false
                    }, error = fullnameError)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email
                    LabelText("Email")
                    EmailEdit(email, onValueChange = {
                        email = it
                        emailError = false
                    }, error = emailError)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Contact
                    LabelText("Contact")
                    ContactEdit(contact, onValueChange = {
                        if (it.length <= 11 && it.all { char -> char.isDigit() }) {
                            contact = it
                            contactError = false
                        }
                    }, error = contactError)

                    Spacer(modifier = Modifier.height(32.dp))

                    // Save Button
                    Button(
                        onClick = {
                            usernameError = username.isBlank()
                            fullnameError = fullname.isBlank()
                            emailError = email.isBlank() || !email.matches(Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$"))
                            contactError = contact.isBlank() || contact.length != 11

                            if (usernameError || fullnameError || emailError || contactError) {
                                snackbarMessage = "Please fill all fields correctly"
                                return@Button
                            }

                            isLoading = true
                            val updateRequest = UpdateProfileRequest(
                                name = username,
                                fullname = fullname,
                                email = email,
                                contact = contact,
                                profile_image = if (imageBase64 != authViewModel.profileImageBase64) imageBase64 else null
                            )

                            authViewModel.updateUserProfile(
                                tokenManager = tokenManager,
                                request = updateRequest,
                                onSuccess = {
                                    isLoading = false
                                    snackbarMessage = "Profile updated"
                                    authViewModel.profileImageBase64 = imageBase64

                                    coroutineScope.launch {
                                        tokenManager.saveUserData(
                                            token = tokenManager.getToken() ?: "",
                                            userId = authViewModel.loggedInUserId,
                                            userName = authViewModel.loggedInUserName,
                                            profileImageBase64 = imageBase64
                                        )
                                    }
                                    navController.popBackStack()
                                },
                                onError = {
                                    isLoading = false
                                    snackbarMessage = "Failed: $it"
                                }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF35AEFF))
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(20.dp))
                        } else {
                            Text("SAVE", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    error: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(
                width = if (error) 2.dp else 0.dp,
                color = if (error) Color.Red else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (error) Color.Red else Color(0xFF35AEFF),
            unfocusedBorderColor = if (error) Color.Red else Color.Gray
        )
    )
}