package com.example.nexusplannerandroid.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.nexusplannerandroid.data.local.AppDataBase
import com.example.nexusplannerandroid.data.local.repository.*
import com.example.nexusplannerandroid.data.remote.api_dao.*
import com.example.nexusplannerandroid.utils.Constantes
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "NexusPlanner_db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesProyectoApi(moshi: Moshi): ProyectoApi {
        return Retrofit.Builder()
            .baseUrl(Constantes.ApiLink.value)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ProyectoApi::class.java)
    }

    @Singleton
    @Provides
    fun providesUsuarioApi(moshi: Moshi): UsuarioApi {
        return Retrofit.Builder()
            .baseUrl(Constantes.ApiLink.value)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UsuarioApi::class.java)
    }

    @Singleton
    @Provides
    fun providesTareaApi(moshi: Moshi): TareaApi {
        return Retrofit.Builder()
            .baseUrl(Constantes.ApiLink.value)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TareaApi::class.java)
    }

    @Singleton
    @Provides
    fun ProvideUsuarioRepository(appDataBase: AppDataBase): UsuarioRepository {
        return UsuarioRepository(appDataBase)
    }

    @Singleton
    @Provides
    fun ProvideProyectoRepository(appDataBase: AppDataBase): ProyectoRepository {
        return ProyectoRepository(appDataBase)
    }

    @Singleton
    @Provides
    fun ProvideTareaRepository(appDataBase: AppDataBase): TareaRepository {
        return TareaRepository(appDataBase)
    }
}