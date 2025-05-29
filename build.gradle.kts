plugins {
    alias(libs.plugins.seraphim.android.library)
}
android {
    namespace = "com.seraphim.core.permissions"
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}