package com.selftutor.myarchitecture.view.currentcolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.selftutor.myarchitecture.databinding.FragmentCurrentColorBinding
import com.selftutor.myarchitecture.view.base.BaseFragment
import com.selftutor.myarchitecture.view.base.BaseScreen
import com.selftutor.myarchitecture.view.base.BaseViewModel
import com.selftutor.myarchitecture.view.base.screenViewModel

class CurrentColorFragment: BaseFragment() {
	override val viewModel by screenViewModel<CurrentColorViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
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