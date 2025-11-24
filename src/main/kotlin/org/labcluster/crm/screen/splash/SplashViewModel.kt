@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.screen.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState

@Open
class SplashViewModel(val state: AppState = App.state) : ViewModel()