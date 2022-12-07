fun main() {

  fun part1(input: List<String>): Int {
    val windowSize = 4
    return input.sumOf { line ->
      line
          .asSequence()
          .withIndex()
          .windowed(windowSize, 1)
          .filter { it.map { it.value }.toSet().size == windowSize }
          .onEach { println(it) }
          .firstOrNull()
          ?.last()
          ?.index
          ?.plus(1)
          ?: 0
    }
  }

  fun part2(input: List<String>): Int {
    val windowSize = 14
    return input.sumOf { line ->
      line
          .asSequence()
          .withIndex()
          .windowed(windowSize, 1)
          .filter { it.map { it.value }.toSet().size == windowSize }
          .onEach { println(it) }
          .firstOrNull()
          ?.last()
          ?.index
          ?.plus(1)
          ?: 0
    }
  }
  val testInput = readInput("Day06_test")
  check(part1(testInput).also { println(it) } == 7)
  check(part2(testInput).also { println(it) } == 19)
  //
  val input = readInput("Day06")
  println(part1(input))
  println(part2(input))
}
