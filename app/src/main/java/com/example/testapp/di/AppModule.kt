package com.example.testapp.di

import android.content.Context
import androidx.room.Room
import com.example.testapp.data.local.MedicineDatabase
import com.example.testapp.data.remote.MedicineApi
import com.example.testapp.repository.MedicineRepository
import com.example.testapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMedicineRepository(
        api: MedicineApi
    ) = MedicineRepository(api)

    @Singleton
    @Provides
    fun provideMedicineApi(): MedicineApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MedicineApi::class.java)
    }


    @Provides
    fun provideMedicineDatabase(@ApplicationContext context: Context): MedicineDatabase {
        return Room.databaseBuilder(
            context,
            MedicineDatabase::class.java,
            "medicine-db"
        ).build()
    }
}