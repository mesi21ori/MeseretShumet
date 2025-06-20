package com.example.anew.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.anew.data.Transfer
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TransferDetailScreen(transfer: Transfer) {
    Column(Modifier.padding(24.dp)) {
        Text("Transfer Details", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Bank Name: ${transfer.bankName}")
        Text("Account Number: ${transfer.accountNumber}")
        Text("Amount Sent: ${transfer.amount} ETB")
        Text("Status: ${transfer.status}")
        Text("Date: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", 
            Locale.getDefault()).format(Date(transfer.timestamp))}")
    }
}
