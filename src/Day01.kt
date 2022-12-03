fun main() {
  fun part1(input: List<String>): Int {
    var maxCalories = 0
    (input + "end").fold(0) { currentElfCalories, calLine ->
      calLine.toIntOrNull()?.let { currentElfCalories + it }
          ?: run {
            if (currentElfCalories > maxCalories) {
              maxCalories = currentElfCalories
            }
            0
          }
    }

    return maxCalories
  }

  fun part2(input: List<String>): Int {
    var topCalories = 0
    var secondCalories = 0
    var thirdCalories = 0
    (input + "end").fold(0) { currentElfCalories, calLine ->
      calLine.toIntOrNull()?.let { currentElfCalories + it }
          ?: run {
            when {
              currentElfCalories >= topCalories -> {
                thirdCalories = secondCalories
                secondCalories = topCalories
                topCalories = currentElfCalories
              }
              currentElfCalories >= secondCalories -> {
                thirdCalories = secondCalories
                secondCalories = currentElfCalories
              }
              currentElfCalories >= thirdCalories -> {
                thirdCalories = currentElfCalories
              }
            }

            0
          }
    }
    return topCalories + secondCalories + thirdCalories
  }

  // test if implementation meets criteria from the description, like:
  val testInput = readInput("Day01_test")
  check(part1(testInput) == 24000)
  check(part2(testInput) == 45000)

  val input = readInput("Day01")
  println(part1(input))
  println(part2(input))
}
