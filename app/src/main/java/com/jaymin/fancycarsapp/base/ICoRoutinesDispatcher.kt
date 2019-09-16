package com.jaymin.fancycarsapp.base

import kotlinx.coroutines.CoroutineDispatcher

interface ICoRoutinesDispatcher {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}