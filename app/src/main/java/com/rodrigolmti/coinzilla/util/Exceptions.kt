package com.rodrigolmti.coinzilla.util

class ApiException(override val message: String) : Exception()
class ConnectionException : Exception()
class RtfmException(message: String) : NoSuchMethodException(message)
class UnnecessaryRefresh : Exception()