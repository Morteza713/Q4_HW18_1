package com.example.q4_hw18_1.di

import android.content.Context
import androidx.room.Room
import com.example.q4_hw18_1.data.datasource.database.MainDatabase
import com.example.q4_hw18_1.data.network.remote.ApiUser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase = Room.databaseBuilder(
        context, MainDatabase::class.java, "users_database").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(db: MainDatabase) = db.UserDao()
}
