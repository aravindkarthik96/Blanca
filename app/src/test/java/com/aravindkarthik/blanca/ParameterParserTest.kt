package com.aravindkarthik.blanca

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ParameterParserTest {

    @Test
    fun `should return a list of parameters if there are more than one parameters`() {
        //given
        val codeline = "drawLine(10,20,20,10)"

        //when
        val actual = codeline.parseParams()

        //then
        val expected = listOf("10", "20", "20", "10")

        assertEquals(expected, actual)
    }

    @Test
    fun `should return a list of parameters if there is a function as a parameter`() {
        //given
        val codeline = "drawLine(10,20,20,drawCircle())"

        //when
        val actual = codeline.parseParams()

        //then
        val expected = null

        assertEquals(expected, actual)
    }

    @Test
    fun `should return a list of parameters if there is a function with parameters as a parameter`() {
        //given
        val codeline = "drawLine(10,20,20,drawCircle(10,20))"

        //when
        val actual = codeline.parseParams()

        //then
        val expected = null

        assertEquals(expected, actual)
    }

    @Test
    fun `should return a list of parameters if there is only one parameter`() {
        //given
        val codeline = "drawLine(10)"

        //when
        val actual = codeline.parseParams()

        //then
        val expected = listOf("10")

        assertEquals(expected, actual)
    }

    @Test
    fun `should return null if there are no parameters available`() {
        //given
        val codeline = "drawLine()"

        //when
        val actual = codeline.parseParams()

        //then
        val expected = null

        assertEquals(expected, actual)
    }
}