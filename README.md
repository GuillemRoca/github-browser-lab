# Agenda ![Test](https://github.com/GuillemRoca/github-browser-lab/workflows/CI/badge.svg?branch=test/all-about-users&event=push)
Another Agenda to test and show architecture and UI/Material Design samples

### Introduction

Currently, this is a simple application with dark theme support tasked with displaying a list users from a CSV file.

### Functionality

The app is composed of one simple screen.

#### MainFragment

This screen allows you to browse user info loaded from a CSV like an agenda.

### Testing
The project uses local unit tests that run on your computer.

#### Local Unit Tests

##### ViewModel Tests
The Main ViewModel is tested using local unit tests with mock implementations.

##### UseCase Tests
The main use case to retrieve the company repositories is tested using local unit tests with mock repositories.

##### Data Repository Tests
The implementation of the data repository is tested using local unit tests with mocks.

### Architecture

This project is mainly based on the 3-layer clean architecture approach (`data`/`domain`/`presentation`).

##### Design decisions

Ideally, every layer could be represented in a separate module, thus achieving separation of concerns, but to not over-engineer I decided to split these concerns into different packages.

By design, I implemented the domain and data layer as a pure-kotlin to avoid filtering any android framework dependencies (with the exception of the application context to get the raw asset and logging). For these two layers, I've used RxJava as the threading library of choice.

The data layer is where the data sources are implemented and in this case, it parses the CSV from resources and returns it. 

In this case I'm parsing all the file at once but if the file was bigger I would implement a async logic to load more items whenever the user scrolls further to simulate an infinite scroll. Furthermore I decided to use a BufferedReader in UsersDataSource to increase performance.

The domain layer is where the domain entities and business logic are. Still since we only aim to display the info no further logic was added.

Finally, in the presentation layer, I'm using the MVVM pattern, using Jetpack ViewModels, and exposing a LiveData object that the view is observing and can update the UI accordingly when its state changes.

As a final note, this project also follows patterns found on [Android Architecture Blueprints sample by Google](https://github.com/googlesamples/android-architecture) in regards to using Hilt (Dagger) as DI. I've used Dagger several times in the past but I used this project to learn more how it could be implemented and used.

