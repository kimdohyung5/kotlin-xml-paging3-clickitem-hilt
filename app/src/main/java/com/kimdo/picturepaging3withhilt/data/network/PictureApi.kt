package com.kimdo.picturepaging3withhilt.data.network


import com.kimdo.picturepaging3withhilt.domain.model.Picture
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureApi {


    //https://picsum.photos/v2/list?page=1&limit=100
    @GET("v2/list")
    suspend fun getPictures( @Query("page") page: Int, @Query("limit") limit: Int ) : List<Picture>
}


