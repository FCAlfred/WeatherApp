WeatherApp is a gorgeous client application for [WeatherMap](https://home.openweathermap.org/) on Android, built using Kotlin.


## Architecture and Tech-stack

* Built on MVVM architecture pattern
* Uses [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/), specifically Fragments, ViewModel, LiveData, viewBinding, Navigation and Tabs.
* Uses [Retrofit](https://square.github.io/retrofit/) for making API calls.
* Uses [Glide](https://github.com/bumptech/glide) for image loading.
* Uses [ROOM](https://github.com/androidx-releases/Room/releases) for local storage.
* Uses [Google Map API](https://cloud.google.com/apis?gad_source=1&hl=es) for maps.
* Uses [Coroutines](https://developer.android.com/kotlin/coroutines?hl=es-419) for hadler flows.
* Uses [Gson](https://github.com/google/gson) for serialization/deserealization .
* Main Content was Built on a Single-Activity Architecture. Every screen in the app is a fragment.
