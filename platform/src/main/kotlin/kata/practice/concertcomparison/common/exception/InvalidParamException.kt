package kata.practice.concertcomparison.common.exception

import kata.practice.concertcomparison.common.exception.ErrorCode.COMMON_INVALID_PARAMETER

class InvalidParamException : BaseException {
    constructor() : super(COMMON_INVALID_PARAMETER)

    constructor(errorCode: ErrorCode) : super(errorCode)

    constructor(errorMsg: String) : super(errorMsg, COMMON_INVALID_PARAMETER)

    constructor(message: String, errorCode: ErrorCode) : super(message, errorCode)
}
