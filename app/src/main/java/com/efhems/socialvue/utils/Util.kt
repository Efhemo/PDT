package com.efhems.socialvue.utils

import android.content.Context
import android.util.Log
import com.efhems.socialvue.model.Model
import com.efhems.socialvue.model.Professional
import org.json.JSONArray
import java.io.IOException
import java.nio.charset.Charset

fun dataAssets(context: Context): String? {
    val json: String

    try {
        val `is` = context.resources.assets.open("data.json")
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        json = String(buffer, Charset.forName("UTF-8"))
        return json
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return null

}

fun extractJson(context: Context): List<Model> {
    val dataAsset = dataAssets(context)

    Log.i("Data", dataAsset)

    val jsonObject = JSONArray(dataAsset)

    /*var i = 0
    var x = 0*/
    var prof: Professional? = null
    var professionals = ArrayList<Professional>()
    var models = ArrayList<Model>()

    for (i in 0 until jsonObject.length() - 1){

        val jsonMOdel = jsonObject.getJSONObject(i)

        val jsonProf = jsonMOdel.getJSONArray("professionals")
        Log.i("array", jsonProf.toString())

        for (x in 0 until jsonProf.length()){
            val profObject = jsonProf.getJSONObject(x)
            prof = Professional(profObject.getString("address"),
                    profObject.getString("created_date"),
                    profObject.getBoolean("full_time"),
                    profObject.getString("image"),
                    profObject.getString("message"),
                    profObject.getString("name"),
                    profObject.getString("rating"))
                    professionals.add(prof)

        }

        val model = Model(jsonMOdel.getString("category"),
                jsonMOdel.getString("icon"), jsonMOdel.getInt("id"), professionals )

        models.add(model)
    }


    /*while (i < jsonObject.length()){
        val jsonMOdel = jsonObject.getJSONObject(i)

        val jsonProf = jsonMOdel.getJSONArray("professionals")

        while (x < jsonProf.length()){
            val profObject = jsonProf.getJSONObject(x)
             prof = Professional(profObject.getString("address"),
                     profObject.getString("created_date"),
                     profObject.getBoolean("full_time"),
                     profObject.getString("image"),
                     profObject.getString("message"),
                     profObject.getString("name"),
                     profObject.getString("rating"))
            professionals[x] = prof
            x++
        }

        val model = Model(jsonMOdel.getString("category"),
                jsonMOdel.getString("icon"), jsonMOdel.getInt("id"), professionals )

        models[i] = model
        i++
    }*/

    return models
}

fun getResId(resName: String, c: Class<*>): Int {

    try {
        val idField = c.getDeclaredField(resName)
        return idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        return -1
    }

}