import dependencies.Camera
import dependencies.Barcode
apply {
    from("$rootDir/android-library-build.gradle")
}


dependencies {

    "implementation"(Camera.cameraCore)
    "implementation"(Camera.camera2)
    "implementation"(Camera.cameraLifecycle)
    "implementation"(Camera.cameraView)
    "implementation"(Camera.cameraPermissions)
    "implementation"(Barcode.barcodeScanning)

}
