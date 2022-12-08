import java.io.File

fun main(args: Array<String>) {
    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day6_01\\input.txt"

    val file = File(FILENAME)
    val signal = file.readLines()[0]

    tailrec fun charInPrevSlice(slice : CharSequence, lastChar: Char) : Boolean {
        return if (slice.contains(lastChar)) {
            true
        } else if (slice.length == 1) {
            false
        } else {
            charInPrevSlice(slice.slice(0 until slice.length - 1), slice[slice.length - 1])
        }
    }

    for (idx in 3 until signal.length){
        if ( !charInPrevSlice( signal.slice(idx-3 until idx), signal[idx]) )
        {
            println("4 different chars ${signal.slice(idx-3..idx)} finish at ${idx+1}")
            break
        }
    }


}