package com.oays.fa.InList.updates

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.oays.fa.InList.App
import com.oays.fa.InList.R
import io.github.tonnyl.whatsnew.WhatsNew
import io.github.tonnyl.whatsnew.item.item
import io.github.tonnyl.whatsnew.item.whatsNew
import io.github.tonnyl.whatsnew.util.PresentationOption

object ReleaseNote {

    val releasesNotes = linkedMapOf(
            "1.0" to maj1Min1
    )

    private val maj1Min1
        get() =
            whatsNew {
                titleText = appContext.getString(R.string.enjoy)
                titleColor = ContextCompat.getColor(appContext, R.color.colorPrimaryDark)
                iconColor = ContextCompat.getColor(appContext, R.color.colorPrimaryDark)
                buttonBackground = ContextCompat.getColor(appContext, R.color.colorPrimaryDark)
                buttonText = appContext.getString(R.string.continue_button)
                buttonTextColor = ContextCompat.getColor(appContext, R.color.white)
                presentationOption = PresentationOption.ALWAYS

                item {
                    title = appContext.getString(R.string.external_folder_title)
                    content = appContext.getString(R.string.external_folder_content)
                    imageRes = R.drawable.ic_save_whatsnew_24dp
                }
                item {
                    title = appContext.getString(R.string.import_title)
                    content = appContext.getString(R.string.import_content)
                    imageRes = R.drawable.ic_add_circle_outline_whatsnew_24dp
                }
                item {
                    title = appContext.getString(R.string.pull_title)
                    content = appContext.getString(R.string.pull_content)
                    imageRes = R.drawable.ic_refresh_whatsnew_24dp
                }
                item {
                    title = appContext.getString(R.string.settings_title)
                    content = appContext.getString(R.string.settings_content)
                    imageRes = R.drawable.ic_settings_whatsnew_24dp
                }
                item {
                    title = appContext.getString(R.string.share_title)
                    content = appContext.getString(R.string.share_content)
                    imageRes = R.drawable.ic_share_whatsnew_24dp
                }

                item {
                    title = appContext.getString(R.string.tuto)
                    content = appContext.getString(R.string.click_to_add_list)
                    imageRes = R.drawable.ic_help_outline_black_24dp
                }
                item {
                    content = appContext.getString(R.string.switch_lists)
                }
                item {
                      content = appContext.getString(R.string.show_lists_option)
                }
                item {
                     content = appContext.getString(R.string.add_an_item)}
                item {
                    content = appContext.getString(R.string.swipe_to_delete)
                    imageRes = R.drawable.ic_delete_forever_primary_24dp
                }
                item {
                    content = appContext.getString(R.string.swipe_to_edit)
                    imageRes = R.drawable.ic_mode_edit_accent_24dp
                }
                 item {
                     content = appContext.getString(R.string.mark_as_done)
                     imageRes = R.drawable.ic_check_black_18dp
                 }
                item {
                      content = appContext.getString(R.string.quickly_add_a_comment)
                      imageRes = R.drawable.ic_expand_more_add
                }
                item {
                    content = appContext.getString(R.string.show_an_item_comments)
                    imageRes = R.drawable.ic_expand_more_add
                }

                item {
                    content = appContext.getString(R.string.see_again_in_settings)
                }
            }
}

fun WhatsNew.show(activity: AppCompatActivity) {
    setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    show(activity.supportFragmentManager, WhatsNew.TAG)
}

val appContext
    get() = App.instance.context