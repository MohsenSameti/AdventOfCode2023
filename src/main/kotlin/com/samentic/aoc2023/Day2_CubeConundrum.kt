package com.samentic.aoc2023

import java.util.regex.Pattern

private val idPattern = Pattern.compile("(Game) (\\d+)")
private val bluePattern = Pattern.compile("(\\d+) (blue)")
private val redPattern = Pattern.compile("(\\d+) (red)")
private val greenPattern = Pattern.compile("(\\d+) (green)")

fun main() {

//    val lines = """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
//        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
//        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
//        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
//        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""".trimIndent().split("\n")

    val lines = Loader::class.java
        .getResource("/day2-input.txt")!!
        .readText()
        .split("\n")

    println(part1(lines))
    println(part2(lines))
}

private fun part2(lines: List<String>): Int {
    var sum = 0

    lines.forEach { line ->

        var maxBlue = 0
        val blueMatcher = bluePattern.matcher(line)
        while (blueMatcher.find()) {
            maxBlue = maxOf(maxBlue, blueMatcher.group(1).toInt())
        }

        var maxGreen = 0
        val greenMatcher = greenPattern.matcher(line)
        while (greenMatcher.find()) {
            maxGreen = maxOf(maxGreen, greenMatcher.group(1).toInt())
        }

        var maxRed = 0
        val redMatcher = redPattern.matcher(line)
        while (redMatcher.find()) {
            maxRed = maxOf(maxRed, redMatcher.group(1).toInt())
        }

        sum += maxBlue * maxRed * maxGreen
    }

    return sum
}

private fun part1(lines: List<String>): Int {
    val MAX_BLUE = 14
    val MAX_GREEN = 13
    val MAX_RED = 12

    var sum = 0

    lines.forEach { line ->
        val idMatcher = idPattern.matcher(line)
        if (!idMatcher.find()) {
            return@forEach
        }
        val id = idMatcher.group(2).toInt()

//        println(line)

        val blueMatcher = bluePattern.matcher(line)
        while (blueMatcher.find()) {
            if (blueMatcher.group(1).toInt() > MAX_BLUE) {
//                println("found invalid blue: ${blueMatcher.group(1)}")
                return@forEach
            }
        }

        val greenMatcher = greenPattern.matcher(line)
        while (greenMatcher.find()) {
            if (greenMatcher.group(1).toInt() > MAX_GREEN) {
//                println("found invalid green: ${greenMatcher.group(1)}")
                return@forEach
            }
        }
        val redMatcher = redPattern.matcher(line)
        while (redMatcher.find()) {
            if (redMatcher.group(1).toInt() > MAX_RED) {
//                println("found invalid red: ${redMatcher.group(1)}")
                return@forEach
            }
        }

//        println("YAAAAAAY: id=$id")
        sum += id
    }

    return sum
}
