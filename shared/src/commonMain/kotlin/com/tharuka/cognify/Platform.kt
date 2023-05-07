package com.tharuka.cognify

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform