package org.labcluster.crm.objects

import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileReader

object Storage {

    fun Any.prepareSave(serializer: Json = Json): String = serializer.encodeToString(this)

    fun Any.dumpToFile(
        path: String = "",
        save: String = this.prepareSave()
    ) = try {
        File(path).writeText(save)
    } catch (_: Exception) {
    }

    fun getSave(path: String) = try {
        FileReader(path).readText()
    } catch (_: Exception) {
        ""
    }

    inline fun <reified T> getFromFile(
        path: String = "",
        serializer: Json = Json,
        save: String = getSave(path)
    ): T? = try {
        serializer.decodeFromString(save)
    } catch (_: Exception) {
        null
    }
}