package dependencies

object Barcode {

    //Barcode
    const val barcode_version = "17.0.0"

    val barcodeScanning by lazy { "com.google.mlkit:barcode-scanning:${barcode_version}" }

}