fun Char.points(): Int =
    when {
      isLowerCase() -> code - 'a'.code + 1
      isUpperCase() -> code - 'A'.code + 27
      else -> 0
    }

fun main() {
  check('a'.points() == 1)
  check('z'.points() == 26)
  check('A'.points() == 27)
  check('Z'.points() == 52)

  fun part1(input: List<String>): Int =
      input.sumOf { line ->
        line
            .chunkedSequence(line.length / 2)
            .map { it.toSet() }
            .reduce { intersection, half -> intersection.intersect(half) }
            .sumOf { it.points() }
      }

  fun part2(input: List<String>): Int =
      input.chunked(3).sumOf { lines ->
        lines
            .asSequence()
            .map { it.toSet() }
            .reduce { intersection, line -> intersection.intersect(line) }
            .sumOf { it.points() }
      }

  val testInput = readInput("Day03_test")
  check(part1(testInput) == 157)
  check(part2(testInput) == 70)

  val input = readInput("Day03")
  println(part1(input))
  println(part2(input))
}
