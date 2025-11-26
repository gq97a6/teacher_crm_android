package org.labcluster.crm.android.app

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.labcluster.crm.android.Open
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class AppApi(
    val state: AppState,
    val url: String = "https://crm.labcluster.org"
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

    suspend fun fetchTeacherTimetable(teacherUuid: String): List<Lesson> {
        return try {
            val response = client.get("$url/api/lesson/teacherTimetable/$teacherUuid")
            return if (response.status == HttpStatusCode.OK) response.body<List<Lesson>>()
            else listOf()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun fetchGroupTimetable(teacherUuid: String): List<Lesson> {
        return try {
            val response = client.get("$url/api/lesson/groupTimetable/$teacherUuid")
            return if (response.status == HttpStatusCode.OK) response.body<List<Lesson>>()
            else listOf()
        } catch (e: Exception) {
            listOf()
        }
    }

    suspend fun fetchGroupNextLesson(groupUuid: String): Lesson? {
        return try {
            val response = client.get("$url/api/lesson/groupNextLesson/$groupUuid")
            return if (response.status == HttpStatusCode.OK) response.body<Lesson>()
            else null
        } catch (e: Exception) {
            null
        }
    }

    suspend fun fetchGroupsTaughtBy(teacherUuid: String): List<Group> {
        return try {
            val response = client.get("$url/api/group/taughtBy/$teacherUuid")
            return if (response.status == HttpStatusCode.OK) response.body<List<Group>>()
            else listOf()
        } catch (e: Exception) {
            listOf()
        }
    }

    fun authorize(): Boolean {
        return true
    }

    /*
    suspend fun postMatch(index: Int) {
        try {
            val response = client.post("$URL/api/match/current") {
                setBody("$index")
            }
            window.alert(if (response.status == HttpStatusCode.OK) "OK" else "ERROR")
        } catch (e: Exception) {
            println(e.message)
            window.alert("ERROR")
        }
    }

    @OptIn(InternalAPI::class)
    suspend fun postPlayers(players: List<Player>) {
        try {
            // Manually serialize the user object to JSON string
            val jsonBody = Json.encodeToString(ListSerializer(Player.serializer()), players)

            val response = client.post("$URL/api/player") {
                contentType(ContentType.Application.Json)
                body = jsonBody
            }
            window.alert(if (response.status == HttpStatusCode.OK) "OK" else "ERROR")
        } catch (e: Exception) {
            println(e.message)
            window.alert("ERROR")
        }
    }
     */
}