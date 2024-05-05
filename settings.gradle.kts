pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io" )
        maven {
            url = uri("https://maven.pkg.github.com/iZettle/sdk-android")
            credentials {
                username = "vlzdtecnopac"
                password = ""
            }
        }


    }
}



rootProject.name = "MomoCoffeClient"
include(":app")
