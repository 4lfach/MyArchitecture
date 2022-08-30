package com.selftutor.myarchitecture.view.changecolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.GridLayoutManager
import com.selftutor.myarchitecture.R
import com.selftutor.myarchitecture.databinding.FragmentChangeColorBinding
import com.selftutor.myarchitecture.view.HasScreenTitle
import com.selftutor.myarchitecture.view.base.BaseFragment
import com.selftutor.myarchitecture.view.base.BaseScreen
import com.selftutor.myarchitecture.view.base.screenViewModel

class ChangeColorFragment : BaseFragment(), HasScreenTitle {

	override val viewModel by screenViewModel<ChangeColorViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val binding = FragmentChangeColorBinding.inflate(inflater, container, false)

		val adapter = ColorsAdapter(viewModel)
		setupLayoutManager(binding, adapter)

		binding.btnSave.setOnClickListener { viewModel.onSavePressed() }
		binding.btnCancel.setOnClickListener { viewModel.onCancelPressed() }

		viewModel.colorsList.observe(viewLifecycleOwner) {
			adapter.items = it
		}

		viewModel.screenTitle.observe(viewLifecycleOwner){
			notifyScreenUpdates()
		}

		return binding.root
	}

	private fun setupLayoutManager(binding: FragmentChangeColorBinding, adapter: ColorsAdapter) {
		binding.rvColors.viewTreeObserver.addOnGlobalLayoutListener(object :
			ViewTreeObserver.OnGlobalLayoutListener {
			override fun onGlobalLayout() {
				binding.rvColors.viewTreeObserver.removeOnGlobalLayoutListener(this)

				val width = binding.rvColors.width
				val itemWidth = resources.getDimensionPixelSize(R.dimen.item_width)
				val columns = width / itemWidth

				binding.rvColors.adapter = adapter
				binding.rvColors.layoutManager = GridLayoutManager(requireContext(), columns)
			}
		})
	}


	override fun getScreenTitle(): String? {
		return viewModel.screenTitle.value
	}

	class Screen(val currentColorId: Long) : BaseScreen
}