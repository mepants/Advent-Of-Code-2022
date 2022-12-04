import java.io.File

enum class Move(val value: Int)
{
    ROCK(1) {
        override fun scoreAgainst(opponentMove: Move): Int {
            return when (opponentMove) {
                ROCK -> 3
                PAPER -> 0
                SCISSORS -> 6
            }
        }
    },
    PAPER(2){
        override fun scoreAgainst(opponentMove: Move): Int {
            return when (opponentMove) {
                ROCK -> 6
                PAPER -> 3
                SCISSORS -> 0
            }
        }
    },
    SCISSORS(3){
        override fun scoreAgainst(opponentMove: Move): Int {
            return when (opponentMove) {
                ROCK -> 0
                PAPER -> 6
                SCISSORS -> 3
            }
        }
    };

    abstract fun scoreAgainst(opponentMove: Move) : Int

    companion object {
        fun fromLetter(letter: Char): Move {
            return when (letter) {
                'A' -> Move.ROCK
                'X' -> Move.ROCK
                'B' -> Move.PAPER
                'Y' -> Move.PAPER
                'C' -> Move.SCISSORS
                'Z' -> Move.SCISSORS
                else -> throw Exception("Unknown move $letter")
            }
        }
    }
}

fun main(args: Array<String>) {

    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day2_01\\input.txt"

    fun scoreRound(oppLetter: Char, myLetter: Char) : Int {
        val myMove = Move.fromLetter(myLetter)
        return myMove.value + myMove.scoreAgainst(Move.fromLetter(oppLetter))
    }

    var score = 0

    val file = File(FILENAME)
    file.forEachLine {
        score += scoreRound(it[0], it[2])
    }

    println("My total score is $score")
}