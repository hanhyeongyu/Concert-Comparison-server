
tasks.getByName("bootJar"){
    enabled = true
}

tasks.getByName("jar"){
    enabled = false
}


dependencies{
    implementation(project(":api"))

    implementation(project(":platform"))

    implementation(project(":projection"))

    implementation(project(":domain:maps"))
    implementation(project(":domain:concert"))
    implementation(project(":domain:location"))

    implementation(project(":domain:user"))
    implementation(project(":domain:ticket"))
    implementation(project(":domain:reservation"))
    implementation(project(":domain:payment"))

    implementation(project(":security"))

    implementation(project(":support:log"))
    implementation(project(":support:storage"))
    implementation(project(":support:file"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}