package com.dacanay_xyzhie_f.dacanay.ladon_app.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dacanay_xyzhie_f.dacanay.ladon_app.R
import com.dacanay_xyzhie_f.dacanay.ladon_app.core.reusable.*
import com.dacanay_xyzhie_f.dacanay.ladon_app.navigation.Routes
import com.dacanay_xyzhie_f.dacanay.ladon_app.presentation.auth.AuthViewModel

@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            //Back Button
            IconButton(
                onClick = { navController.navigate(Routes.LogSign) },
                modifier = Modifier
                    .padding(top = 30.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Arrow Back image Button"
                )
            }

            Spacer(modifier = Modifier.height(36.dp)) //

            // Header Section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadingText(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(8.dp))
                DescriptionText(value = stringResource(id = R.string.description_text))
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Email Input
            LabelText(value = stringResource(id = R.string.email))
            Spacer(modifier = Modifier.height(8.dp))
            InputFields(
                labelValue = stringResource(id = R.string.emailInt),
                painterResource = painterResource(id = R.drawable.envelope),
                value = authViewModel.email,
                onValueChange = { authViewModel.email = it },
                errorMessage = authViewModel.emailError

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            LabelText(value = stringResource(id = R.string.password))
            Spacer(modifier = Modifier.height(8.dp))
            PassFields(
                labelValue = stringResource(id = R.string.passwordInt),
                painterResource = painterResource(id = R.drawable.lock_line_icon),
                value = authViewModel.password,
                onValueChange = { authViewModel.password = it },
                errorMessage = authViewModel.passwordError
            )

            Spacer(modifier = Modifier.height(16.dp))




               // Remember Me Checkbox
               RememberComp(value = stringResource(id = R.string.rememberme))

               Spacer(modifier = Modifier.width(20.dp))





            // Login Button
            LoginButtonComponent(value = stringResource(id = R.string.login))

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ForgotComponent()
            }

            // Divider + Social Login Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DividerComponent()
                Spacer(modifier = Modifier.height(16.dp))
                ButtonComponent()
                Spacer(modifier = Modifier.height(16.dp))
                ButtonTextComponent(navController = navController, isSignUpScreen = false) // ✅ Shows "Don't have an account? Sign Up"

            }
        }
    }
}
