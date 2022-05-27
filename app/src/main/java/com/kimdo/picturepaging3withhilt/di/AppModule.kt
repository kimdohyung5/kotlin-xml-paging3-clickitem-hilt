package com.kimdo.picturepaging3withhilt.di

import com.kimdo.picturepaging3withhilt.Constants.BASE_URL
import com.kimdo.picturepaging3withhilt.data.network.PictureApi
import com.kimdo.picturepaging3withhilt.data.repository.PicturePagingRepositoryImpl
import com.kimdo.picturepaging3withhilt.domain.repository.PicturePagingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePictureApi(): PictureApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create( PictureApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository( api: PictureApi): PicturePagingRepository {
        return PicturePagingRepositoryImpl( api )
    }

}