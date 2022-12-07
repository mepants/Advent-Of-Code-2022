import java.io.File

fun main(args: Array<String>) {

    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day4_01\\input.txt"

    infix fun IntRange.inside(other : IntRange) : Boolean = (this.first >= other.first && this.last <= other.last)

    fun getRange(rangeStr : String) : IntRange{
        val parts = rangeStr.split("-")
        return parts[0].toInt() .. parts[1].toInt()
    }

    var numSubsets = 0

    val file = File(FILENAME)
    file.forEachLine {
        val (part1, part2) = it.split(",")
        if (getRange(part1) inside getRange(part2) || getRange(part2) inside getRange(part1)){
            ++numSubsets
        }
    }

    println("Number of total overlaps is $numSubsets")

}