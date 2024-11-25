import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.reload.ComposeHotRun
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.compose.hot.reload)

}
composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
    featureFlags.add(ComposeFeatureFlag.StrongSkipping)
    featureFlags.add(ComposeFeatureFlag.IntrinsicRemember)
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    metricsDestination = layout.buildDirectory.dir("compose_compiler")
}

tasks.register<ComposeHotRun>("hotReload") {
    mainClass.set("org.example.project.MainKt")

}


kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            //Exposed
            implementation("org.jetbrains.exposed:exposed-core:0.56.0")
            implementation("org.jetbrains.exposed:exposed-crypt:0.56.0")
            implementation("org.jetbrains.exposed:exposed-dao:0.56.0")
            implementation("org.jetbrains.exposed:exposed-jdbc:0.56.0")
            implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.56.0")
            implementation("org.jetbrains.exposed:exposed-json:0.56.0")
            implementation("org.jetbrains.exposed:exposed-money:0.56.0")
            implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.56.0")
            implementation("mysql:mysql-connector-java:8.0.33")
            //Koin
            //api(libs.koin.core)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)


            //Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            //ViewModel


            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}


compose.desktop {
    application {
        mainClass = "org.example.project.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.project"
            packageVersion = "1.0.0"
        }
    }
}
