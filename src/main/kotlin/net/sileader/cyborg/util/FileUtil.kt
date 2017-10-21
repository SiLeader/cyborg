package net.sileader.cyborg.util

import java.io.BufferedReader
import java.io.FileReader

fun read(file: String): String {
    val br=BufferedReader(FileReader(file))

    return br.readLines().joinToString("\n")
}