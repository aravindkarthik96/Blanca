package com.aravindkarthik.blanca.lang.drawing

import com.aravindkarthik.blanca.lang.core.DataTypes
import com.aravindkarthik.blanca.lang.core.Function
import com.aravindkarthik.blanca.lang.core.FunctionDocumentation
import com.aravindkarthik.blanca.lang.core.IntType
import kotlinx.coroutines.*

class DelayFunction : Function() {

    override val name: String
        get() = "delay"
    override val functionArguments: List<DataTypes>
        get() = listOf(IntType)

    override suspend fun invokeFunction(lineNumber: Int, arguments: List<String>) {
        delay(arguments[0].toLong())
    }

    override val documentation: FunctionDocumentation
        get() = FunctionDocumentation(
            title = "delay(milliseconds)",
            description = "adds a delay for the specified time in milliseconds",
            exampleCode = "drawCircle(200,200,200)\ndelay(1000)\nclear()",
            runExample = { canvas, _ ->
                GlobalScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.Main) { canvas.drawCircle(200, 200, 200) }
                    delay(1000)
                    withContext(Dispatchers.Main) { canvas.clear() }
                }
            }
        )

}