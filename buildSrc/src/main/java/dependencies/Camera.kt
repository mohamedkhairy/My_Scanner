package dependencies

object Camera {


    const val camerax_version = "1.0.2"

    val cameraCore by lazy { "androidx.camera:camera-core:${camerax_version}" }
    val camera2 by lazy { "androidx.camera:camera-camera2:${camerax_version}" }
    val cameraLifecycle by lazy { "androidx.camera:camera-lifecycle:${camerax_version}" }
    val cameraView by lazy { "androidx.camera:camera-view:1.0.0-alpha29" }

    //Camera Permission
    val cameraPermissions by lazy { "com.google.accompanist:accompanist-permissions:0.19.0" }



}