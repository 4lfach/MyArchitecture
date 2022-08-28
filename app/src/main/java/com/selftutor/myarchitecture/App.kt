package com.selftutor.myarchitecture

import android.app.Application
import com.selftutor.myarchitecture.model.colors.InMemoryColorsRepository


/**
 * This class stores instances of models
 */
class App: Application() {


	//These repositories will be accessible throughout the whole app life
	val models = listOf<Any>(
		InMemoryColorsRepository()
	)
}