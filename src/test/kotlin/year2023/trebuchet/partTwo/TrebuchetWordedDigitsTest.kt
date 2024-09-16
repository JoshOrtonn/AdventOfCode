package year2023.trebuchet.partTwo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TrebuchetWordedDigitsTest {
    private val trebuchetWordedDigitsTest = TrebuchetWordedDigits()

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
        assertEquals(trebuchetWordedDigitsTest.decipherDocument(sampleInput), 281)
    }

    @Test
    fun isCorrectCalibrationDocumentValue() {
        assertEquals(trebuchetWordedDigitsTest.decipherDocument(), 54719)
    }
}