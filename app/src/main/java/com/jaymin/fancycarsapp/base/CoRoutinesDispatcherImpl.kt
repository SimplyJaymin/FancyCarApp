package com.jaymin.fancycarsapp.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoRoutinesDispatcherImpl : ICoRoutinesDispatcher {

    override fun ui(): CoroutineDispatcher = Dispatchers.Main

    override fun io(): CoroutineDispatcher = Dispatchers.IO
}