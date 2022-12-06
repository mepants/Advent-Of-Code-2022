import java.io.File

fun main(args: Array<String>) {

    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day3_01\\input.txt"

    fun splitBag(allItems: String) : Pair<String, String> {
        val c1 = allItems.substring(0 until allItems.length/2)
        val c2 = allItems.substring(allItems.length/2)
        return Pair(c1, c2)
    }

    fun findMatch(sections : Pair<String, String>) : Char {
        val set1 = sections.first.toSet()
        val set2 = sections.second.toSet()

        val intersection = set1.intersect(set2)

        if (intersection.isEmpty()){
            throw Exception("No matching items found")
        }

        return intersection.first()
    }

    fun itemPriority(item: Char) : Int {
        if (item < 'a'){
            return ((item - 'A') + 27)
        }
        else{
            return ((item) - 'a' + 1)
        }
    }

    var sumOfPriorities = 0

    val file = File(FILENAME)
    file.forEachLine {
        sumOfPriorities += itemPriority(findMatch(splitBag(it)))
    }

    println("Sum of priorities: $sumOfPriorities")
}