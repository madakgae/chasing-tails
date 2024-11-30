import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.*

plugins {
    idea
    alias(libs.plugins.kotlin)
    alias(libs.plugins.runPaper)
    alias(libs.plugins.pluginYml)
}

group = "me.prdis"
version = "1.0.1"
val codeName = "chasingtails"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    library(kotlin("stdlib"))
    compileOnly(libs.paper)

    compileOnly(libs.coroutines)
    compileOnly(libs.mccoroutines)
    compileOnly(libs.mccoroutinesCore)

    bukkitLibrary(libs.coroutines)
    bukkitLibrary(libs.mccoroutines)
    bukkitLibrary(libs.mccoroutinesCore)
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

tasks {
    jar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        archiveVersion.set("")
    }
    runServer {
        minecraftVersion("1.21")
        jvmArgs = listOf("-Dcom.mojang.eula.agree=true")
    }
}

idea {
    module {
        excludeDirs.addAll(listOf(file("run"), file("out"), file(".idea"), file(".kotlin")))
    }
}

bukkit {
    name = rootProject.name
    version = rootProject.version.toString()
    author = "Paradise Dev Team"

    main = "${project.group}.${codeName}.plugin.${codeName.replaceFirstChar { it.titlecase() }}Plugin"

    apiVersion = "1.21"
}