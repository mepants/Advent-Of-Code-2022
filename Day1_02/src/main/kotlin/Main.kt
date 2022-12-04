import java.io.File

fun main(args: Array<String>) {

    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day1_01\\elf_food.txt"

    var maxFood = 0
    var maxIndex = 0

    var currFood = 0
    var currIndex = 0

    val elves = mutableListOf<Int>()

    fun processLine(line: String){
        if (line.trim() == ""){
            if (currFood > maxFood){
                maxFood = currFood
                maxIndex = currIndex
            }
            elves.add(currFood)
            currFood = 0
            ++currIndex
        }
        else{
            val foodOnLine = try {
                line.toInt()
            } catch(e: Exception) {
                println("Failed to parse $line to int")
                0
            }

            currFood += foodOnLine
        }
    }

    val file = File(FILENAME)
    file.forEachLine {
        processLine(it)
    }

    val sorted = elves.sortedDescending()
    var top3amount = 0
    for (index in 0 .. 2){
        top3amount += sorted[index]
    }

    println("Sum of top 3 food amounts is $top3amount")

}