fun redNosedReports1(): Int {
    return readFile("Day02.txt")
        .count {
            var report = it.trim()
                .split(Regex("\\s+"))
                .map(String::toInt)

            if (report[0] < report[1]) report = report.reversed()

            report.windowed(2)
                .all { window -> window[0] - window[1] in 1..3 }
        }
}

fun redNosedReports2(): Int {
    return readFile("Day02.txt")
        .count {
            val initialReport = it.trim()
                .split(Regex("\\s+"))
                .map(String::toInt)

            val allPossibleReports = mutableListOf<List<Int>>()
            for (i in initialReport.indices) {
                val report = buildList {
                    addAll(initialReport.subList(0, i))
                    addAll(initialReport.subList(i + 1, initialReport.size))
                }
                allPossibleReports.add(report)
                allPossibleReports.add(report.reversed())
            }

            allPossibleReports.any { report ->
                report.windowed(2)
                    .all { window -> window[0] - window[1] in 1..3 }
            }
        }
}

fun main() {
    println(redNosedReports1())
    println(redNosedReports2())
}