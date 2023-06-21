package id.pixis.dompetkita.utils

import org.json.JSONArray
import org.json.JSONObject


object Extentions {
    fun JSONArray.foreach(callback: (data: JSONObject) -> Unit) {
        for(position in 0 until this.length()){
            callback.invoke(this.getJSONObject(position))
        }
    }
}