dependencies{
    implementation(project(":platform"))

    implementation(project(":domain:concert"))
    implementation(project(":domain:performance"))
    implementation(project(":domain:maps"))
    implementation(project(":domain:location"))
    implementation(project(":domain:payment"))


    compileOnly("org.springframework:spring-context")
    compileOnly("org.springframework:spring-tx")
    compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
}