package year2023.trebuchet.partOne

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TrebuchetDigitsTest {
    private val trebuchetDigitsTest = TrebuchetDigits()
    private val sampleInput: List<String> = buildList<String> {
        add("1abc2")
        add("pqr3stu8vwx")
        add("a1b2c3d4e5f")
        add("treb7uchet")
    }.toList()

    @Test
    fun isCorrectCalibrationDocumentValue() {
        assertEquals(trebuchetDigitsTest.decipherDocument(), 55971)
    }

    @Test
    fun isCorrectCalibrationDocumentSampleInput() {
        assertEquals(trebuchetDigitsTest.decipherDocument(sampleInput), 142)
    }

}