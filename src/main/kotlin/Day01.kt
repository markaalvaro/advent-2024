import kotlin.math.abs

fun part1(): Int {
    val map = getMap()
    return map.keys.sorted().zip(map.values.sorted())
        .sumOf { abs(it.first - it.second) }
}

fun part2(): Int {
    val map = getMap()
    val counts = map.values.groupingBy { it }.eachCount()
    return map.keys.sumOf { (counts[it] ?: 0) * it }
}

fun main() {
    println(part1())
    println(part2())
}

private fun getMap(): Map<Int, Int> {
    return readFile("Day01.txt") {
        it.trim()
            .split(Regex("\\s+"))
            .map(String::toInt)
    }
        .associate { Pair(it[0], it[1]) }
}