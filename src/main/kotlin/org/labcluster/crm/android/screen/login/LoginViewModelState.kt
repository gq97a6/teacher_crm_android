@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.shared.Open
import org.labcluster.crm.shared.model.Teacher

@Open
@Serializable
class LoginViewModelState() {
    @Transient
    val authToken = MutableStateFlow("")
    val teacher = MutableStateFlow(Teacher())

    //Mockup
    suspend fun login(api: AppApi = App.api) {
        authToken.update { api.getAuthToken() }

        //Refetch teacher
        teacher.update { api.getTeacher("01ef4c39-9577-4eeb-a017-b3e1a9e38864") ?: Teacher() }
    }
}