# Trending Home Credit

## Getting Start

You can open this Project with Android Studio *3.4.1*.

## Prerequisites

What things you need to install the software and install them :
If you using Mac OS, you could install this with [Homebrew](homebrew.sh)

- Java 8
- Gradle

## Installing

A step by step to get a development env running

```
./gradlew assembleDevDebug
```

To install in emulator or device
```
./gradlew installDevDebug
```

To check dependencies using

```
./gradlew app:dependencies
```

## Features

- API Call with [Retrofit](https://square.github.io/retrofit)
- Dependency injection (with [Koin](https://insert-koin.io))
- Reactive programming with RxJava 2 and RxAndroid
- Google Design library
- Android architecture components with MVVM
    
## Supported devices

The template support every device with a SDK level of at least 16 (Android 4+)


| **Splash Screen** | **Main Screen** | **Browser Product tab  Screen** | **Browser Article tab  Screen** |
| ------ | ------ | ------ | ------ |
| ![alt text](https://i.postimg.cc/4NbsCb3t/Screenshot-2020-04-11-10-36-31-56.png "Splash Screen") | ![alt text](https://i.postimg.cc/3NZGD03q/Screenshot-2020-04-11-10-36-25-87.png "Main Screen") | ![alt text](https://i.postimg.cc/G24ryWdn/Screenshot-2020-04-11-10-36-46-94.png "Browser Product tab Screen") | ![alt text](https://i.postimg.cc/KjnY16Dv/Screenshot-2020-04-11-10-36-54-25.png "Browser Article tab Screen")

