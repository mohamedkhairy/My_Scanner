package dependencies

object Kotlinx {
   private const val coroutinesCoreVersion = "1.6.2"
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion" }

}