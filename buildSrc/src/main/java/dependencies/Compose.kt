package dependencies

import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.support.delegates.ProjectDelegate
import org.gradle.tooling.model.gradle.GradleBuild

object Compose {


    private const val compose_version = "1.2.0"


    val composeUi by lazy { "androidx.compose.ui:ui:$compose_version" }
    val composeMaterial by lazy { "androidx.compose.material:material:$compose_version" }
    val composeTools by lazy { "androidx.compose.ui:ui-tooling-preview:$compose_version" }
    val composeActivity by lazy { "androidx.activity:activity-compose:1.3.1" }




}