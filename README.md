# 🍲 FeastMode 

**FeastMode** is a modern Android application built using **Jetpack Compose** and **Clean Architecture (MVVM)**. It allows users to explore recipes fetched from a remote REST API as well as create, edit, view, and manage custom recipes stored in a local **Room Database**.

---

## 🌟 Key Features

- 📱 **100% Jetpack Compose UI**: Built with modern, declarative Compose components and Material 3 design.
- 🎨 **Animated Splash Screen**: Custom animated splash screen (`FeastModeSplashScreen`) combined with Android 12+ SplashScreen API integration.
- 🌐 **Remote Recipe Browsing**: Fetches recipe data dynamically using **Ktor HTTP Client** from `https://dummyjson.com/recipes`.
- 🔍 **Cuisine & Category Filtering**: Easily filter remote and local recipes by cuisine category (All, Italian, Asian, Mexican, etc.).
- 📖 **Detailed Recipe Screen**: View comprehensive recipe details including prep time, cook time, difficulty, calories per serving, servings, ingredients list, and step-by-step cooking instructions.
- 💾 **Local Database (Room)**: Full offline storage and CRUD (Create, Read, Update, Delete) capability for user-created custom recipes.
- ✏️ **Add & Edit Custom Recipes**: Form validation to add new custom recipes or edit existing ones with dynamic ingredient and instruction fields.
- 🗑️ **Delete Recipe Confirmation**: Interactive modal dialogs (`DeleteDialog`) for confirming recipe deletion.
- 🧭 **Type-Safe Navigation**: Powered by Navigation Compose with `@Serializable` route objects (`Routes`).
- 🖼️ **Image Loading**: Async image loading with **Coil 3** (integrated with Ktor 3 network engine).

---

## 🏗️ Architecture & Project Structure

The application follows **Clean Architecture** principles separated into Data, Domain, and Presentation layers:

```
com.example.beastmode_2/
├── Data/
│   ├── Remote/
│   │   ├── DTO/
│   │   │   ├── AddRecipeRequest.kt
│   │   │   ├── AddRecipeResponse.kt
│   │   │   ├── RecipeDTO.kt
│   │   │   └── RecipeResponse.kt
│   │   ├── KtorClient.kt               # Ktor HTTP client configuration
│   │   └── RecipeApiService.kt         # Remote API network calls
│   ├── RoomDatabase/
│   │   ├── AddRecipe.kt                # Room Entity (@Entity)
│   │   ├── Converters.kt               # Room TypeConverters (List<String> <-> JSON)
│   │   ├── Dao.kt                      # Room DAO (@Dao)
│   │   └── DataBase.kt                 # Room Database (@Database)
│   └── repository/
│       └── RepositoryImplementation.kt # Concrete repository implementation
├── Domain/
│   └── Repository/
│       └── Repository.kt               # Repository interface contract
├── Presentation/
│   ├── AndroidSplashScreen/
│   │   └── SplashScreen.kt             # Custom animated splash screen UI
│   ├── Navigation/
│   │   ├── BottomBar.kt                # Navigation bar UI component
│   │   ├── RecipeNavHost.kt            # NavHost composition & screen routing
│   │   └── Routes.kt                   # Type-safe navigation routes (@Serializable)
│   ├── Screens/
│   │   ├── AddRecipeScreen/            # Add & edit recipe screen
│   │   ├── CustomRecipeDetailsScreen/    # Local recipe details & delete dialog
│   │   ├── HomeScreen/                 # Remote recipe feed & category filters
│   │   ├── MyRecipeScreen/             # Local user recipes list screen
│   │   └── RecipeDetailsScreen/        # Remote recipe detail view
│   └── Viewmodels/
│       ├── ViewModels/                 # HomeViewModel, AddRecipeViewModel, etc.
│       └── ViewModelFactory/           # ViewModel Factories
├── ui/
│   └── theme/                          # Color, Theme, Type specifications
└── MainActivity.kt                     # Single Activity entry point
```

---

## 🔌 API Implementation & Endpoints

### Base URL
```
https://dummyjson.com/
```

### Endpoints Integrated

| Method | Endpoint | Description | Data Transfer Object (DTO) |
| :--- | :--- | :--- | :--- |
| `GET` | `/recipes` | Retrieves all public recipes from DummyJSON API | `RecipeResponse`, `RecipeDTO` |
| `GET` | `/recipes/{id}` | Fetches details for a specific recipe by ID | `RecipeDTO` |
| `POST` | `/recipes/add` | Submits a request to add a new recipe to the remote server | `AddRecipeRequest`, `AddRecipeResponse` |

### Networking Stack
- **HTTP Client**: Ktor 3.5.2 (`io.ktor:ktor-client-android`)
- **Content Negotiation**: Ktor Content Negotiation with Kotlinx Serialization (`ignoreUnknownKeys = true`)
- **Image Network Engine**: Coil 3 with Ktor 3 integration (`io.coil-kt.coil3:coil-network-ktor3`)

---

## 💾 Local Storage (Room Database)

For local custom recipe persistence, **Room Database 2.8.4** (with KSP) is implemented.

- **Database Name**: `recipe_database`
- **Entity (`AddRecipe`)**:
  - `id` (Primary Key, Auto-Generated)
  - `name`, `cuisine`, `difficulty`, `prepTimeMinutes`, `cookTimeMinutes`, `servings`, `caloriesPerServing`, `image`
  - `ingredients` (`List<String>`)
  - `instructions` (`List<String>`)
  - `mealType` (`List<String>`)
  - `tags` (`List<String>`)
- **Type Converters**: `Converters` uses `kotlinx.serialization` (`Json.encodeToString` / `Json.decodeFromString`) to serialize/deserialize Kotlin lists into SQLite strings.

---

## 🧭 Navigation Routes

Type-safe navigation routes defined in `Routes.kt`:

- `Routes.Home`: Main recipe discovery feed.
- `Routes.RecipeDetails(id: Int)`: Detailed view for remote recipes.
- `Routes.MyRecipes`: Saved/custom recipes stored in local database.
- `Routes.CustomRecipeDetails(id: Int)`: Detailed view for user-created recipes.
- `Routes.AddRecipe(id: Int?)`: Screen for adding a new recipe or editing an existing custom recipe (`id != null`).

---

## 🛠️ Technology Stack & Dependencies

| Category | Technology / Library | Version |
| :--- | :--- | :--- |
| **Language** | Kotlin | 2.x |
| **Target SDK / Min SDK** | Target: 37 / Min: 24 | - |
| **UI Framework** | Jetpack Compose (BOM) | Material 3 |
| **Networking** | Ktor Client | 3.5.2 |
| **JSON Serialization** | Kotlinx Serialization | 1.11.0 |
| **Database** | Room Database | 2.8.4 |
| **Annotation Processor** | KSP (Kotlin Symbol Processing) | Configured |
| **Navigation** | Navigation Compose | 2.9.8 |
| **Image Loading** | Coil 3 | 3.5.0 |
| **Splash Screen** | AndroidX Core SplashScreen | 1.0.0 |
| **Architecture** | ViewModel & Flow | 2.11.0 |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug (2024.2.1) or newer / Android Studio 2026+
- JDK 11+
- Android Device or Emulator running Android 7.0 (API Level 24) or higher

### Building & Running
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle project files.
4. Run on an emulator or physical device (`Shift + F10`).
