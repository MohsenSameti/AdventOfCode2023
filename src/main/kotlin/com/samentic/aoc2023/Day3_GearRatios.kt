package com.samentic.aoc2023

import java.math.BigInteger

fun main() {
//    val lines = """467..114..
//        |...*......
//        |..35..633.
//        |......#...
//        |617*......
//        |.....+.58.
//        |..592.....
//        |......755.
//        |...${'$'}.*....
//        |.664.598..""".trimMargin("|").split("\n")

    val lines = Loader::class.java
        .getResource("/day3-input.txt")!!
        .readText()
        .split(System.lineSeparator())


    println(part1(lines))
    println(part2(lines))
}

// 81997870
private fun part2(lines: List<String>): BigInteger {
    val stars = mutableMapOf<Int, List<Int>>()
    val numbers = mutableMapOf<Int, List<Pair<Int, String>>>()

    lines.forEachIndexed { index, line ->
        val n = mutableListOf<Pair<Int, String>>()
        val s = mutableListOf<Int>()

        var i = 0
        while (i < line.length) {
            if (line[i] == '.') {
                i++
                continue
            }

            if (line[i].isDigit()) {
                val start = i
                while (++i < line.length && line[i].isDigit()) continue
                n.add(start to line.substring(start, i))
                continue
            }

            if (line[i] == '*')
                s.add(i)
            i++
        }

        if (n.isNotEmpty()) numbers[index] = n
        if (s.isNotEmpty()) stars[index] = s
    }

    val possibleGears = mutableMapOf<String, MutableList<Int>>()
    numbers.entries.forEach { (lineIndex, nums) ->
        nums.forEach num@{ (index, num) ->
            val range = (index - 1..index + num.length)
            // previous line
            stars[lineIndex - 1]?.let { syms ->
                syms.firstOrNull { idx -> idx in range }?.let { starIndex ->
                    val pos = "${lineIndex - 1}:$starIndex"
                    possibleGears[pos] = (possibleGears[pos] ?: mutableListOf()).apply {
                        add(num.toInt())
                    }
                }
            }
            // current line
            stars[lineIndex]?.let { syms ->
                syms.firstOrNull { idx -> idx in range }?.let { starIndex ->
                    val pos = "${lineIndex}:$starIndex"
                    possibleGears[pos] = (possibleGears[pos] ?: mutableListOf()).apply {
                        add(num.toInt())
                    }
                }
            }
            // next line
            stars[lineIndex + 1]?.let { syms ->
                syms.firstOrNull { idx -> idx in range }?.let { starIndex ->
                    val pos = "${lineIndex + 1}:$starIndex"
                    possibleGears[pos] = (possibleGears[pos] ?: mutableListOf()).apply {
                        add(num.toInt())
                    }
                }
            }
//            println("NOT FOUND: $num")
        }
    }

    return possibleGears.values.filter { it.size == 2 }
        .map { it.map { it.toBigInteger() }.fold(BigInteger.ONE) { acc, a -> acc * a } }
        .fold(BigInteger.ZERO) { acc, a -> acc + a }
}

// 550934
private fun part1(lines: List<String>): Int {
    val symbols = mutableMapOf<Int, List<Int>>()
    val numbers = mutableMapOf<Int, List<Pair<Int, String>>>()

    lines.forEachIndexed { index, line ->
        val s = mutableListOf<Int>()
        val n = mutableListOf<Pair<Int, String>>()

        var i = 0
        while (i < line.length) {
            if (line[i] == '.') {
                i++
                continue
            }

            if (line[i].isDigit()) {
                val start = i
                while (++i < line.length && line[i].isDigit()) continue
                n.add(start to line.substring(start, i))
                continue
            }

            s.add(i)
            i++
        }

        if (n.isNotEmpty()) numbers[index] = n
        if (s.isNotEmpty()) symbols[index] = s
    }

    var sum = 0

    numbers.entries.forEach { (lineIndex, nums) ->
        nums.forEach num@{ (index, num) ->
            val range = (index - 1..index + num.length)
            // previous line
            symbols[lineIndex - 1]?.let { syms ->
                syms.firstOrNull { idx -> idx in range }?.let {
                    sum += num.toInt()
//                    println("$num (${lines[lineIndex - 1][it]})(${lineIndex-1}, $it)")
                    return@num
                }
            }
            // current line
            symbols[lineIndex]?.let { syms ->
                syms.firstOrNull { idx -> idx in range }?.let {
                    sum += num.toInt()
//                    println("$num (${lines[lineIndex][it]})(${lineIndex}, $it)")
                    return@num
                }
            }
            // next line
            symbols[lineIndex + 1]?.let { syms ->
                syms.firstOrNull { idx -> idx in range }?.let {
                    sum += num.toInt()
//                    println("$num (${lines[lineIndex+1][it]})(${lineIndex+1}, $it)")
                    return@num
                }
            }
//            println("NOT FOUND: $num")
        }
    }
    return sum
}