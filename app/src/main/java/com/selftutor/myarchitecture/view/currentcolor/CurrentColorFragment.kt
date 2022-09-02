package com.selftutor.myarchitecture.view.currentcolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.selftutor.myarchitecture.databinding.FragmentCurrentColorBinding
import com.selftutor.foundation.view.BaseFragment
import com.selftutor.foundation.view.BaseScreen
import com.selftutor.foundation.view.screenViewModel

class CurrentColorFragment: BaseFragment() {
	override val viewModel by screenViewModel<CurrentColorViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val binding = FragmentCurrentColorBinding.inflate(inflater, container, false)

		viewModel.currentColor.observe(viewLifecycleOwner){
			binding.colorView.setBackgroundColor(it.value)
		}

		binding.btnChangeColor.setOnClickListener {
			viewModel.onChangeColor()
		}
		return binding.root
	}

	class Screen : BaseScreen
}