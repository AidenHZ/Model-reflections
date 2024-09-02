package com.example.kotlintext

import android.telecom.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.File

interface GitHuber {
    @GET("/repos/{owner}/{repo}")
    fun getRepository(
        @Path("owner") owner:String,
        @Path("repo") repo: String
    ):retrofit2.Call<Repository>

}
fun main(){
    val retrofit = Retrofit .Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val gitHuberApi = retrofit.create(GitHuber::class.java)
    val respose = gitHuberApi.getRepository("JetBrains","Kotlin").execute()
    val repository = respose.body()
    if (repository ==null){
        println("Error${respose.code()} - ${respose .message()}")

    }else{
        println(repository.name)
        println(repository.git_url)
        println(repository.archive_url)
        println(repository.owner.login)

        File("kotlin.html").writeText("""
                <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>${repository.owner.login} - ${repository.name}</title>
            </head>
            <body>
                <h1><a href='${repository.html_url}'>${repository.owner.login} - ${repository.name}</a></h1>
                <p>${repository.description}</p>
                <p>Stars: ${repository.stargazers_count}</p>
                <p>Forks: ${repository.forks_count}</p>
            </body>
            </html>
            """.trimIndent()
        )

    }

}