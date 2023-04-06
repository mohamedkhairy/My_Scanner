package dependencies

object AndroidX {
    private const val coreKtxVersion = "1.7.0"
    val coreKtx by lazy { "androidx.core:core-ktx:$coreKtxVersion" }

    private const val appCompatVersion = "1.3.1"
    val appCompat by lazy { "androidx.appcompat:appcompat:$appCompatVersion" }

    private const val lifecycleRuntimeVersion = "2.3.1"
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleRuntimeVersion" }

    private const val lifecycleVmKtxVersion = "2.4.1"

    val lifecycleVmKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVmKtxVersion" }
    val livedataKtx by lazy {"androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVmKtxVersion"  }

    private const val fragment_ktx = "1.3.4"
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:$fragment_ktx" }

    private const val constraintlayoutVersion = "2.1.0"
    val constraintlayout by lazy { "androidx.constraintlayout:constraintlayout:$constraintlayoutVersion" }


}

