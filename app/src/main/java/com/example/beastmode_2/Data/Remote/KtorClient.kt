package com.example.beastmode_2.Data.Remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    val client=HttpClient(Android){
        //content Negotiation is used to convert json to modal that is here data class
        install(ContentNegotiation){
            json(
                Json{
                    ignoreUnknownKeys=true
                }
            )
        }
    }
    const val BASE_URL="https://dummyjson.com/"
}