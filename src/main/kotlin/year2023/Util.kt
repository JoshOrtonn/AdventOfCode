package year2023

import java.io.File

fun readFile(): List<String> = buildList {
    File("src/main/kotlin/year2023/trebuchet/textInput.txt")
        .useLines { lines -> lines.forEach { add(it) } }
}