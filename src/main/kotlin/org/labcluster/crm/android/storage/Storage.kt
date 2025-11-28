package org.labcluster.crm.android.storage

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.labcluster.crm.android.StateFlowSerializer
import java.io.File
import java.io.FileReader

object Storage {

    val serializer = Json {
        serializersModule = SerializersModule {
            contextual(MutableStateFlow::class) { args -> StateFlowSerializer(args[0]) }
        }
    }

    inline fun <reified T> T.deepCopy(serializer: Json = Storage.serializer): T? = try {
        val encoded = serializer.encodeToString(this)
        serializer.decodeFromString(encoded)
    } catch (e: Exception) {
        null
    }

    inline fun <reified T> T.prepareSave(serializer: Json = Storage.serializer): String = try {
        serializer.encodeToString(this)
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
        serializer: Json = Storage.serializer,
        save: String = getSave(path)
    ): T? = try {
        serializer.decodeFromString(save)
    } catch (e: Exception) {
        null
    }
}