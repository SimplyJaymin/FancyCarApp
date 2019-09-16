package com.jaymin.fancycarsapp

import com.jaymin.fancycarsapp.base.ICoRoutinesDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MockedCoRoutinesDispatcher : ICoRoutinesDispatcher {

    override fun ui(): CoroutineDispatcher = Dispatchers.Main

    override fun io(): CoroutineDispatcher = Dispatchers.Main
}