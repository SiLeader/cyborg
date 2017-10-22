package net.sileader.cyborg.appman

import org.json.JSONObject

class Application(val name: String, val library: String, val className: String, val permission: Permission) {
    companion object {
        const val APP_NAME="app"
        const val APP_LIB="lib"
        const val APP_PERMIT="permit"
        const val APP_CLASS_NAME="class"
    }

    constructor(json: JSONObject)
            : this(
            json.getString(APP_NAME),
            json.getString(APP_LIB),
            json.getString(APP_CLASS_NAME),
            Permission(json.getJSONArray(APP_PERMIT)))

    fun toJson(): JSONObject {
        val json=JSONObject()
        json.put(APP_NAME, name)
        json.put(APP_LIB, library)
        json.put(APP_CLASS_NAME, className)
        json.put(APP_CLASS_NAME, permission.toJson())

        return json
    }

    val homeDirectory: String
    get() = "/cyborg/data/$className"
}
