# FancyCarApp

This Android app allows the user to check out the latest cars in the inventory of Fancy Cars Inc.

Programming language used - Kotlin

**Libraries used** - 
* Mockito
* Kotlin CoRoutines
* Android Room Persistence
* Gson

**Architecture used** - Model-View-Presenter

**Code Coverage** - 24%
(Note - Apart from writing more tests, the above coverage can be increased by excluding UI classes, Auto-generated classes etc.)

**Assumptions** - 
1) The requirements specified that there would be a separate service to get the availability status of a Car, but as we have to implement pagination alongwith sorting by availability, having a separate service to get the availability status of a Car would be in-efficient & would not yeild correct sorting results. Thus, at the moment, the availability status of the Car is being returned in the GetCars service.

**App Screens** -
i) Splash Screen
* This screen is shown to the user while the database is being loaded with mock data in the background.
* Consists of a loading animation & text that indicates the current state of the background process.

ii) Car Collections Screen
* This screen shows all the cars as an infinite scrolling list.
* Each car item shows - Name, Make, Model, Availability, Image & AddToCart button
* Following sorting functionalities are available -
  ..*Name - Sort the cars by their Name
  ..*Availability - Sort the cars by their Availability



