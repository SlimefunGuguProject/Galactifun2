plugins {
    kotlin("jvm") version "1.9.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
    id("xyz.jpenilla.run-paper") version "2.2.0"
}

repositories {
    mavenCentral()
    maven(url = "https://papermc.io/repo/repository/maven-public/")
    maven(url = "https://jitpack.io")
    maven(url = "https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    library(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:RC-36")

    implementation("org.bstats:bstats-bukkit:3.0.2")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")

    implementation("com.github.Seggan:kfun:0.1.0")
}

group = "io.github.addoncommunity.galactifun"
version = "MODIFIED"

kotlin {
    jvmToolchain(17)
}

tasks.compileKotlin {
    kotlinOptions.javaParameters = true
}

tasks.shadowJar {
    dependsOn(tasks.test)
    relocate("io.github.seggan.kfun", "io.github.addoncommunity.galactifun.kfun")
    relocate("org.bstats", "io.github.addoncommunity.galactifun.bstats")
    relocate("co.aikar.commands", "io.github.addoncommunity.galactifun.acf")
    relocate("co.aikar.locales", "io.github.addoncommunity.galactifun.acf.locales")
}

bukkit {
    name = project.name
    main = "io.github.addoncommunity.galactifun.Galactifun2"
    version = project.version.toString()
    author = "Seggan"
    apiVersion = "1.20"
    softDepend = listOf("ClayTech")
    loadBefore = listOf("Multiverse-Core")
    depend = listOf("Slimefun")
}

tasks.runServer {
    downloadPlugins {
        url("https://blob.build/dl/Slimefun4/Dev/1116")
    }
    maxHeapSize = "4G"
    minecraftVersion("1.20.1")
}