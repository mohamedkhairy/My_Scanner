package dependencies

object Ktor {
    private const val ktorVersion = "1.6.2"

    val core by lazy { "io.ktor:ktor-client-core:$ktorVersion" }
    val clientSerialization by lazy { "io.ktor:ktor-client-serialization:$ktorVersion" }
    val android by lazy { "io.ktor:ktor-client-android:$ktorVersion" }
    val ktorJson by lazy { "io.ktor:ktor-client-json:$ktorVersion" }
    val logging by lazy { "io.ktor:ktor-client-logging:$ktorVersion" }



}