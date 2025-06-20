package com.example.anew.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anew.data.Transfer
import com.example.anew.viewmodel.TransferViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationScreen(
    navController: NavHostController,
    bank: String?,
    account: String?,
    amount: String?,
    desc: String? = null,
    viewModel: TransferViewModel
) {
    var pin by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf<String?>(null) }

    val bankName = URLDecoder.decode(bank ?: "", StandardCharsets.UTF_8.name())
    val accountNumber = URLDecoder.decode(account ?: "", StandardCharsets.UTF_8.name())
    val transferAmount = URLDecoder.decode(amount ?: "0", StandardCharsets.UTF_8.name())
    val description = URLDecoder.decode(desc ?: "No description", StandardCharsets.UTF_8.name())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = bankName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "Account: $accountNumber", fontSize = 16.sp)
                Text(text = "Amount: $transferAmount Birr", fontSize = 16.sp)
                Text(text = "Description: $description", fontSize = 14.sp, color = Color.Gray)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(12.dp))
                Text("Enter PIN", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) { index ->
                        val filled = index < pin.length
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(if (filled) Color.Black else Color.LightGray)
                        )
                    }
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val keys = listOf(
                    listOf("1", "2", "3"),
                    listOf("4", "5", "6"),
                    listOf("7", "8", "9"),
                    listOf(" ", "0", "X")
                )

                keys.forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                        row.forEach { label ->
                            Text(
                                text = label,
                                fontSize = 26.sp,
                                modifier = Modifier
                                    .clickable {
                                        when (label) {
                                            "X" -> if (pin.isNotEmpty()) pin = pin.dropLast(1)
                                            " " -> {}
                                            else -> if (pin.length < 4) pin += label
                                        }
                                    }
                                    .padding(16.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            successMessage?.let {
                Text(
                    text = it,
                    color = if (it.contains("Successful")) Color(0xFF00A859) else Color.Red,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Button(
                onClick = {
                    if (pin.length == 4) {
                        viewModel.insertTransfer(
                            Transfer(
                                bankName = bankName,
                                accountNumber = accountNumber,
                                amount = transferAmount.toIntOrNull() ?: 0,
                                status = "Success"
                            )
                        )
                        successMessage = "Transfer Successful!"
                    } else {
                        successMessage = "PIN must be exactly 4 digits."
                    }
                }
            ) {
                Text("Done")
            }
        }
    }
}
