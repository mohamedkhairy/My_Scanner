# My_Scanner
<h3> this app was built following Multi-module Architecture (Modularization) 
<h4> using BuildSrc for dependency management with Kotlin DSL
<h4> Jetpack compose for building UI state
<h4> Compose navigation system
<h4> Google MLkit and Camera Analysis to read Barcode
<h4> Dagger Hilt


<h2> [TODO - List] 
<h6>- need to handle some UI chages states 
  like "on scanner screen when to toggle between two options in bottom it should switch between each other and also header title should be updated"
  
<h6>- resources like string and colors sholud be in the correct file (String.xml , Color) not to be hardcoded

<h6>- it's better to create new module for Barcode Analyser, and inject it useing Dagger Hilt to interactor

<h6>- need to create interactors (use cases) it can be on "Core" module to get requirements to ViewModel.
    <h5> so when you open scanner screen the flow will be like this [UI"ViewModel" <- interactors <- Barcode Analyser module]


*********************************

<h5> APK link: https://github.com/mohamedkhairy/My_Scanner/raw/master/app/release/my_scanner.apk

#Screenshots
![screens](https://user-images.githubusercontent.com/8893078/231925468-c7d0acba-49f4-4961-84b8-bb3aa33802f4.png)

