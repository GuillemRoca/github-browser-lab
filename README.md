# GitHub Browser Lab ![Test](https://github.com/GuillemRoca/github-browser-lab/workflows/CI/badge.svg?branch=main&event=push)
Another GitHub Android client to test and show architecture and UI/Material Design samples

### Introduction

<img align="right" src="https://github.com/GuillemRoca/github-browser-lab/assets/demo.gif" alt="A demo illustraating the UI of the app" width="320" height="676" style="display: inline; float: right"/>

Currently, this is a simple application with dark theme support tasked with displaying a list of public repositories from Github organization and get more information about them if interested.

### Disclaimer

I've used this project to learn more about the latest practices in Android development, like using Hilt for dependency injection or how to correctly implement dark theme inside an app.

### Functionality

The app is composed of one simple screen and two extra dialogs.

#### MainFragment

This screen allows you to browse and explore the available GitHub public repositories and check, based on the background color of the element, if the repository is forked or not.

If the user long presses one of the repositories a dialog is displayed to provide more info. The user then can navigate to check the repository details or the owner details.

Furthermore, the app has an error handling mechanism that displays an error dialog if there are any issues while fetching the repositories.

### Testing
The project uses both instrumentation tests that run on the device and local unit tests that run on your computer.

##### Device Tests

##### UI Tests

The project uses Espresso for UI testing. There are some basic tests added to test the overall functionality of the app.

#### Local Unit Tests

##### ViewModel Tests
The Main ViewModel is tested using local unit tests with mock implementations.

##### UseCase Tests
The main use case to retrieve the company repositories is tested using local unit tests with mock repositories.

##### Data Repository Tests
The implementation of the data repository is tested using local unit tests with mock web service.

### Architecture

This project is mainly based on the 3-layer clean architecture approach (`data`/`domain`/`presentation`).

##### Design decisions

Ideally, every layer could be represented in a separate module, thus achieving separation of concerns, but to not over-engineer I decided to split these concerns into different packages.

By design, I implemented the domain and data layer as a pure-kotlin to avoid filtering any android framework dependencies. For these two layers, I've used RxJava as the threading library of choice.

The data layer is where the data sources are implemented and in this case, it communicates with a Retrofit service to retrieve and parse the request.

The domain layer is where the domain entities and business logic are.

The use case implemented contains business logic that is used in the presentation layer, and it connects with the repositories that are implemented in the data layer. 

Finally, in the presentation layer, I'm using the MVVM pattern, using Jetpack ViewModels, and exposing a LiveData object that the view is observing and can update the UI accordingly when its state changes.

As a final note, this project also follows patterns found on [Android Architecture Blueprints sample by Google](https://github.com/googlesamples/android-architecture) in regards to using Hilt (Dagger) as DI. I've used Dagger several times in the past but I used this project to learn more how it could be implemented and used.

### TODO

* Cache the data so it is available offline.

If I was to implement this functionality I would extend the current use case and link it to an additional data repository and local data source to store the data in a local database.

In this particular case, I would use Room library (part of the Android Jetpack components) to store the data.

* Implement a load more mechanism.

If I was to implement this functionality I would extend the current use case and link it to an additional data repository that would handle the pagination logic based on a trigger from the UI. The UI in turn would trigger the flag to fetch more repositories with the help of an EndlessScrollListener in the recycler view.

Another approach could be using the Paging Jetpack library.
