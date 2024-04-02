package com.khlebovitch.bfa.binnen

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform