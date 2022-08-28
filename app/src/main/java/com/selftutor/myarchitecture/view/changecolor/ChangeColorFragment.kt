package com.selftutor.myarchitecture.view.changecolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.selftutor.myarchitecture.view.HasScreenTitle
import com.selftutor.myarchitecture.view.base.BaseFragment
import com.selftutor.myarchitecture.view.base.BaseScreen
import com.selftutor.myarchitecture.view.base.BaseViewModel

class ChangeColorFragment : BaseFragment(), HasScreenTitle {

	override val viewModel by viewModels<ChangeColorViewModel>()
	//todo change viewmodels to specific viewmodel factory

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return super.onCreateView(inflater, container, savedInstanceState)
	}

	override fun getScreenTitle(): String? {
		TODO("Not yet implemented")
	}

	class Screen(val currentColorId: Int): BaseScreen
}