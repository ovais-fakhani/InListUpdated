package com.oays.fa.InList.model

import com.google.gson.annotations.SerializedName
import com.oays.fa.InList.App
import com.oays.fa.InList.R

data class ItemList(@SerializedName("title") var title: String = "", @SerializedName("items") val items: MutableList<Item> = arrayListOf(), @SerializedName("stableId") val stableId: Long = System.currentTimeMillis(), @Transient var path: String = "") {
    override fun toString(): String {
        return """
$title:

${items.joinToString("\n") { "$it" }}

${App.instance.getString(R.string.list_created_with_one1ist)}
${App.instance.getString(R.string.app_link)}
        """.trimIndent()
    }
}
