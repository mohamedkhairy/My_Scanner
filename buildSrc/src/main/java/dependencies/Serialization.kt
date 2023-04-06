package dependencies

object Serialization {
    const val serializationPlugin = "plugin.serialization"
    const val serializationVersion = "1.2.2"
    val serialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion" }


}