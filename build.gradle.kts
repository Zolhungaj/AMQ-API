plugins {
	java
	`maven-publish`
	kotlin("jvm") version "1.9.22"
}

group = "tech.zolhungaj"
version = "0.17.0"

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
	implementation(libs.apache.httpclient)
	implementation(libs.jackson.core)
	implementation(libs.jackson.databind)
	implementation(libs.json)
	implementation(libs.moshi)
	implementation(libs.moshi.kotlin)
	implementation(libs.moshi.adapters)
	implementation(libs.socket.io.client) {
		// excluding org.json because the one imported in socket-io has flaws
		exclude (group = "org.json", module = "json")
	}
	implementation(libs.slf4j.api)
	implementation(libs.slf4j.simple)
	compileOnly(libs.lombok)
	annotationProcessor(libs.lombok)
	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(17)
}
