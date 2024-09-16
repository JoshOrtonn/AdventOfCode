package year2023.Trebuchet.part2

import org.example.year2023.Trebuchet.part2.TrebuchetPartTwo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TrebuchetPartTwoTest {
    private val trebuchetPartTwoTest = TrebuchetPartTwo()

    private val sampleInput: List<String> = buildList<String> {
        add("two1nine")
        add("eightwothree")
        add("abcone2threexyz")
        add("xtwone3four")
        add("4nineeightseven2")
        add("zoneight234")
        add("7pqrstsixteen")
    }.toList()


    @Test
    fun isCorrectCalibrationSampleInputValue() {
        assertEquals(trebuchetPartTwoTest.decipherDocument(sampleInput), 281)
    }

    @Test
    fun isCorrectCalibrationDocumentValue() {
        assertEquals(trebuchetPartTwoTest.decipherDocument(), 54719)
    }
}