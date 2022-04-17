package com.cerberus.galtwallet.dependency

import android.content.Context
import android.util.Log
import com.cerberus.galtwallet.domain.repository.PrivateKeyRepository
import com.cerberus.galtwallet.domain.repository.TransactionRepository
import com.cerberus.galtwallet.infrastructure.repository.MockTransactionRepository
import com.cerberus.galtwallet.infrastructure.repository.SharedPreferencePrivateKeyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val TAG = "AppModule"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    init {
        Log.d(TAG, "Initializing dependencies")
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(): TransactionRepository = MockTransactionRepository()

    @Provides
    @Singleton
    fun providePrivateKeyRepository(
        @ApplicationContext context: Context
    ): PrivateKeyRepository = SharedPreferencePrivateKeyRepository(context)
}
