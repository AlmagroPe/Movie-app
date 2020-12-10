Movie App
===========================================================
This is a simple app that uses a movie API

Brief explanation
-------------

An architecture has been implemented following, as close as possible, the concept of Clean Architecture. keeping the thought of getting a scalable, modular and independent app from framework, UI and database. The application has been divided into 3 modules: data, domain and presentation+app.

The data module is represented by the "data" package where the "ApiDataSource" packages are located, which will provide the data from the API. This module will depend on the Android framework. It contains the definition of the APIs to which we are going to connect to obtain data. We have all the "dataSource" that will provide us with data either from the database, cache or API.

In addition, we include the implementation of the repository and from here we will call the different dataSource to obtain data. A class called NetworkCall has been implemented that will control whether the api calls have been successful or not. Also, data models for the response of the calls and the domain have been created.

The domain module that corresponds to the "domain" module where we have the data models to use in this layer, the use cases and the definition of the repositories.

Finally, the app module will contain the application views and UI models, if necessary. We also have the Dagger implementation. An MVP pattern has been followed to separate the logical layer of the screen from its UI part. And the presentation module will contain all the presenters and interfaces to communicate with the view, also in the presenters we change from Main thread to IO thread to make an background job

The concept of functional programming "Either" has been used, which is a monadic type of functional programming. Represents a disjoint function, it is made up of two Left and Right values, so it can only hold one value. The left represents the failure case, while the right contains the success value. Every time a request is made, it will be checked whether the transaction has gone well or badly and it will return Left, with the error that will be represented by the class "Failure" which is a sealed class with as many classes as different errors we have, or Right with the data obtained. Being a small app it may not be necessary to introduce a whole library to just use Either, but this library will provide us with versatility in the future

Some questions
-------------
#### 1. How long did you spend on the coding test? What would you add to your solution if you had more time? If you didn't spend a lot of time testing coding, use this as an opportunity to explain what you would add.
I spent 2 days to coding this app. I would like to improve many things but the 3 more important are: UI design, improve error detection for more information and add more drives and instrumented tests

#### 2. How would you locate a performance problem in production? Have you ever had to do this?
I typically use the Firebase SDK to track crashes and poor performance. Firebase warns you about a crash and depending on the affected users you must resolve it quickly to upload a hotfix

#### 3. How would you ask for a "dangerous" permission for the user, for example, if location is required?
To request permissions from the user, you must follow the guides that google has.


### Libraries
* [Android Support Library][support-lib]
* [Dagger 2][dagger2]
* [Retrofit][retrofit] 
* [Glide][glide]
* [mockito][mockito]
* [Arrow][arrow]


[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[dagger2]: https://google.github.io/dagger
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[mockito]: http://site.mockito.org
[arrow]: https://github.com/arrow-kt/arrow
