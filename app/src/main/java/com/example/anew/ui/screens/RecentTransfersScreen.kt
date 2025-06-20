package com.example.anew.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.anew.data.Transfer
import com.example.anew.viewmodel.TransferViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RecentTransfersScreen(
    viewModel: TransferViewModel,
    onTransferClick: (Transfer) -> Unit
) {
    val transfers by viewModel.transfers.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(1000)
            isRefreshing = false
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { isRefreshing = true }
    ) {
        if (transfers.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No recent transfers available.")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(transfers) { transfer ->
                    TransferItem(transfer = transfer, onClick = { onTransferClick(transfer) })
                }
            }
        }
    }
}

@Composable
fun TransferItem(transfer: Transfer, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Bank: ${transfer.bankName}", fontWeight = FontWeight.Bold)
            Text("Account: ${transfer.accountNumber}")
            Text("Amount: ${transfer.amount} ETB")
            Text(
                "Date: ${
                    SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                        .format(Date(transfer.timestamp))
                }",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
