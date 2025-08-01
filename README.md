## Android Jetpack Compose App

In this weekend project, I explore Jetpack Compose, and try to implement the BLoC pattern/architecture used in my Flutter projects.

### Description

Initially, I was planning on implementing a simple authentication flow, based on [this](https://www.figma.com/design/gnLdrXC66waMZG0CprzxCv/%E2%9C%A8-Login-and-Signup-Screen----harshadux--Community-?node-id=1-652&t=ThQzFLLLW8VJ9qfH-0) simple Figma design, but since I was having a good time working with Kotlin, I grew eager to learn more, and I ended up implementing more of an app scaffold.

### Points of interest:
- in MainActivity.kt state and navigation are hoisted
  - see [this](https://developer.android.com/develop/ui/compose/state-hoisting)
- state makes use of a [mapSaver](https://developer.android.com/develop/ui/compose/state#mapsaver)
- navigation is based on the [navigation3](https://github.com/android/nav3-recipes/blob/main/app/src/main/java/com/example/nav3recipes/basic/BasicActivity.kt) package
- instead of DI or IoC, and due to the limited scope of this app, I found a [service locator](https://developer.android.com/training/dependency-injection#di-alternatives) the best way to inject dependencies
- the data layer implements Retrofit, but the response is mocked for now
