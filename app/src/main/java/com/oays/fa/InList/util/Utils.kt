package com.oays.fa.InList.util

import android.Manifest
import android.content.Context
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.IOException
import java.util.*
import com.oays.fa.InList.App
import com.oays.fa.InList.model.ItemList
import com.oays.fa.InList.MainActivity

fun loadJSONFromAsset(context: Context, filename: String): String {
    val json: String?
    try {
        val `is` = context.assets.open(filename)
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        json = String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return ""
    }
    return json
}

fun dpToPx(dp: Int): Int {
    val density = App.instance.resources.displayMetrics.density
    return Math.round(dp.toFloat() * density)
}

fun String.removeForbidenChars(): String {
    return replace("""[|?*<":>+\[\]/']""".toRegex(), "").toLowerCase(Locale.getDefault())
}

val ItemList.notCustomPath
    get() = path.endsWith("${stableId}.1list")

fun ItemList.getNewPath(newTitle: String) = "${path.substringBeforeLast("/")}/${newTitle.removeForbidenChars()}-${stableId}.1list"

val ItemList.fileName
        get() = "${title.removeForbidenChars()}-${stableId}.1list"


fun withStoragePermission(activity: MainActivity, block : () -> Unit) {
    Dexter.withActivity(activity)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    activity.whenResumed = block
                }
                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    Toast.makeText(activity, "Permission is required to access external storage.", Toast.LENGTH_LONG).show()
                }
                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                    token.continuePermissionRequest()
                }
            }).check()
}
