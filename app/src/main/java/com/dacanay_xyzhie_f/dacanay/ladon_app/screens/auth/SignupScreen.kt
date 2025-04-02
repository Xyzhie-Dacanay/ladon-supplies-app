package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(Routes.LogSign) }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back Button"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE6F7FF)
                )
            )
        }, containerColor = Color(0xFFE6F7FF)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE6F7FF))
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            HeadingText(value = stringResource(id = R.string.create))
            Spacer(modifier = Modifier.height(3.dp))
            DescriptionText(value = stringResource(id = R.string.fill))

            Spacer(modifier = Modifier.height(8.dp))

            LabelText(value = stringResource(id = R.string.username))
            Spacer(modifier = Modifier.height(4.dp))
            InputFields(
                labelValue = stringResource(id = R.string.username_des),
                painterResource = painterResource(id = R.drawable.user),
                value = authViewModel.username,
                onValueChange = { authViewModel.username = it },
                errorMessage = authViewModel.usernameError
            )

            Spacer(modifier = Modifier.height(12.dp))

            LabelText(value = stringResource(id = R.string.email))
            Spacer(modifier = Modifier.height(4.dp))
            InputFields(
                labelValue = stringResource(id = R.string.emailInt),
                painterResource = painterResource(id = R.drawable.envelope),
                value = authViewModel.email,
                onValueChange = { authViewModel.email = it },
                errorMessage = authViewModel.emailError
            )

            Spacer(modifier = Modifier.height(12.dp))

            LabelText(value = stringResource(id = R.string.password))
            Spacer(modifier = Modifier.height(4.dp))
            PassFields(
                labelValue = stringResource(id = R.string.passwordInt),
                painterResource = painterResource(id = R.drawable.lock_line_icon),
                value = authViewModel.password,
                onValueChange = { authViewModel.password = it },
                errorMessage = authViewModel.passwordError
            )

            Spacer(modifier = Modifier.height(12.dp))

            LabelText(value = stringResource(id = R.string.confirm_password))
            Spacer(modifier = Modifier.height(4.dp))
            SignUpPasswordField(
                labelValue = stringResource(id = R.string.confirm_desc),
                painterResource = painterResource(id = R.drawable.lock_line_icon),
                value = authViewModel.confirmPassword,
                onValueChange = { authViewModel.confirmPassword = it },
                errorMessage = authViewModel.confirmPasswordError
            )

            Spacer(modifier = Modifier.height(12.dp))

            LabelText(value = stringResource(id = R.string.contact))
            Spacer(modifier = Modifier.height(4.dp))
            ContactTextField(
                value = authViewModel.contactNumber,
                onValueChange = { authViewModel.contactNumber = it },
                painterResource = painterResource(id = R.drawable.contact),
                errorMessage = authViewModel.contactNumberError
            )

            Spacer(modifier = Modifier.height(14.dp))

            SignupButtonComponent(
                value = stringResource(id = R.string.signup),
                authViewModel = authViewModel,
                navController = navController,
                onSuccess = {
                    navController.navigate(Routes.LoginScreen) {
                        popUpTo(Routes.SignUp) { inclusive = true }
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            ButtonTextComponent(navController = navController, isSignUpScreen = true)
        }
    }
}
