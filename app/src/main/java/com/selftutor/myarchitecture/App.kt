package com.selftutor.myarchitecture

import android.app.Application
import com.selftutor.foundation.BaseApplication
import com.selftutor.foundation.model.Repository
import com.selftutor.myarchitecture.model.colors.InMemoryColorsRepository


/**
 * This class stores instances of models
 */
class App: Application(), BaseApplication {

	override val repositories: List<Repository> = listOf(
		InMemoryColorsRepository()
	)
}