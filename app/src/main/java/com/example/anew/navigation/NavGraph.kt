package com.example.anew.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.anew.data.Transfer
import com.example.anew.ui.screens.*
import com.example.anew.viewmodel.TransferViewModel

@Composable
fun AppNavigation(
    isDarkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit
) {
    val navController: NavHostController = rememberNavController()

    val application = LocalContext.current.applicationContext as Application
    val transferViewModel: TransferViewModel = viewModel(
        factory = AndroidViewModelFactory(application)
    )

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme
            )
        }

        composable("add_money") {
            AddMoneyScreen(navController = navController)
        }

        composable("bank_transfer") {
            BankTransferScreen(navController = navController)
        }

        composable("receive_from_abroad") {
            ReceiveFromAbroadScreen(navController = navController)
        }

        composable(
            route = "confirmation?bank={bank}&account={account}&amount={amount}&desc={desc}",
            arguments = listOf(
                navArgument("bank") { type = NavType.StringType; defaultValue = "" },
                navArgument("account") { type = NavType.StringType; defaultValue = "" },
                navArgument("amount") { type = NavType.StringType; defaultValue = "0" },
                navArgument("desc") { type = NavType.StringType; defaultValue = "" }
            )
        ) { backStackEntry ->
            val bank = backStackEntry.arguments?.getString("bank")
            val account = backStackEntry.arguments?.getString("account")
            val amount = backStackEntry.arguments?.getString("amount")
            val desc = backStackEntry.arguments?.getString("desc")

            ConfirmationScreen(
                navController = navController,
                bank = bank,
                account = account,
                amount = amount,
                desc = desc,
                viewModel = transferViewModel
            )
        }

        composable("recent_transfers") {
            RecentTransfersScreen(
                viewModel = transferViewModel,
                onTransferClick = { transfer ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("selectedTransfer", transfer)
                    navController.navigate("transfer_detail")
                }
            )
        }

        composable("transfer_detail") {
            val transfer = navController.previousBackStackEntry
                ?.savedStateHandle?.get<Transfer>("selectedTransfer")

            transfer?.let {
                TransferDetailScreen(transfer)
            }
        }
    }
}
