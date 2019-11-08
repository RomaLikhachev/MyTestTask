package com.test.mytesttask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * ViewModelFactory, который использует Dagger для создания экземпляров.
 */
@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Searching VM by its class, returns Entry element with key and VM provider
        val foundMapEntry = creators.entries.find {
            modelClass.isAssignableFrom(it.key)
        }

        val viewModelCreator = foundMapEntry?.value
            ?: throw IllegalArgumentException("Unknown view model type class: $modelClass")

        try {
            @Suppress("UNCHECKED_CAST")
            return viewModelCreator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}