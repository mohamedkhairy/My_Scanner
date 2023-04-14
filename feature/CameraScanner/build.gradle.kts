import dependencies.Camera
import dependencies.Barcode
import dependencies.Modules

apply {
    from("$rootDir/android-library-build.gradle")
}


dependencies {

    "implementation"(project(Modules.core))

    "implementation"(Camera.cameraCore)
    "implementation"(Camera.camera2)
    "implementation"(Camera.cameraLifecycle)
    "implementation"(Camera.cameraView)
    "implementation"(Camera.cameraPermissions)
    "implementation"(Barcode.barcodeScanning)


}
