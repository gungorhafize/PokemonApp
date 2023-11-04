<h1>Pokemon </h1>
<p>Pokemon app using the MVVM design pattern, integrating Jetpack Compose for the UI, Coroutines for concurrency, Dagger Hilt for dependency injection and Retrofit for network operations.</p>
<br>

<!-- Screenshots -->
<p align="center">
  <img  src="https://github.com/gungorhafize/PokemonApp/assets/33956266/ca30f0ed-68b7-4075-9e0d-66483fec42ed" width="30%"/>
  &nbsp;                                                                                                                
  <img src="https://github.com/gungorhafize/PokemonApp/assets/33956266/8e095fde-aaa7-4ec9-8d08-67c7786d2ed9" width="30%"/>
  </p>
<br>
<!-- Open APIs-->
<h2>Open APIs🔗 </h2>
<img src="https://user-images.githubusercontent.com/57670625/235320883-b4eae21e-93c0-4229-abaf-642d1cb4bc34.png" align="right" width="21%"/>
<br>
<p>Pokemon uses the <a href="https://pokeapi.co/">PokeApi</a> to get information from the server. The PokeApi has a lot of details about the main Pokémon games and offers them through a RESTful API. game series.</p>
<br>

<!-- Architecture -->
<h2>Architecture🏗️ </h2>
<p>Pokemon App uses the MVVM architecture to separate UI and Data components, ensuring a clear separation of concerns, driving the UI from data models, maintaining a single source of truth, and enforcing unidirectional data flow for a more maintainable and testable Android app.</p>

<p align="center">
   <img src="https://github.com/gungorhafize/PokemonApp/assets/33956266/e1b3087a-b3a0-49a5-817c-0c386d2b7c19" width="85%"/>
</p>

<h3>Architecture Overview</h3>
<p>By adapting architectural layering, the Pokemon App follows a unidirectional data flow, responding accordingly to user events and updating UI states.</p>

<h3>UI Layer🖼️ </h3>
<p align="center">
   <img src="https://github.com/gungorhafize/PokemonApp/assets/33956266/3dec5f60-8049-4c59-9aee-402a0947740f" width="85%"//>
</p>
<p>The UI layer serves as the primary point for user interactions and displays the application data. It should update whenever the app data changes, reflecting changes made by either user interaction or external input./p>
<ul>
  <li>Depending on user interactions, the main activity hosts the navigation controller and navigates through two screens: PokemonListScreen and PokemonDetailScreen. </li>
  <li>The PokemonListViewModel requests data from the data layer, and the PokemonListScreen updates its UI by observing the states managed by the ViewModel.</li>
  <li>The responsibility of the PokemonDetailViewModel is to fetch Pokemon details and update the states for the PokemonDetailScreen.</li>
</ul>
<br>

<h3>Data Layer👾 </h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/235320689-16de6724-0570-43ac-aa4d-786f879ffeb0.jpg" width="65%"/>
</p>
<p>The data layer is responsible for containing application data and business logic. It consists of repositories and data sources. Keeping each repository as a single source of truth is crucial.</p>
<ul>
  <li>PokemonRepository requests data from the remote data source and serves as a single source of truth.</li>
  <li>PokemonRepository uses the Retrofit library to send requests to the PokeAPI server and retrieve network responses.</li>
</ul>
<br>

<!-- Tech Stack-->
<h2>Tech Stack🚀 </h2>
<ul>
<li>Minumum SDK Level: 21</li>
<li>Target SDK Level: 34</li>
<li>100% Kotlin</li>
<li>Architecture
    <ul>
      <li><a href="https://developer.android.com/topic/architecture">MVVM Pattern</a>: Industry-recognized software architecure pattern supported by Google</li>
    </ul>
 </li>
<li><a href="https://developer.android.com/jetpack/compose/documentation">Jetpack Compose</a>:Using a declarative programming model, modern toolkit for building Android user interfaces.</li>
<li><a href="https://developer.android.com/kotlin/coroutines">Coroutines</a>: Concurrency design patterns offered by Kotlin</li>
<li><a href="https://developer.android.com/jetpack/compose/state">ViewModel</a>: As a state holder, it exposes data streams.</li>
<li><a href="https://github.com/coil-kt/coil/">Coil</a> : A lightweight image loading library for Android backed by Kotlin Coroutines. </li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a>: A dependency injection library built on top of Dagger gains advantages such as compile-time correctness, runtime performance, scalability, and support within Android Studio. </li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a>: A REST client for Android, Java, and Kotlin, developed by Square, known for its type-safe nature</li>
<li><a href="https://square.github.io/okhttp/"[>OkHttp</a> : A third-party library built on top of the Okio library, used for sending and receiving HTTP-based network requests</li>
<li><a href="https://github.com/JakeWharton/timber/">Timber</a> : Logging utility library commonly used in Android development for logging messages and debugging information</li>
</ul>
<br>



