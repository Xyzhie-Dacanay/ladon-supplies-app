package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.storage.TokenManager
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val tokenManager = remember { TokenManager(context) }
    var rememberMe by remember { mutableStateOf(false) }

    val signupSuccess = navController.currentBackStackEntry
        ?.arguments?.getString("signedUp") == "true"

    LaunchedEffect(signupSuccess) {
        if (signupSuccess) {
            Toast.makeText(context, "Registered successfully!", Toast.LENGTH_SHORT).show()
            navController.currentBackStackEntry?.arguments?.remove("signedUp")
        }
    }




    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Observe login result state
    val loginResult = authViewModel.loginResult


    // Handle login feedback
    LaunchedEffect(loginResult) {
        loginResult?.let {
            when (it) {
                "Login successful" -> {
                    Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                    authViewModel.loginResult = null
                }
                "logout" -> {
                    authViewModel.email = ""
                    authViewModel.password = ""
                    authViewModel.loginResult = null
                }
                else -> {
                    errorMessage = it
                    showErrorDialog = true
                    authViewModel.loginResult = null
                }
            }
        }
    }




    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.LogSign) }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE6F7FF)
                )
            )
        },
        containerColor = Color(0xFFE6F7FF),

    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .background(Color(0xFFE6F7FF))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE6F7FF)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE6F7FF)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeadingText(value = stringResource(id = R.string.welcome))
                    Spacer(modifier = Modifier.height(8.dp))
                    DescriptionText(value = stringResource(id = R.string.description_text))
                }

                Spacer(modifier = Modifier.height(36.dp))

                LabelText(value = stringResource(id = R.string.email))
                Spacer(modifier = Modifier.height(5.dp))
                InputFields(
                    labelValue = stringResource(id = R.string.emailInt),
                    painterResource = painterResource(id = R.drawable.envelope),
                    value = authViewModel.email,
                    onValueChange = { authViewModel.email = it },
                    errorMessage = authViewModel.signInEmailError
                )

                Spacer(modifier = Modifier.height(16.dp))

                LabelText(value = stringResource(id = R.string.password))
                Spacer(modifier = Modifier.height(5.dp))
                PassFields(
                    labelValue = stringResource(id = R.string.passwordInt),
                    painterResource = painterResource(id = R.drawable.lock_line_icon),
                    value = authViewModel.password,
                    onValueChange = { authViewModel.password = it },
                    errorMessage = authViewModel.signInPasswordError
                )

                Spacer(modifier = Modifier.height(16.dp))

                RememberComp(
                    value = stringResource(id = R.string.rememberme),
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it }
                )

                Spacer(modifier = Modifier.height(20.dp))

                LoginButtonComponent(
                    value = stringResource(id = R.string.login),
                    navController = navController,
                    authViewModel = authViewModel,
                    rememberMe = rememberMe,
                    tokenManager = tokenManager
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ForgotComponent()
                }

                Spacer(modifier = Modifier.height(80.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE6F7FF)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ButtonTextComponent(navController = navController, isSignUpScreen = false)
                }
            }
        }





    }
}
