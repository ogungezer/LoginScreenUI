package com.example.loginscreenui.di

import android.content.Context
import com.example.loginscreenui.datastore.UserManagement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesUserManagementClass(@ApplicationContext app : Context) = UserManagement(context = app)


}