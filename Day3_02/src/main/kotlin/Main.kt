import java.io.File

fun main(args: Array<String>) {

    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day3_01\\input.txt"

    fun findBadge(sections : List<String>) : Char {
        val set1 = sections[0].toSet()
        val set2 = sections[1].toSet()
        val set3 = sections[2].toSet()

        val intersection = set1.intersect(set2).intersect(set3)

        if (intersection.isEmpty()){
            throw Exception("No badge found")
        }

        return intersection.first()
    }

    fun itemPriority(item: Char) : Int {
        return if (item < 'a') {
            (item - 'A') + 27
        } else{
            (item - 'a') + 1
        }
    }

    var sumOfPriorities = 0
    var elfGroup = mutableListOf<String>()

    val file = File(FILENAME)
    file.forEachLine {
        elfGroup.add(it)
        if (elfGroup.size == 3){
            sumOfPriorities += itemPriority(findBadge(elfGroup))
            elfGroup.clear()
        }
    }

    println("Sum of priorities: $sumOfPriorities")
}