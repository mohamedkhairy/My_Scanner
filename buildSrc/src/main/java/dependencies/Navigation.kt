package dependencies

object Navigation {

    private const val navigation_compose = "2.4.0-rc01"
    private const val compose_destinations = "1.1.2-beta"
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:$navigation_compose" }
    val composeDestinations by lazy { "io.github.raamcosta.compose-destinations:core:$compose_destinations" }
    val destinationsKsp by lazy { "io.github.raamcosta.compose-destinations:ksp:$compose_destinations" }


}