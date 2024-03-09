package com.ajwlhzh.core.mvvm

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.ajwlhzh.core.mvvm.ext.getViewBinding
import java.lang.RuntimeException

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/08 15:38:06
 */
abstract class BaseBindingActivity<VB : ViewDataBinding> : BaseActivity(),
    BaseBinding<VB> {

    protected val mBinding: VB by lazy {
        getViewBinding<VB>(layoutInflater) ?: throw RuntimeException("加载ViewBinding失败！")
    }

    override fun getLayoutId(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.initBindingView()
        mBinding.initBindingData()
        setupView()
        setupData()
    }

    protected open fun setupView(){}

    protected open fun setupData(){}

    override fun setupContentView() {
        setContentView(mBinding.root)
    }
}