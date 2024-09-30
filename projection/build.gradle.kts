dependencies{
    implementation(project(":platform"))

    implementation(project(":support:file"))

    implementation(project(":domain:user"))

    implementation(project(":domain:maps"))
    implementation(project(":domain:concert"))
    implementation(project(":domain:performance"))
    implementation(project(":domain:location"))

    implementation(project(":domain:ticket"))
    implementation(project(":domain:reservation"))
    implementation(project(":domain:payment"))

    compileOnly("org.springframework:spring-context")
    compileOnly("org.springframework:spring-tx")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}