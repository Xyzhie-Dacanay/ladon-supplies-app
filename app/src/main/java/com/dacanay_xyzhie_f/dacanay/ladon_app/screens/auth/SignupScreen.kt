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
fun SignUpScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Back Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton(
                    onClick = { navController.navigate(Routes.LogSign) }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = "Back Button"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Header Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadingText(value = stringResource(id = R.string.create))
                Spacer(modifier = Modifier.height(3.dp))
                DescriptionText(value = stringResource(id = R.string.fill))

                Spacer(modifier = Modifier.height(24.dp))

                // Username Input
                LabelText(value = stringResource(id = R.string.username))
                Spacer(modifier = Modifier.height(5.dp))
                InputFields(
                    labelValue = stringResource(id = R.string.username_des),
                    painterResource = painterResource(id = R.drawable.user),
                    value = authViewModel.username,
                    onValueChange = { authViewModel.username = it },
                    errorMessage = authViewModel.usernameError
                )

                Spacer(modifier = Modifier.height(15.dp))

                // Email Input
                LabelText(value = stringResource(id = R.string.email))
                Spacer(modifier = Modifier.height(5.dp))
                InputFields(
                    labelValue = stringResource(id = R.string.emailInt),
                    painterResource = painterResource(id = R.drawable.envelope),
                    value = authViewModel.email,
                    onValueChange = { authViewModel.email = it },
                    errorMessage = authViewModel.emailError
                )

                Spacer(modifier = Modifier.height(15.dp))

                //Password Input
                LabelText(value = stringResource(id = R.string.password))
                Spacer(modifier = Modifier.height(5.dp))
                PassFields(
                    labelValue = stringResource(id = R.string.passwordInt),
                    painterResource = painterResource(id = R.drawable.lock_line_icon),
                    value = authViewModel.password,
                    onValueChange = { authViewModel.password = it },
                    errorMessage = authViewModel.passwordError

                )

                Spacer(modifier = Modifier.height(15.dp))

                //Confirm Password Input
                LabelText(value = stringResource(id = R.string.confirm_password))
                Spacer(modifier = Modifier.height(5.dp))
                SignUpPasswordField(
                    labelValue = stringResource(id = R.string.confirm_desc),
                    painterResource = painterResource(id = R.drawable.lock_line_icon),
                    value = authViewModel.confirmPassword,
                    onValueChange = { authViewModel.confirmPassword = it },
                    errorMessage = authViewModel.confirmPasswordError

                )

                Spacer(modifier = Modifier.height(24.dp))

                // Sign-Up Button

                SignupButtonComponent(
                    value = stringResource(id = R.string.signup),
                    authViewModel = authViewModel, 
                    navController = navController
                )

                Spacer(modifier = Modifier.height(14.dp))



                DividerComponent()
                Spacer(modifier = Modifier.height(10.dp))
                ButtonComponent()
                Spacer(modifier = Modifier.height(10.dp))
                ButtonTextComponent(navController = navController, isSignUpScreen = true) // ✅ Shows "Already have an account? Log In"

            }




        }
    }
}
