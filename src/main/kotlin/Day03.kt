val mulRegex = Regex("""mul\((\d+),(\d+)\)""")
val doRegex = Regex("""do\(\)""")
val dontRegex = Regex("""don't\(\)""")

fun mullItOver1(): Long {
    return readFile("Day03.txt")
        .flatMap {
            mulRegex.findAll(it)
                .map { match -> match.groupValues[1].toInt() * match.groupValues[2].toInt() }
        }
        .sumOf { it.toLong() }
}

fun mullItOver2(): Long {
    var sum = 0L
    var shouldMultiply = true

    readFile("Day03.txt")
        .forEach {
            var index = 0
            while (index in it.indices) {
                val mulMatch = mulRegex.find(it, index)

                val nextMulIndex = startIndex(mulMatch)
                val nextDoIndex = startIndex(doRegex.find(it, index))
                val nextDontIndex = startIndex(dontRegex.find(it, index))

                if (nextMulIndex < nextDoIndex && nextMulIndex < nextDontIndex && shouldMultiply) {
                    sum += mulMatch!!.groupValues[1].toInt() * mulMatch.groupValues[2].toInt()
                    index = nextMulIndex + 1
                }
                else if (nextDoIndex < nextDontIndex) {
                    shouldMultiply = true
                    index = nextDoIndex + 1
                }
                else if (nextDontIndex != nextDoIndex) {
                    shouldMultiply = false
                    index = nextDontIndex + 1
                }
                else break
            }
        }

    return sum
}

private fun startIndex(matchGroup: MatchResult?): Int {
    return matchGroup?.range?.start ?: Int.MAX_VALUE
}

fun main() {
    println(mullItOver1())
    println(mullItOver2())
}