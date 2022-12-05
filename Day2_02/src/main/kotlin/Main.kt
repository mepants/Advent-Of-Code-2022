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

        override fun losesAgainst() = PAPER
        override fun winsAgainst() = SCISSORS
    },
    PAPER(2){
        override fun scoreAgainst(opponentMove: Move): Int {
            return when (opponentMove) {
                ROCK -> 6
                PAPER -> 3
                SCISSORS -> 0
            }
        }

        override fun losesAgainst() = SCISSORS
        override fun winsAgainst() = ROCK
    },
    SCISSORS(3){
        override fun scoreAgainst(opponentMove: Move): Int {
            return when (opponentMove) {
                ROCK -> 0
                PAPER -> 6
                SCISSORS -> 3
            }
        }
        override fun losesAgainst() = ROCK
        override fun winsAgainst() = PAPER
    };

    abstract fun scoreAgainst(opponentMove: Move) : Int
    abstract fun losesAgainst() : Move
    abstract fun winsAgainst() : Move

    companion object {
        fun fromLetter(letter: Char): Move {
            return when (letter) {
                'A' -> Move.ROCK
                'B' -> Move.PAPER
                'C' -> Move.SCISSORS
                else -> throw Exception("Unknown move $letter")
            }
        }
    }
}

fun main(args: Array<String>) {

    val FILENAME = "c:\\Users\\micro\\code\\Advent-Of-Code-2022\\Day2_02\\input.txt"

    fun scoreRound(oppLetter: Char, myLetter: Char) : Int {
        val opponentMove = Move.fromLetter(oppLetter)
        val myMove = when (myLetter) {
            'X' -> opponentMove.winsAgainst()
            'Z' -> opponentMove.losesAgainst()
            else -> opponentMove
        }
        return myMove.value + myMove.scoreAgainst(opponentMove)
    }

    var score = 0

    val file = File(FILENAME)
    file.forEachLine {
        score += scoreRound(it[0], it[2])
    }

    println("My total score is $score")
}