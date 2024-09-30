package kata.practice.concertcomparison.common.response

import kata.practice.concertcomparison.common.exception.BaseException
import kata.practice.concertcomparison.common.exception.ErrorCode



class ResponseException(
    val message: String? = null,
    val errorCode: Int? = null
){

    constructor(exception: BaseException): this(
        message = exception.message,
        errorCode = exception.errorCode?.errorCode
    )

    constructor(errorCode: ErrorCode): this(
        message = errorCode.errorMessage,
        errorCode = errorCode.errorCode
    )
}
