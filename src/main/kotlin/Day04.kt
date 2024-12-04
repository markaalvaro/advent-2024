fun ceresSearch1(): Int {
    val chars = readFile("Day04.txt")
        .map { it.toList() }

    var count = 0
    for (r in chars.indices) {
        for (c in chars[0].indices) {
            if (chars[r][c] == 'X') {
                (-1..1).forEach { rDiff ->
                    (-1..1).forEach { cDiff ->
                        if (rDiff != 0 || cDiff != 0)
                            if (chars.isXmas(r, c, rDiff, cDiff)) count++
                    }
                }
            }
        }
    }

    return count
}

fun List<List<Char>>.isXmas(r: Int, c: Int, rDiff: Int = 0, cDiff: Int = 0): Boolean {
    return try {
        val substring =
            this[r + rDiff * 3][c + cDiff * 3].toString() + this[r + rDiff * 2][c + cDiff * 2] + this[r + rDiff * 1][c + cDiff * 1] + this[r][c]
        substring == "XMAS" || substring == "SAMX"
    } catch (e: IndexOutOfBoundsException) {
        false
    }
}

fun ceresSearch2(): Int {
    val chars = readFile("Day04.txt")
        .map { it.toList() }

    var count = 0
    for (r in chars.indices) {
        for (c in chars[0].indices) {
            if (chars[r][c] == 'A') {
                if (chars.isXShapedMas(r, c)) count++
            }
        }
    }

    return count
}

val mas = setOf("MAS", "SAM")
fun List<List<Char>>.isXShapedMas(r: Int, c: Int): Boolean {
    return try {
        this[r-1][c-1].toString() + this[r][c] + this[r+1][c+1] in mas
                && this[r+1][c-1].toString() + this[r][c] + this[r-1][c+1] in mas
    } catch (e: IndexOutOfBoundsException) {
        false
    }
}

fun main() {
    println(ceresSearch1())
    println(ceresSearch2())
}