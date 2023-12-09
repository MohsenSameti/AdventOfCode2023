package com.samentic.aoc2023

import java.util.regex.Pattern

private val idPattern = Pattern.compile("(Card)\\s+(\\d+)")

fun main() {
    val sampleLines = """
        &Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        &Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        &Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        &Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        &Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        &Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """
        .trimMargin("&")
        .split('\r', '\n')
        .filter { it.isNotBlank() }

    val actualLines = Loader::class.java
        .getResource("/day4-input.txt")!!
        .readText()
        .split('\r', '\n')
        .filter { it.isNotBlank() }


    println("part1 for sample: ${part1(sampleLines)}")
    println("part1 for input: ${part1(actualLines)}")
    println("part2 for sample: ${part2(sampleLines)}")
    println("part2 for input: ${part2(actualLines)}")
}

// 14624680
private fun part2(lines: List<String>): Int {
    val countMap = mutableMapOf<Int, Int>()
    lines.forEach { line ->
        val (id, winning, numbers) = extractData(line)
        countMap[id] = (countMap[id] ?: 0) + 1

        val count = numbers.count { num -> num in winning }
        if (count < 0) {
            return@forEach
        }
        val rewards = List(count) { it + id + 1 }
        rewards.forEach { rewardId ->
            countMap[rewardId] = (countMap[rewardId] ?: 0) + (countMap[id]!!)
        }
    }

    return countMap.values.sum()
}

// 32609
private fun part1(lines: List<String>): Int {
    var sum = 0
    lines.forEach { line ->
        val (_, winning, numbers) = extractData(line)
        val count = numbers.count { num -> num in winning }
        if (count != 0) {
            var pow = 1
            repeat(count - 1) { pow *= 2 }
            sum += pow
        }

    }
    return sum
}

private fun extractData(line: String): Triple<Int, List<Int>, List<Int>> {
    val (winning, numbers) = line.split(":")[1]
        .split("|")
        .map { numbers ->
            numbers.split("\\s".toRegex())
                .filter { it.isNotBlank() }
                .map { it.trim().toInt() }
        }

    val cardId = idPattern.matcher(line).run {
        find()
        group(2)
    }

    return Triple(cardId.toInt(), winning, numbers)
}