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
rootProject.name = "one-store"
include(":app")
include(":feature:home")
include(":feature:category")
include(":feature:products")
include(":feature:search")
include(":feature:profile")
include(":feature:details")
include(":feature:order")
include(":data:products")
include(":data:category")
include(":data:profile")
include(":data:order")
include(":core:network")
include(":core:local")


include(":core:common:common-main")
include(":core:common:model")
include(":core:common:legacy")
