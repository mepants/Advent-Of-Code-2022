import java.io.File

fun main(args: Array<String>) {
    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day5_01\\input.txt"

    val NUM_STACKS = 9

    var stacks = Array<MutableList<Char>>(NUM_STACKS, { mutableListOf() })

    fun addToBottom(stackNum: Int, crate: Char){
        stacks[stackNum-1].add(crate)
    }

    fun addToTop(stackNum: Int, crate: Char){
        stacks[stackNum-1].add(0, crate)
    }

    fun popTop(stackNum: Int) : Char {
        return stacks[stackNum-1].removeAt(0)
    }

    fun addInitialLine(line: String){
        for(idx in 1 .. (NUM_STACKS*4-3) step 4){
            if (line[idx] != ' ') {
                addToBottom(idx / 4 + 1, line[idx])
            }
        }
    }

    fun parseMove(line: String) : Triple<Int, Int, Int>{
        val match = Regex("move (\\d+) from (\\d+) to (\\d+)").find(line) ?: throw Exception("No match found")
        val (number, from, to) = match.destructured
        return Triple(number.toInt(), from.toInt(), to.toInt())
    }

    fun processMove(line: String){
        val (number, from, to) = parseMove(line)
        for (i in 1 .. number){
            addToTop(to, popTop(from))
        }
    }

    val file = File(FILENAME)
    file.forEachLine {
        if (it.contains('[')) {
            addInitialLine(it)
        } else if (it.contains("move")){
            processMove(it)
        }
    }

    var topEntries = ""
    stacks.forEach {
        topEntries += it.get(0)
    }

    println("Top crates are $topEntries")
}