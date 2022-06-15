package com.example.wildberriesweek71

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.*
import java.lang.StringBuilder
import java.lang.reflect.Type

open class DotaHeroesViewModel : ViewModel() {

    val fileHeroData: File = File.createTempFile("HeroesDataFile", ".txt")

    val itemsDotaHeroes = MutableLiveData<List<DotaHeroesJSON>>()
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().build()
    var listMyData: Type = Types.newParameterizedType(
        MutableList::class.java,
        DotaHeroesJSON::class.java
    )
    var jsonAdapter: JsonAdapter<List<DotaHeroesJSON>> = moshi.adapter<List<DotaHeroesJSON>>(listMyData)
    var dotaHeroes: List<DotaHeroesJSON>? = null
    private var job: Job? = null

    fun request() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val request = Request.Builder()
                .url("https://api.opendota.com/api/heroStats")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    response.body?.string()?.let { writeDataToFile(fileHeroData, it) }
                    val heroDataFromFile = readDataFromFile(fileHeroData)
                    if (heroDataFromFile.isNotEmpty()) {
                        dotaHeroes = jsonAdapter.fromJson(heroDataFromFile)
                    } else jsonAdapter.fromJson(response.body!!.source())
                    itemsDotaHeroes.postValue(dotaHeroes!!)
            }
        }

    }

    fun writeDataToFile(file: File, text: String) {
        FileOutputStream(file).use {
            it.write(text.toByteArray())
        }
    }

    fun readDataFromFile(file: File): String {
        val text = FileInputStream(file).bufferedReader().use {
            it.readText()
        }
        return text
    }


    private fun read(context: Context, fileName: String): String? {
        return try {
            val fis: FileInputStream = context.openFileInput(fileName)
            val isr = InputStreamReader(fis)
            val bufferedReader = BufferedReader(isr)
            val sb = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            sb.toString()
        } catch (fileNotFound: FileNotFoundException) {
            null
        } catch (ioException: IOException) {
            null
        }
    }

    private fun create(context: Context, fileName: String, jsonString: String?): Boolean {
        val FILENAME = "storage.json"
        return try {
            val fos: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            if (jsonString != null) {
                fos.write(jsonString.toByteArray())
            }
            fos.close()
            true
        } catch (fileNotFound: FileNotFoundException) {
            false
        } catch (ioException: IOException) {
            false
        }
    }

    open fun isFilePresent(context: Context, fileName: String): Boolean {
        val path: String = context.getFilesDir().getAbsolutePath().toString() + "/" + fileName
        val file = File(path)
        return file.exists()
    }



}