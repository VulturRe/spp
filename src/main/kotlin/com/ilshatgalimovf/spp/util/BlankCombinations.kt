package com.ilshatgalimovf.spp.util

import com.ilshatgalimovf.spp.domain.Blank

internal class BlankCombinations(val blankList: List<Blank>, val maxLength: Int) {

    private val combinations: MutableList<MutableList<MutableList<Blank>>> = mutableListOf()
    private val comb: MutableList<MutableList<Blank>> = mutableListOf()
    private var indicesCombination = IntArray(0)
    private var indicesCombinationList: MutableList<IntArray> = mutableListOf()
    private var m: Int = 0
    private var n: Int = 0

    private fun generate(): List<List<List<Blank>>> {
        if (sum(blankList) < maxLength) {
            return listOf(listOf(blankList))
        }

        for (i in blankList.size - 1 downTo 1) {

        }

        return listOf(listOf(listOf()))
    }

    private fun combine(m: Int): List<List<Blank>> {
        this.m = m
        n = blankList.size
        combineIndices(0)
        for (i in 0 until indicesCombinationList.size) {

        }
        return mutableListOf()
    }

    private fun combineIndices(k: Int) {
        if (k >= m) {
            indicesCombinationList.add(indicesCombination)
        }
        else {
            for (j in 0 until n)
                if (k == 0 || j > indicesCombination[k - 1]) {
                    indicesCombination[k] = j
                    combineIndices(k + 1)
                }
        }
    }

    private fun sum(blankList: List<Blank>): Int {
        var sum = 0
        for (item in blankList) { sum += item.length }
        return sum
    }
}