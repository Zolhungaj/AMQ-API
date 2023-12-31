plugins {
	java
	`maven-publish`
	kotlin("jvm") version "1.9.22"
}

group = "tech.zolhungaj"
version = "0.15.0"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}
configurations {
	compileOnly {
		extendsFrom( configurations.annotationProcessor.get())
	}
}


publishing {
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/Zolhungaj/AMQ-API")
			credentials {
				username = System.getenv("GITHUB_ACTOR")
				password = System.getenv("GITHUB_TOKEN")
			}
		}
	}
	publications {
		//noinspection GroovyAssignabilityCheck
		create<MavenPublication>("gpr") {
			//noinspection GroovyAssignabilityCheck
			artifactId = "amq-api"
			from(components["java"])
		}
	}
}
repositories {
	mavenCentral()
}

dependencies {
	implementation("org.apache.httpcomponents:httpclient:4.5.14")
	implementation("com.fasterxml.jackson.core:jackson-core:2.16.0")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
	implementation("org.json:json:20231013")
	implementation("com.squareup.moshi:moshi:1.15.0")
	implementation("com.squareup.moshi:moshi-adapters:1.15.0")
	implementation("io.socket:socket.io-client:2.1.0") {
		// excluding org.json because the one imported in socket-io has flaws
		exclude (group = "org.json", module = "json")
	}
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	implementation("org.slf4j:slf4j-api:2.0.10")
	implementation("org.slf4j:slf4j-simple:2.0.10")
	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(17)
}