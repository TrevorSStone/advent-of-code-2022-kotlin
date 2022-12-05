fun main() {

  fun createStacks(input: List<String>) =
      input
          .filter { it.contains('[') }
          .fold(mutableListOf<ArrayDeque<Char>>()) { stacks, line ->
            val blocks = line.windowed(3, 4)
            if (stacks.isEmpty()) {
              repeat(blocks.size) { stacks.add(ArrayDeque()) }
            }
            blocks
                .withIndex()
                .filter { it.value.isNotBlank() }
                .map { (index, value) -> IndexedValue(index, value[1]) }
                .forEach { (index, value) -> stacks[index].addFirst(value) }

            stacks
          }
          .toList()

  fun part1(input: List<String>): String {
    val stacks = createStacks(input)

    input
        .asSequence()
        .filter { it.firstOrNull() == 'm' }
        .map { line -> line.split(' ').mapNotNull { it.toIntOrNull() } }
        .onEach { (count, from, to) ->
          repeat(count) { stacks[to - 1].addLast(stacks[from - 1].removeLast()) }
        }
        .toList()
    return stacks.map { it.last() }.joinToString(separator = "")
  }

  fun part2(input: List<String>): String {
    val stacks = createStacks(input)

    input
        .asSequence()
        .filter { it.firstOrNull() == 'm' }
        .map { line -> line.split(' ').mapNotNull { it.toIntOrNull() } }
        .onEach { (count, from, to) ->
          val crates = buildList { repeat(count) { add(stacks[from - 1].removeLast()) } }
          stacks[to - 1].addAll(crates.asReversed())
        }
        .toList()
    return stacks.map { it.last() }.joinToString(separator = "")
  }

  val testInput = readInput("Day05_test")
  check(part1(testInput).also { println(it) } == "CMZ")
  check(part2(testInput).also { println(it) } == "MCD")
  //
  val input = readInput("Day05")
  println(part1(input))
  println(part2(input))
}
