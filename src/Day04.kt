fun main() {

  fun part1(input: List<String>): Int =
      input
          .asSequence()
          .flatMap { line -> line.split('-', ',') }
          .chunked(2)
          .map { (first, second) -> first.toInt()..second.toInt() }
          .chunked(2)
          .map { (first, second) ->
            first.intersect(second).let { it.size == first.count() || it.size == second.count() }
          }
          .count { it }

  fun part2(input: List<String>): Int =
      input
          .asSequence()
          .flatMap { line -> line.split('-', ',') }
          .chunked(2)
          .map { (first, second) -> first.toInt()..second.toInt() }
          .chunked(2)
          .map { (first, second) -> first.intersect(second).isNotEmpty() }
          .count { it }

  val testInput = readInput("Day04_test")
  check(part1(testInput) == 2)
  check(part2(testInput) == 4)
  //
  val input = readInput("Day04")
  println(part1(input))
  println(part2(input))
}
