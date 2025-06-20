# Mobile Money App (Kotlin + Jetpack Compose)

 mobile money transfer application built with Kotlin and Jetpack Compose
##  Features

# Home Page
    - Add Money
    - Send to Bank

  # Add Money Page
    - Options like: Receive from Abroad, From Bank, Ask Friend
    - Includes bank logos UI with overlap display
    - Navigates to "Receive from Abroad" screen

# Receive from Abroad
    - Simple screen with "Back to Home" navigation

# Send to Bank 
    - Form with:
        - Bank Name (Dropdown)
        - Account Number
        - Amount (Min 25 Birr)
        - Optional Description
    - Validation for required inputs and correct formats

# Confirmation Screen
    - Displays entered details
    - PIN entry UI
    - Success message after pressing “Done”
# Recent Transfers Screen 
    - Shows past transfers (pre-seeded Room data)
    - Tap to view transfer detail

# Theme Toggle 
    - Light/Dark theme support via switch on Home Page

##  Folder Structure


│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.anew/
│   │   │       ├── data/
│   │   │       ├── navigation/
│   │   │       │   ├── AppContent.kt
│   │   │       │   └── NavGraph.kt
│   │   │       ├── ui/
│   │   │       │   ├── components/
│   │   │       │   │   └── OverlappingBankLogos.kt
│   │   │       │   └── screens/
│   │   │       │       ├── AddMoneyScreen.kt
│   │   │       │       ├── BankTransferScreen.kt
│   │   │       │       ├── ConfirmationScreen.kt
│   │   │       │       ├── HomeScreen.kt
│   │   │       │       ├── ReceiveFromAbroadScreen.kt
│   │   │       │       ├── RecentTransfersScreen.kt
│   │   │       │       └── TransferDetailScreen.kt
│   │   │       ├── theme/
│   │   │       ├── viewmodel/
│   │   │       │   └── TransferViewModel.kt
│   │   │       └── MainActivity.kt
│   │   ├── res/
│   │   └── AndroidManifest.xml
│   │
│   └── test/  [unitTest]
│
├── screenshots/
├── .gitignore
└── README.md