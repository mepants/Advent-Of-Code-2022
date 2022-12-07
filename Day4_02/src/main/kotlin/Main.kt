import java.io.File

fun main(args: Array<String>) {

    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day4_01\\input.txt"

    infix fun IntRange.overlaps(other : IntRange) : Boolean {
        return this.contains(other.first) ||
                this.contains(other.last) ||
                other.contains(this.first) ||
                other.contains(this.last)
    }

    fun getRange(rangeStr : String) : IntRange{
        val parts = rangeStr.split("-")
        return parts[0].toInt() .. parts[1].toInt()
    }

    var numOverlaps = 0

    val file = File(FILENAME)
    file.forEachLine {
        val (part1, part2) = it.split(",")
        if (getRange(part1) overlaps getRange(part2)){
            ++numOverlaps
        }
    }

    println("Number of total overlaps is $numOverlaps")

}