private enum class Decoded {
  WIN,
  LOSE,
  DRAW;
  companion object {
    fun fromChar(char: Char): Decoded? =
        when (char) {
          'X' -> LOSE
          'Y' -> DRAW
          'Z' -> WIN
          else -> null
        }
  }
}

private sealed class RPS(val points: Int) {
  fun totalPoints(other: RPS): Int = points + against(other)
  abstract fun against(other: RPS): Int
  abstract fun decode(other: Decoded): Int

  object Rock : RPS(1) {
    override fun against(other: RPS): Int =
        when (other) {
          Paper -> 0
          Rock -> 3
          Scissors -> 6
        }

    override fun decode(other: Decoded): Int =
        when (other) {
          Decoded.WIN -> Paper
          Decoded.LOSE -> Scissors
          Decoded.DRAW -> Rock
        }.totalPoints(this)
  }

  object Paper : RPS(2) {
    override fun against(other: RPS): Int =
        when (other) {
          Paper -> 3
          Rock -> 6
          Scissors -> 0
        }
    override fun decode(other: Decoded): Int =
        when (other) {
          Decoded.WIN -> Scissors
          Decoded.LOSE -> Rock
          Decoded.DRAW -> Paper
        }.totalPoints(this)
  }

  object Scissors : RPS(3) {
    override fun against(other: RPS): Int =
        when (other) {
          Paper -> 6
          Rock -> 0
          Scissors -> 3
        }
    override fun decode(other: Decoded): Int =
        when (other) {
          Decoded.WIN -> Rock
          Decoded.LOSE -> Paper
          Decoded.DRAW -> Scissors
        }.totalPoints(this)
  }

  companion object {
    fun fromChar(char: Char): RPS? =
        when (char) {
          'A',
          'X' -> Rock
          'B',
          'Y' -> Paper
          'C',
          'Z' -> Scissors
          else -> null
        }
  }
}

fun main() {

  fun part1(input: List<String>): Int =
      input.fold(0) { points, line ->
        val moves = line.mapNotNull { RPS.fromChar(it) }
        check(moves.size == 2)
        points + moves[1].totalPoints(moves[0])
      }

  fun part2(input: List<String>): Int =
      input.fold(0) { points, line ->
        val moves = line.toCharArray().filter { it.isLetter() }
        check(moves.size == 2)
        val opponentMove = RPS.fromChar(moves[0]) ?: return@fold points
        val decoded = Decoded.fromChar(moves[1]) ?: return@fold points

        points + opponentMove.decode(decoded)
      }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day02_test")
  check(part1(testInput) == 15)
  check(part2(testInput) == 12)
  //
  val input = readInput("Day02")
  println(part1(input))
  println(part2(input))
}
