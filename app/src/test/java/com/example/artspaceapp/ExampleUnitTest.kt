package com.example.artspaceapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun nextButton_2To0() {
        val isNext = true
        var currentPage = 2
        currentPage = currentPageUpdate(isNext = isNext, currentPage = currentPage)
        assertEquals(0, currentPage)
    }

    @Test
    fun nextButton_0To1() {
        val isNext = true
        var currentPage = 0
        currentPage = currentPageUpdate(isNext = isNext, currentPage = currentPage)
        assertEquals(1, currentPage)
    }

    @Test
    fun previousButton_2To1() {
        val isNext = false
        var currentPage = 2
        currentPage = currentPageUpdate(isNext = isNext, currentPage = currentPage)
        assertEquals(1, currentPage)
    }

    @Test
    fun previousButton_0To2() {
        val isNext = false
        var currentPage = 0
        currentPage = currentPageUpdate(isNext = isNext, currentPage = currentPage)
        assertEquals(2, currentPage)
    }
}