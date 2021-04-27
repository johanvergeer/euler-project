fun <T> Collection<T>.countOccurrences(): Map<T, Int> =
    mutableMapOf<T, Int>().apply {
        for (e in this@countOccurrences) {
            put(e, getOrDefault(e, 0) + 1)
        }
    }
