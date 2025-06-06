package com.example.goshopping.data.di

import android.content.Context
import com.example.goshopping.data.network.ShoppingItemService
import com.example.goshopping.data.prefs.GoShoppingPrefs
import com.example.goshopping.data.prefs.GoShoppingPrefsImpl
import com.example.goshopping.data.repositories.ShoppingItemRepository
import com.example.goshopping.data.repositories.ShoppingItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {
    @Provides
    @Singleton
    fun providesPrefs(@ApplicationContext applicationContext: Context): GoShoppingPrefs =
        GoShoppingPrefsImpl(applicationContext)

    @Provides
    @Singleton
    fun providesCountryRepository(
        service: ShoppingItemService,
    ): ShoppingItemRepository = ShoppingItemRepositoryImpl(service)
}
