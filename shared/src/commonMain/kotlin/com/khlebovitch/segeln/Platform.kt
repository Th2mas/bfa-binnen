package com.khlebovitch.segeln

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform