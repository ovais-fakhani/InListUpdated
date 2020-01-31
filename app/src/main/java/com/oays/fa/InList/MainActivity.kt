package com.oays.fa.InList

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.oays.fa.InList.updates.ReleaseNote
import com.oays.fa.InList.updates.show
import io.github.tonnyl.whatsnew.WhatsNew
import kotlinx.android.synthetic.main.fragment_one_list.*

class MainActivity : AppCompatActivity() {


    val persistence: PersistenceHelper by lazy { PersistenceHelper(this) }

    // On some devices, displaying storage chooser fragment before activity is resumed leads to a crash.
    // This is a workaround.
    var whenResumed = {}
    set(value) {
        if(this.isResumed) value()
        else field = value
    }
    private var isResumed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = OneListFragment().apply {
            if (intent.action == "android.intent.action.VIEW")
                arguments = Bundle().apply {
                    putParcelable("EXT_FILE_URI", intent.data)
                }
        }

        savedInstanceState ?: supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.zoom_in, R.anim.zoom_out, R.anim.zoom_in, R.anim.zoom_out)
                .replace(R.id.fragmentContainer, fragment, "OneListFragment")
                .commit()

        // WORKAROUND FOR WHATSNEW LIB NOT HANDLING WELL CONFIG CHANGES
        if(savedInstanceState != null) {
            supportFragmentManager.findFragmentByTag(WhatsNew.TAG)
                    ?.let { supportFragmentManager.beginTransaction().remove(it).commit() }
                    ?.let { ReleaseNote.releasesNotes.entries.last().value.show(this) }
        }


    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        supportFragmentManager.fragments.filterIsInstance<OnDispatchTouchEvent>().forEach {
            it.onDispatchTouchEvent(ev)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        whenResumed()
        whenResumed = {}
        isResumed = true
    }

    override fun onPause() {
        super.onPause()
        isResumed = false
    }

    interface OnDispatchTouchEvent {
        fun onDispatchTouchEvent(ev: MotionEvent)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}