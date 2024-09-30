dependencies{
    implementation(project(":platform"))
    implementation(project(":security"))

    implementation(project(":domain:user"))
    implementation(project(":domain:maps"))
    implementation(project(":domain:concert"))
    implementation(project(":domain:performance"))
    implementation(project(":domain:location"))
    implementation(project(":domain:ticket"))
    implementation(project(":domain:reservation"))
    implementation(project(":domain:payment"))

    implementation(project(":projection"))

    implementation(project(":support:file"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}