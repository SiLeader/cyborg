package net.sileader.cyborg.appman

import org.json.JSONArray

class ApplicationManager(val applications: List<Application>) {
    constructor(json: JSONArray) : this((0..(json.length()-1)).map { Application(json.getJSONObject(it)) })
    constructor(json: String) : this(JSONArray(json))

    private val mApps: MutableMap<String, Application> = mutableMapOf()

    init {
        applications.map {
            app ->
            mApps.put(app.name, app)
        }
    }

    operator fun get(appName: String): Application? =mApps[appName]

    fun toJson(): JSONArray {
        val json=JSONArray()
        for(app in applications) {
            json.put(app.toJson())
        }
        return json
    }
}
