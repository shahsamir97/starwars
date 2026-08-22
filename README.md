# Starwars KMP

A modern Kotlin Multiplatform (KMP) application that fetches and displays Star Wars data using the SWAPI GraphQL API. This project demonstrates a robust architectural approach for sharing logic between Android and iOS.

## 🚀 Features

- **Multiplatform Architecture**: Shared Data, Domain, and Presentation layers across Android and iOS.
- **GraphQL Integration**: Typed queries and mutations using Apollo GraphQL.
- **Networking**: Asynchronous network requests using Ktor.
- **Dependency Injection**: Decoupled components using Koin (including KMP ViewModel support).
- **Observable ViewModels**: Shared ViewModels using `kmp-observableviewmodel` for consistent state management.
- **Clean Architecture**: Separation of concerns with Repositories and UI states.

## 🛠 Tech Stack

- **Kotlin Multiplatform**: Core logic and platform sharing.
- **Compose Multiplatform**: Shared UI components and state management.
- **Apollo GraphQL Kotlin**: Type-safe GraphQL client.
- **Koin**: Lightweight dependency injection framework.
- **KMP-ObservableViewModel**: Lifecycle-aware ViewModels for shared logic.
- **Kermit**: Multiplatform logging utility.

## 📁 Project Structure

- `sharedLogic`: 
    - `commonMain`: Core business logic, GraphQL queries (`.graphql`), and shared ViewModels.
    - `androidMain` & `iosMain`: Platform-specific implementations
- `androidApp`: Android application module using Jetpack Compose.
- `iOSApp`: iOS application module using SwiftUI

## ⚙️ Getting Started

### Prerequisites

- **Android Studio** (Latest stable version recommended).
- **Xcode** (For iOS development).
- **JDK 17+**.

### Building the Project

To build the shared logic for iOS simulator:
