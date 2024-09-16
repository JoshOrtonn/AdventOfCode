package year2023.Trebuchet

import org.example.year2023.Trebuchet.Trebuchet
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TrebuchetTest {
    private val trebuchetTest = Trebuchet()
    private val sampleInput: List<String> = buildList<String> {
        add("1abc2")
        add("pqr3stu8vwx")
        add("a1b2c3d4e5f")
        add("treb7uchet")
    }.toList()

    @Test
    fun isCorrectCalibrationDocumentValue() {
        assertEquals(trebuchetTest.decipherDocument(), 55971)
    }

    @Test
    fun isCorrectCalibrationDocumentSampleInput() {
        assertEquals(trebuchetTest.decipherDocument(sampleInput), 142)
    }

}