# Weather

Welcome to the Weather Jetpack Compose application repository! This Android app is a feature-rich, modern weather application built using Jetpack Compose, designed to provide a seamless and visually appealing experience for users. 


## Key Features

- **Responsive UI:** Utilizes Jetpack Compose to create a responsive user interface that adapts to various screen sizes and orientations.
- **MVVM Architecture:** Follows the Model-View-ViewModel architectural pattern for a modular and maintainable codebase.
- **Modularization:** Codebase is organized into modular components for improved scalability, maintainability, and code separation.
- **Tablet Support:** Optimized for tablet devices, providing a seamless user experience on larger screens.
- **Offline Mode:** Allows the app to function in offline mode, displaying the last saved weather data when the internet is not available.
- **Dependency Injection:** Utilizes Dagger Hilt for efficient and clean dependency injection, promoting code organization and testability.
- **JSON Serialization:** Employs Gson for seamless JSON serialization and deserialization, facilitating smooth communication with the Weather.com API.
- **API Calls with Retrofit:** Uses Retrofit for making API calls to the Weather.com API, fetching real-time weather information.
- **Local Data Storage:** Implements Room as a local database management system, storing and retrieving data for efficient offline functionality.
- **Image Caching:** Integrates Coil for image loading and caching, enhancing performance when displaying weather-related images.
- **Portrait and Landscape Support:** Ensures a consistent and visually appealing user experience in both portrait and landscape orientations.


## Tech Stack

#### Core

- 100% [Kotlin](https://kotlinlang.org/)
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material3 design](https://m3.material.io/) (UI components)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) (structured concurrency)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html) (reactive datastream)
- [Hilt](https://dagger.dev/hilt/) (DI)
- [Coil](https://coil-kt.github.io/coil/) (Image caching)

#### Local Persistence
- [Room DB](https://developer.android.com/training/data-storage/room) (SQLite ORM)

#### Networking
- [Retrofit](https://square.github.io/retrofit/) (REST client)
- [Gson Serialization](https://github.com/google/gson) (JSON serialization)

### API
- [Weather API](https://www.weatherapi.com/)


## TODOs (Feel free to contribute)

- Add Dark/Light theme feature.
- Add support for foldable phones.
- Add feature - weather info for current location (if the user doesn't allow permission then use IP address-based location).
- Add feature - Everyday notification at 9 PM for the next day's weather updates. Using work manager.
- Add feature - Everyday notification at 9 AM for today's AQI updates. Using work manager.


### Screenshots
|          |             |                |
| :---:    |    :----:   |          :---: |
| ![1](https://raw.githubusercontent.com/vikanshu-joshi/Weather/main/screenshots/preview1.jpg) | ![2](https://raw.githubusercontent.com/vikanshu-joshi/Weather/main/screenshots/preview2.jpg) | ![3](https://raw.githubusercontent.com/vikanshu-joshi/Weather/main/screenshots/preview3.jpg)
| ![5](https://raw.githubusercontent.com/vikanshu-joshi/Weather/main/screenshots/preview5.png) | ![6](https://raw.githubusercontent.com/vikanshu-joshi/Weather/main/screenshots/preview6.png) |![7](https://raw.githubusercontent.com/vikanshu-joshi/Weather/main/screenshots/preview7.png)

#### Reponsive UI

|          |
| :---:    |
| ![1](https://raw.githubusercontent.com/vikanshu-joshi/Weather/main/screenshots/responsive_ui.gif) |
