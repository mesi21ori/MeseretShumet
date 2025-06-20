package com.example.anew.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CallReceived
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.anew.R
import com.example.anew.ui.components.OverlappingBankLogos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMoneyScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Icon(
                imageVector = Icons.Default.AccountBalanceWallet,
                contentDescription = "Add Money Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ADD MONEY",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "How you can fund your M-PESA account:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )

            MoneyOptionCard(
                icon = Icons.Default.AccountBalance,
                title = "Transfer from bank",
                description = "Move funds from your bank to M-PESA instantly.",
                buttonText = "LEARN HOW",
                extraContent = {
                    OverlappingBankLogos(
                        bankLogoResIds = listOf(
                            R.drawable.logo1,
                            R.drawable.logo2,
                            R.drawable.logo3,
                            R.drawable.logo4,
                            R.drawable.logo5,
                            R.drawable.logo3
                        )
                    )
                }
            )

            MoneyOptionCard(
                icon = Icons.Default.AccountBalanceWallet,
                title = "Deposit at Agent",
                description = "Deposit cash at any M-PESA Agent near you.",
                buttonText = "FIND AN AGENT"
            )

            MoneyOptionCard(
                icon = Icons.Default.AccountBalanceWallet,
                title = "Ask a friend",
                description = "Request money from a friend with just a tap.",
                buttonText = "REQUEST MONEY"
            )

            MoneyOptionCard(
                icon = Icons.Default.CallReceived,
                title = "Receive from abroad",
                description = "Receive international money transfers easily.",
                buttonText = "LEARN HOW",
                extraContent = {
                    OverlappingBankLogos(
                        bankLogoResIds = listOf(
                            R.drawable.logo1,
                            R.drawable.logo2,
                            R.drawable.logo3,
                            R.drawable.logo4,
                            R.drawable.logo5,
                            R.drawable.logo3
                        )
                    )
                } ,
                onClick = { navController.navigate("receive_from_abroad") }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyOptionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit = {},
    extraContent: @Composable (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall
            )

            if (extraContent != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        extraContent()
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick = onClick) {
                        Text(buttonText.uppercase())
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = onClick) {
                        Text(buttonText.uppercase())
                    }
                }
            }
        }
    }
}
