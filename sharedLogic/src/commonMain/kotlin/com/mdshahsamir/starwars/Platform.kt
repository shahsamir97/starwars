package com.mdshahsamir.starwars

import io.ktor.client.engine.HttpClientEngine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform