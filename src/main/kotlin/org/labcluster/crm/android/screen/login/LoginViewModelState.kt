@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.shared.Open
import org.labcluster.crm.shared.model.Teacher

@Open
@Serializable
class LoginViewModelState() {
    val authToken = MutableStateFlow("")
    val teacher = MutableStateFlow(Teacher())

    //Mockup
    suspend fun login(uuid: String, api: AppApi = App.api): Boolean {
        val fetchedTeacher = api.getTeacher(uuid) ?: return false
        authToken.value = "123"
        teacher.update { fetchedTeacher }

        return true
    }
}