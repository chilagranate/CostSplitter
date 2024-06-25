package com.example.costsplitter

import android.app.Application
import com.example.costsplitter.data.AppContainer
import com.example.costsplitter.data.AppDataContainer

class CostSplitterApplication: Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}