package org.example.collectswag

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform