rootProject.name = "concertComparison"

include(
    "springApplication",

    "api",
    "projection",

    "platform",

    "support:log",
    "support:storage",
    "support:file",

    "security",

    "domain:concert",
    "domain:performance",
    "domain:ticket",

    "domain:maps",
    "domain:location",

    "domain:reservation",
    "domain:payment",

    "domain:user",

)

pluginManagement{
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings


    resolutionStrategy {
        eachPlugin {
            when(requested.id.id){
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion) }
        }
    }

}