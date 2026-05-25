package com.mdshahsamir.starwars

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform