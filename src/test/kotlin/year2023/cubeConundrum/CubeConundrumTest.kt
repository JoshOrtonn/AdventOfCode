package year2023.cubeConundrum

import org.example.year2023.cubeConundrum.CubeConundrum
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CubeConundrumTest {
    private val cubeConundrum = CubeConundrum()


    @Test
    fun shouldCalcPossibleGames() {
        /*
        The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?

         */
        assertEquals(
            cubeConundrum.calculatePossibleGamesGivenBagCount("src/test/kotlin/year2023/cubeConundrum/testInput.txt"), 8
        )
    }

    @Test
    fun shouldCalcPossibleGamesUsingSampleInput() {
        /*
        The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?

         */
        assertEquals(cubeConundrum.calculatePossibleGamesGivenBagCount(), 2348)
    }

    /*
    As you continue your walk, the Elf poses a second question: in each game you played,
    what is the fewest number of cubes of each color that could have been in the bag to make the game possible?
     */
    @Test
    fun shouldCalcFewestNumberOfCubesRequired() {
        assertEquals(cubeConundrum.calculateMinBallCountGames("src/test/kotlin/year2023/cubeConundrum/testInput.txt"), 2286)
    }

    @Test
    fun shouldCalcFewestNumberOfCubesRequiredUsingSampleInput() {
        assertEquals(cubeConundrum.calculateMinBallCountGames(), 76008)
    }
}