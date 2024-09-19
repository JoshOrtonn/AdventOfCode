package org.example.year2023.cubeConundrum

import java.io.File

/*
As you walk, the Elf shows you a small bag and some cubes which are either red, green, or blue. Each time you play this game,
he will hide a secret number of cubes of each color in the bag, and your goal is to figure out information about the number of cubes.

To get information, once a bag has been loaded with cubes, the Elf will
reach into the bag, grab a handful of random cubes, show them to you,
and then put them back in the bag. He'll do this a few times per game.

You play several games and record the information from each game (your puzzle input).
Each game is listed with its ID number (like the 11 in Game 11: ...) followed by a semicolon-separated
list of subsets of cubes that were revealed from the bag (like 3 red, 5 green, 4 blue).

For example, the record of a few games might look like this:

Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green

In game 1, three sets of cubes are revealed from the bag (and then put back again).
The first set is 3 blue cubes and 4 red cubes; the second set is 1 red cube, 2 green cubes, and 6 blue cubes; the third set is only 2 green cubes.

1. The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?

In the example above, games 1, 2, and 5 would have been possible if the bag had been loaded with that configuration.
However, game 3 would have been impossible because at one point the Elf showed you 20 red cubes at once;
similarly, game 4 would also have been impossible because the Elf showed you 15 blue cubes at once.
If you add up the IDs of the games that would have been possible, you get 8.

Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes.
What is the sum of the IDs of those games?

 */

enum class BallColour() {
    RED, GREEN, BLUE
}

class CubeConundrum {
    private fun readFile(textFileName: String = "src/main/kotlin/year2023/cubeConundrum/sampleInput.txt"): Map<Int, String> =
        buildMap<Int, String> {
            File(textFileName)
                .useLines { games ->
                    games.forEachIndexed { index: Int, game: String ->
                        val gameNumber = index + 1
                        put(gameNumber, game)
                    }
                }
        }

    private fun getBallColour(colour: String): BallColour = when (colour) {
        "blue" -> BallColour.BLUE
        "red" -> BallColour.RED
        else -> BallColour.GREEN
    }

    /*
        Determine which games would have been possible
        if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes.
        What is the sum of the IDs of those games?
     */
    fun calculatePossibleGamesGivenBagCount(textFileName: String? = null): Int {
        val gameInput = (if (textFileName != null) readFile(textFileName) else readFile())
        val proposedMaxBallCounts: Map<BallColour, Int> =
            mapOf(BallColour.BLUE to 14, BallColour.RED to 12, BallColour.GREEN to 13)

        val calculatedGamesMaxBallCount: Map<Int, Map<BallColour, Int>> = calculateBallCount(gameInput)

        var sumOfGameIndexes = 0
        // Compare maxBallCounts values for each key to the calculated games.
        calculatedGamesMaxBallCount.forEach { (game, gameResult) ->
            println("Game number $game had results: $gameResult")
            if (gameResult.all { entry ->
                    proposedMaxBallCounts.getOrDefault(entry.key, 0) >= gameResult.getOrDefault(entry.key, 0)
                }) {
                sumOfGameIndexes += game
            }
        }

        return sumOfGameIndexes

    }

    /*
As you continue your walk, the Elf poses a second question:
in each game you played, what is the fewest number of cubes of each color that could have been in the bag
to make the game possible?
Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green (min 4 red, 6 blue, 2 green) = 4*6*2 = 48 +
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue (min 1 red, 4 blue, 3 green) = 1*4*3 = 12 +
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
*/

    fun calculateMinBallCountGames(textFileName: String? = null): Int {
        val gameInput = (if (textFileName != null) readFile(textFileName) else readFile())
        val calculatedBallCount: Map<Int, Map<BallColour, Int>> = calculateBallCount(gameInput)

        var runningPowerTotal = 0
        calculatedBallCount.forEach { (_, gameResult) ->
            var inGameTotal = 1
            gameResult.values.forEach { entry ->
                inGameTotal *= entry
            }
            runningPowerTotal += inGameTotal
        }

        return runningPowerTotal

    }


    private fun calculateBallCount(gameInput: Map<Int, String>): Map<Int, Map<BallColour, Int>> =
        buildMap<Int, Map<BallColour, Int>> {
            gameInput.forEach { (gameIndex: Int, game: String) ->
                val sanitizedInput = sanitizeNewGameInput(game, gameIndex)
                val sets = splitIntoSets(sanitizedInput)
                val ballColourToOccurrenceMap = mutableMapOf<BallColour, Int>()

                sets.forEach { set: String ->
                    val balls = set.split(", ")
                    balls.forEach { ball ->
                        val ballOccurrence = ball.filter { it.isDigit() }.toInt()
                        val colourOfBall: BallColour = getBallColour(ball.removePrefix("$ballOccurrence "))

                        val previousBallOccurrence = ballColourToOccurrenceMap.getOrDefault(colourOfBall, ballOccurrence)
                        ballColourToOccurrenceMap[colourOfBall] = maxOf(previousBallOccurrence, ballOccurrence)
                    }
                    put(gameIndex, ballColourToOccurrenceMap)
                }
            }
        }

    private fun sanitizeNewGameInput(game: String, gameNumber: Int) = game.removePrefix("Game $gameNumber: ")

    private fun splitIntoSets(sanitizedInput: String) = sanitizedInput.split("; ")
}