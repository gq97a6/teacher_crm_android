package org.labcluster.crm.android.app

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.labcluster.crm.android.Open
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class AppApi(
    private val state: AppState,
    private val url: String
) {
    @Open
    @Serializable
    class State()

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = true
                isLenient = true
                allowSpecialFloatingPointValues = true
                allowStructuredMapKeys = true
                prettyPrint = false
                useArrayPolymorphism = false
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchTeacherTimetable(teacherUuid: String): List<Lesson> = basicGet(
        response = { client.get("$url/lesson/teacherTimetable/$teacherUuid") },
        onSuccess = { it.body<List<Lesson>>() },
        onFailure = { listOf() },
        onException = { listOf() }
    )

    suspend fun fetchGroupTimetable(teacherUuid: String): List<Lesson> = basicGet(
        response = { client.get("$url/lesson/groupTimetable/$teacherUuid") },
        onSuccess = { it.body<List<Lesson>>() },
        onFailure = { listOf() },
        onException = { listOf() }
    )

    suspend fun fetchGroupNextLesson(groupUuid: String): Lesson? = basicGet(
        response = { client.get("$url/lesson/groupNextLesson/$groupUuid") },
        onSuccess = { it.body<Lesson>() },
        onFailure = { null },
        onException = { null }
    )

    suspend fun fetchGroupsTaughtBy(teacherUuid: String): List<Group> = basicGet(
        response = { client.get("$url/group/taughtBy/$teacherUuid") },
        onSuccess = { it.body<List<Group>>() },
        onFailure = { listOf() },
        onException = { listOf() }
    )

    suspend fun healthCheck(): Boolean = basicGet(
        response = { client.get("$url/health") },
        onSuccess = { true },
        onFailure = { false },
        onException = { false }
    )

    suspend fun authorize(): Boolean = basicGet(
        response = { client.get("$url/health") },
        onSuccess = { true },
        onFailure = { true },
        onException = { true }
    )

    private suspend fun <T> basicGet(
        response: suspend () -> HttpResponse,
        onSuccess: suspend (HttpResponse) -> T,
        onFailure: suspend (HttpResponse) -> T,
        onException: suspend (Exception) -> T,
    ): T = try {
        val response = response()
        if (response.status == HttpStatusCode.OK) onSuccess(response)
        else onFailure(response)
    } catch (e: Exception) {
        onException(e)
    }
}