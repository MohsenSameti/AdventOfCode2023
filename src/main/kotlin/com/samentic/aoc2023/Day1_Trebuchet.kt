package com.samentic.aoc2023

fun main() {
//    val input = """1abc2
//        pqr3stu8vwx
//        a1b2c3d4e5f
//        treb7uchet""".split("\n")

//    val input = """two1nine
//    eightwothree
//    abcone2threexyz
//    xtwone3four
//    4nineeightseven2
//    zoneight234
//    7pqrstsixteen""".split("\n")

    val input = Loader::class.java
        .getResource("/day1-input.txt")!!
        .readText()
        .split("\n")

    var sum1 = 0
    var sum2 = 0
    input.forEach { line ->
        sum1 += part1(line)
        sum2 += part2(line)
    }

    println("part1: $sum1")
    println("part2: $sum2")
}

private fun part2(line: String): Int {
    var number = 0
    var i = 0
    while (i < line.length) {
        val c = line[i]
        if (c.isDigit()) {
            number = c.digitToInt() * 10
            break
        }

        try {
            when (c) {
                'o' -> {
                    if (line.substring(i, i + 3) == "one") {
                        number = 1 * 10
                        break
                    }
                }

                't' -> {
                    if (line.substring(i, i + 3) == "two") {
                        number = 2 * 10
                        break
                    }
                    if (line.substring(i, i + 5) == "three") {
                        number = 3 * 10
                        break
                    }
                }

                'f' -> {
                    if (line.substring(i, i + 4) == "four") {
                        number = 4 * 10
                        break
                    }
                    if (line.substring(i, i + 4) == "five") {
                        number = 5 * 10
                        break
                    }
                }

                's' -> {
                    if (line.substring(i, i + 3) == "six") {
                        number = 6 * 10
                        break
                    }
                    if (line.substring(i, i + 5) == "seven") {
                        number = 7 * 10
                        break
                    }
                }

                'e' -> {
                    if (line.substring(i, i + 5) == "eight") {
                        number = 8 * 10
                        break
                    }
                }

                'n' -> {
                    if (line.substring(i, i + 4) == "nine") {
                        number = 9 * 10
                        break
                    }
                }
            }
        } catch (_: Throwable) {
        }

        i++
    }

    i = line.lastIndex
    while (i >= 0) {
        val c = line[i]
        if (c.isDigit()) {
            number += c.digitToInt()
            break
        }

        try {
            when (c) {
                'e' -> {
                    if (line.substring(i - 2, i) == "on") {
                        number += 1
                        break
                    }

                    if (line.substring(i - 3, i) == "nin") {
                        number += 9
                        break
                    }

                    if (line.substring(i - 4, i) == "thre") {
                        number += 3
                        break
                    }
                    if (line.substring(i - 3, i) == "fiv") {
                        number += 5
                        break
                    }

                }

                'o' -> {
                    if (line.substring(i - 2, i) == "tw") {
                        number += 2
                        break
                    }
                }

                'r' -> {
                    if (line.substring(i - 3, i) == "fou") {
                        number += 4
                        break
                    }
                }

                'n' -> {
                    if (line.substring(i - 4, i) == "seve") {
                        number += 7
                        break
                    }
                }

                'x' -> {
                    if (line.substring(i - 2, i) == "si") {
                        number += 6
                        break
                    }
                }

                't' -> {
                    if (line.substring(i - 4, i) == "eigh") {
                        number += 8
                        break
                    }
                }
            }
        } catch (_: Throwable) {
        }

        i -= 1
    }

//    println("$number -> $line")

    return number
}

private fun part1(line: String): Int {
    var number = 0
    // first
    for (i in 0 until line.lastIndex) {
        if (line[i].isDigit()) {
            number = line[i].digitToInt() * 10
            break
        }
    }
    //last
    for (i in line.lastIndex downTo 0) {
        if (line[i].isDigit()) {
            number += line[i].digitToInt()
            break
        }
    }
    return number

}