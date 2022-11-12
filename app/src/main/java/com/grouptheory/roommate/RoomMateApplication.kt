package com.grouptheory.roommate

import android.app.Application
import com.grouptheory.roommate.repository.FbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RoomMateApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val repository by lazy {FbRepository()}
}