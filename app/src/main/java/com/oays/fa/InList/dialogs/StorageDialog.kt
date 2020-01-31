package com.oays.fa.InList.dialogs

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.codekidlabs.storagechooser.Content
import com.codekidlabs.storagechooser.StorageChooser
import com.oays.fa.InList.App
import com.oays.fa.InList.MainActivity
import com.oays.fa.InList.R
import com.oays.fa.InList.util.withStoragePermission
import kotlinx.android.synthetic.main.dialog_list_path.view.*


@SuppressLint("InflateParams")
fun defaultPathDialog(activity: MainActivity, onPathChosen: (String) -> Unit) {
    val view = LayoutInflater.from(activity).inflate(R.layout.dialog_list_path, null)
    view.listPathTitle.text = activity.getString(R.string.default_storage_folder)
    displayDialog(view, activity, onPathChosen)
}

@SuppressLint("InflateParams")
fun storagePathDialog(activity: MainActivity, onPathChosen: (String) -> Unit) {
    val view = LayoutInflater.from(activity).inflate(R.layout.dialog_list_path, null)
    displayDialog(view, activity, onPathChosen)
}

fun displayDialog(view: View, activity: MainActivity, onPathChosen: (String) -> Unit) {

    val dialog = AlertDialog.Builder(activity).run {
        setView(view)
        create()
    }.apply {
        setCanceledOnTouchOutside(true)
        show()
    }

    view.apply {
        appPrivateStorageButton.setOnClickListener {
            onPathChosen("")
            dialog.dismiss()
        }
        chooseFolderButton.setOnClickListener {
            selectDirectory(activity, onPathChosen)
            dialog.dismiss()
        }

        helpChangePath.setOnClickListener {
            AlertDialog.Builder(activity)
                    .setMessage(R.string.changeFolderHelp)
                    .setPositiveButton(activity.getString(R.string.ok_with_tick), null)
                    .create().apply {
                        setCanceledOnTouchOutside(true)
                    }.show()
        }
        cancelChangePath.setOnClickListener { dialog.dismiss() }

    }
}

fun selectDirectory(activity: MainActivity, onPathChosen: (String) -> Any?) {
    withStoragePermission(activity) {
        @Suppress("DEPRECATION")
        StorageChooser.Builder()
                .withActivity(activity)
                .withContent(storageChooserLocales)
                .withFragmentManager(activity.fragmentManager) // activity.fragmentManager deprecated, but lib StorageChooser hasn't fully migrated to androidx yet.
                .withMemoryBar(true)
                .allowCustomPath(true)
                .allowAddFolder(true)
                .setType(StorageChooser.DIRECTORY_CHOOSER)
                .build()
                .apply {
                    show()
                    setOnSelectListener {
                        onPathChosen(it)
                    }
                }
    }
}

fun selectFile(activity: MainActivity, onPathChosen: (String) -> Any?) {
    withStoragePermission(activity) {
        @Suppress("DEPRECATION")
        StorageChooser.Builder()
                .withActivity(activity)
                .withFragmentManager(activity.fragmentManager) // activity.fragmentManager deprecated, but lib StorageChooser hasn't fully migrated to androidx yet.
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.FILE_PICKER)
                .build()
                .apply {
                    show()
                    setOnSelectListener {
                        if(it.endsWith(".1list")) {
                            onPathChosen(it)
                        } else {
                            Toast.makeText(activity, activity.getString(R.string.not_a_1list_file), Toast.LENGTH_LONG).show()
                        }
                    }
                }
    }
}

val storageChooserLocales = Content().apply {
    selectLabel = App.instance.getString(R.string.storage_chooser_select_label)
    createLabel = App.instance.getString(R.string.storage_chooser_create_label)
    newFolderLabel = App.instance.getString(R.string.storage_chooser_new_folder_label)
    cancelLabel = App.instance.getString(R.string.storage_chooser_cancel_label)
    overviewHeading = App.instance.getString(R.string.storage_chooser_overview_heading)
    internalStorageText = App.instance.getString(R.string.storage_chooser_internal_storage_text)
    freeSpaceText = App.instance.getString(R.string.storage_chooser_free_space_text)
    folderCreatedToastText = App.instance.getString(R.string.storage_chooser_folder_created_toast_text)
    folderErrorToastText = App.instance.getString(R.string.storage_chooser_folder_error_toast_text)
    textfieldHintText = App.instance.getString(R.string.storage_chooser_text_field_hint_text)
    textfieldErrorText = App.instance.getString(R.string.storage_chooser_text_field_error_text)
}