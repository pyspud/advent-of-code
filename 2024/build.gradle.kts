plugins {
    java
    `jvm-test-suite`
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            dependencies {
                implementation(platform(libs.junit.bom))
                implementation.bundle(libs.bundles.junit)
            }
            useJUnitJupiter()
            // targets{
            //     all {
            //         testTask.configure {
            //             testLogging {
            //                 events("FAILED")
            //                 // exceptionFormat = TestExceptionFormat.FULL
            //             }
            //         }
            //     }
            // }
        }
    }
}