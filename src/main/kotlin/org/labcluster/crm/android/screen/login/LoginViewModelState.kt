@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.Open
import org.labcluster.crm.shared.model.Teacher

@Open
@Serializable
class LoginViewModelState() {
    @Transient
    val isAuthorized = MutableStateFlow(false)
    val teacher = MutableStateFlow(Teacher())
}