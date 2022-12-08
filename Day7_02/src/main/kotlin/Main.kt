import java.io.File

const val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day7_01\\input.txt"

const val DISK_SIZE = 70_000_000u
const val REQUIRED_SPACE = 30_000_000u

fun main(args: Array<String>) {

    class FileRecord (val name: String, val size: UInt)

    class Directory (val name: String, val parent: Directory?) {
        val subdirs = mutableListOf<Directory>()
        val files = mutableListOf<FileRecord>()
        fun getSize() : UInt = files.sumOf { it.size } + subdirs.sumOf{ it.getSize() }
    }

    val rootDir = Directory("/", null)
    var currentDir = rootDir
    var allDirs = mutableListOf(rootDir)

    fun processCdCommand(cmd : String){
        val dirName = cmd.replace("$ cd ", "")
        if (dirName == "/") {
            currentDir = rootDir
        } else if (dirName == "..") {
            currentDir = currentDir.parent ?: throw Exception("Navigated above /")
        } else {
            val newDir = Directory(dirName, currentDir)
            currentDir.subdirs.add(newDir)
            allDirs.add(newDir)
            currentDir = newDir
        }
    }

    fun processFileLine(line: String){
        val (sizeStr, filename) = line.split(" ")
        currentDir.files.add(FileRecord(filename, sizeStr.toUInt()))
    }

    val file = File(FILENAME)
    file.forEachLine {
        if (it.startsWith("$ cd ")){
            processCdCommand(it)
        } else if (it.matches(Regex("^[0-9]+ .*"))){
            processFileLine(it)
        }
    }

    val freeSpace = DISK_SIZE - rootDir.getSize()
    val spaceToFree = REQUIRED_SPACE - freeSpace

    val bigEnoughDirs = allDirs.filter{ it.getSize() >= spaceToFree }
    val sizeOfDirToDelete = bigEnoughDirs.minOf{ it.getSize() }

    println("Size of smallest acceptable directory is $sizeOfDirToDelete")
}