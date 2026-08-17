import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.apolloGraphQL)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.koin.compiler)
}

apollo {
    service("starwars") {
        packageName.set("com.mdshahsamir.starwars")
        // Update this path to match your package name
        schemaFile.set(file("src/commonMain/graphql/com/mdshahsamir/starwars/schema.graphqls"))

        introspection {
            endpointUrl.set("https://swapi-graphql.netlify.app/graphql")
            headers.put("User-Agent", "Apollo")
        }
    }
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            export(libs.androidx.lifecycle.viewmodel)
            export("com.rickclephas.kmp:kmp-observableviewmodel-core:1.0.6")
            baseName = "SharedLogic"
            isStatic = true
        }
    }
    
    androidLibrary {
       namespace = "com.mdshahsamir.starwars.sharedLogic"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        commonMain.dependencies {
            //viewmodel
            api(libs.androidx.lifecycle.viewmodel)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            //apollo
            implementation(libs.apollo.runtime)

            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)

            implementation(libs.kermit)
            api("com.rickclephas.kmp:kmp-observableviewmodel-core:1.0.6")
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlin.coroutines.test)
        }
    }
}