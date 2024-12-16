package com.example.core

import com.example.core.model.domain.Character
import com.example.core.model.remote.RemoteCharacter
import com.example.core.model.remote.toDomainCharacter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class NetworkClient {
    private val client = HttpClient(OkHttp){
        defaultRequest { url("https://rickandmortyapi.com/api/") }

        install(Logging){
            logger = Logger.SIMPLE
        }


        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }



    }


    suspend fun getCharacterSafe(id:Int): ApiOperation<com.example.core.model.domain.Character> =
        safeApiCall {
            getCharacter(id)
        }


    private var characterCache = mutableMapOf<Int, Character>()
    private suspend  fun getCharacter(id:Int): com.example.core.model.domain.Character{
      characterCache[id]?.let{ return it }

        return  client.get("character/${id}")
            .body<RemoteCharacter>()
            .toDomainCharacter()
            .also {
                characterCache[id] = it
            }
    }

    private inline fun <T> safeApiCall(apiCall: ()->T): ApiOperation<T>{
        return try{
            ApiOperation.Success(apiCall())
        }catch (e: Exception){
            ApiOperation.Failure(e)
        }
    }





}

sealed interface ApiOperation<T>{
    class Success<T>(val data:T):ApiOperation<T>
    class Failure<T>(val exception: Exception): ApiOperation<T>

    fun onSuccess(callback:(T)->Unit):ApiOperation<T>{

        if(this is Success){
            callback(data)
        }
        return this
    }

    fun onFailure(callback:(Exception)->Unit):ApiOperation<T>{
        if (this is Failure){
            callback(exception)
        }
        return this
    }

}
