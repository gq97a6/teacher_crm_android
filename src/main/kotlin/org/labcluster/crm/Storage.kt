package org.labcluster.crm

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.io.File
import java.io.FileReader

object Storage {

    val appJson = Json {
        serializersModule = SerializersModule {
            contextual(MutableStateFlow::class) { args -> StateFlowSerializer(args[0]) }
        }
    }

    inline fun <reified T> T.prepareSave(serializer: Json = appJson): String = try {
        appJson.encodeToString(this)
    } catch (e: Exception) {
        ""
    }

    inline fun <reified T> T.dumpToFile(
        path: String = "",
        save: String = this.prepareSave()
    ) = try {
        File(path).writeText(save)
    } catch (e: Exception) {
        null
    }

    fun getSave(path: String) = try {
        FileReader(path).readText()
    } catch (e: Exception) {
        ""
    }

    inline fun <reified T> getFromFile(
        path: String = "",
        serializer: Json = appJson,
        save: String = getSave(path)
    ): T? = try {
        serializer.decodeFromString(save)
    } catch (e: Exception) {
        null
    }
}