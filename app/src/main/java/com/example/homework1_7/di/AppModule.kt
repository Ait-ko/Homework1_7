package com.example.homework1_7.di

import androidx.databinding.tool.Context
import androidx.room.Room
import com.example.homework1_7.data.AppApiService
import com.example.homework1_7.data.AppDatabase
import com.example.homework1_7.data.Repository
import com.example.homework1_7.domain.repositories.FirstRepository
import com.example.homework1_7.domain.repositories.SecondRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
    @InstallIn(SingletonComponent::class)
    object AppModule {

        @Singleton
        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://cars.cprogroup.ru/api/rubetek/")
                .client(okHttpClient)
                .build()
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(
            interceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .callTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideLogginInterseptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @Provides
        fun provideAppService(
            retrofit: Retrofit
        ): AppApiService {
            return retrofit.create(AppApiService::class.java)
        }
    @Singleton
    @Provides
    fun provideFirstRepository(api: AppApiService,db: AppDatabase): FirstRepository {
        return Repository(api,db)
    }

    @Singleton
    @Provides
    fun provideSecondRepository(api: AppApiService,db:AppDatabase): SecondRepository {
        return Repository(api,db)
    }
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database-name"
        ).build()
    }


    }