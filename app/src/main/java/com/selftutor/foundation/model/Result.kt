package com.selftutor.foundation.model

typealias mapper<Input, Output> = (Input) -> Output

sealed class Result<T>{
	fun <R> map(mapper: mapper<T,R>? = null): Result<R> = when(this){
		is SuccessResult ->{
			if(mapper!= null)
				SuccessResult(mapper(this.data))
			else
				throw IllegalArgumentException("Mapper should not be NULL if result is successful")
		}
		is ExceptionResult -> ExceptionResult(this.exception)
		is PendingResult -> PendingResult()
	}
 }

class PendingResult<T>() : Result<T>()

class SuccessResult<T>(
	val data: T
	) : Result<T>()

class ExceptionResult<T>(
	val exception: Exception
	) : Result<T>()

fun <T> Result<T>?.takeSuccess(): T? {
	return if(this is SuccessResult)
		this.data
	else
		null

}
