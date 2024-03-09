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
    }
}

rootProject.name = "AnjunCrossExpressApp"
include(":app")
include(":service:service_main")
include(":data:data_main")
include(":core:core_router")
include(":core:core_tool")
include(":core:network")
include(":features:feature_main")
include(":features:feature_common:common_view")
include(":features:feature_common:common_base")
include(":core:core_mvvm")
