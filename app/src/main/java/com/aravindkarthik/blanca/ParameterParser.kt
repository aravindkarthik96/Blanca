package com.aravindkarthik.blanca

fun String.parseParams(): List<String>? {
    val parametersString = this.substringAfter("(").substringBeforeLast(")")
    return if (parametersString.isNotEmpty()) {
        if (parametersString.contains("(")) {
            return null
        }

        if (parametersString.contains(",")) {
            parametersString.substring(0, parametersString.length).split(",")
        } else {
            listOf(parametersString.substring(0, parametersString.length))
        }
    } else {
        null
    }
}
