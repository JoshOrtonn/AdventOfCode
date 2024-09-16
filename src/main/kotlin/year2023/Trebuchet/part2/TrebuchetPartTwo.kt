package org.example.year2023.Trebuchet.part2

import java.io.File


/*
--- Part Two ---
Your calculation isn't quite right. It looks like some of the digits are actually spelled out
with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

Equipped with this new information, you now need to find the real first and last digit on each line.

For example:
two1nine (29)
eightwothree (83)
abcone2threexyz (13)
xtwone3four (24)
4nineeightseven2 (42)
zoneight234 (14)
7pqrstsixteen (76)
In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.

 for each char check if it's
    if (isDigit()) {
     add to List()

     } else if ("o" "t" "f" "s" "e" "n")
     iterate max five times, if at any point matches word in the set, add to List()
     } else {do nothing}




 */

class TrebuchetPartTwo {
    private fun readFile(): List<String> = buildList<String> {
        File("/Users/lottieware/IdeaProjects/AdventOfCode/src/main/kotlin/year2023/Trebuchet/textInput.txt")
            .useLines { lines -> lines.forEach { add(it) } }
    }

    private val digitSet = buildMap<String, Char> {
        put("one", '1')
        put("two", '2')
        put("three", '3')
        put("four", '4')
        put("five", '5')
        put("six", '6')
        put("seven",'7')
        put("eight",'8')
        put("nine", '9')
    }

    fun decipherDocument(sampleInput: List<String>? = null): Int {
        val input = sampleInput ?: readFile()
        var runningTotal: Int = 0


        input.forEach { word ->
            val listOfEligibleDigits = buildList<Char> {
                val charArray = word.toCharArray()
                charArray.forEachIndexed { charIndex, char ->
                    if (char.isDigit()) {
                        add(char)
                    } else if (isStartOfDigitWord(char)) {
                        // Start with letter
                        val eligibleNumber = getDigitWord(charArray, charIndex)
                        eligibleNumber?.let {
                            add(it)
                        }
                    }
                }
            }


            val firstLastDigitsInWord = "${listOfEligibleDigits.first()}${listOfEligibleDigits.last()}".toInt()
            runningTotal += firstLastDigitsInWord
        }

        return runningTotal
    }

    private fun isStartOfDigitWord(char: Char) = digitSet.keys.any { char == it.toCharArray()[0] }

    private fun getDigitWord(
        charArray: CharArray,
        charIndex: Int
    ): Char?  {
        var wordSubset: String = ""
        // Iterator appending each letter
        // Checking afterwards to see if result is within the DigitSet
        repeat(5) { iterator ->
            val newIndex = iterator + charIndex
            if (charArray.isWithinBounds(newIndex)) {
                wordSubset += charArray[newIndex]
                if (wordSubset in digitSet.keys) {
                    return digitSet.getValue(wordSubset)
                }
            }
        }

        return null
    }

    private fun CharArray.isWithinBounds(index: Int): Boolean {
        return index <= this.lastIndex
    }
}