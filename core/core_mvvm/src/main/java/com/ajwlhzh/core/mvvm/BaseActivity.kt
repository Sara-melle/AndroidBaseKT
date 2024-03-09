package com.ajwlhzh.core.mvvm

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/08 15:11:10
 */
abstract class BaseActivity : AppCompatActivity() {

    private val mLayoutId: Int by lazy {
        getLayoutId()
    }

    protected open var backPressedCallbackEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(backPressedCallbackEnable) {
                override fun handleOnBackPressed() {
                    onBackPressedCallback()
                }
            })
        setupContentView()
        savedInstanceState?.let { loadSavedState(it) }
        setupStatusBar()
        setup()
    }

    protected open fun setupContentView() {
        if (mLayoutId != 0) {
            setContentView(mLayoutId)
        }
    }

    protected open fun loadSavedState(savedInstanceState: Bundle) {}

    private fun setupStatusBar() {}

    private fun setup() {}

    abstract fun getLayoutId(): Int

    protected open fun onBackPressedCallback() {}


}