package dependencies

object Hilt {
    const val hiltVersion = "2.41"

    val android by lazy { "com.google.dagger:hilt-android:$hiltVersion" }
    val compiler by lazy { "com.google.dagger:hilt-compiler:$hiltVersion" }
    val hiltNavigation by lazy { "androidx.hilt:hilt-navigation-compose:1.0.0" }


}

//object HiltTest {
//    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Hilt.hiltVersion}"
//}