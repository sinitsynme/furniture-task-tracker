package ru.sinitsynme.furnituretasktracker.exception

class IllegalActionException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}