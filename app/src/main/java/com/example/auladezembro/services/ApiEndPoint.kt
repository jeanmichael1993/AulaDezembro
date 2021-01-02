package com.example.auladezembro.services

import com.example.auladezembro.dto.DtoUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiEndPoint {
    @GET("users")
    //users é api que está consumindo, por exemplo https://jsonplaceholder.typicode.com/users"
    fun obterUsuarios(): Call<List<DtoUser>>
    //User é o nome da classe, obterUsuario é o nome do metodo, : é mostrando o tipo do return

    @POST("users")
    fun cadastrarUsuario(@Body dtoUser: DtoUser): Call<DtoUser>
}