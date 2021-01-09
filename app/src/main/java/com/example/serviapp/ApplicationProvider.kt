package com.example.serviapp

import android.app.Application
import android.content.Context

class ApplicationProvider : Application() {
    open var appContext: Context = this
}
