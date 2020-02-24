package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.IntType
import kotlinx.coroutines.delay

class DelayFunction : Function() {

    override val name: String
        get() = "delay"
    override val functionArguments: List<DataTypes>
        get() = listOf(IntType)

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
            delay(arguments[0].toLong())
    }
}