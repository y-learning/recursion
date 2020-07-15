import java.util.concurrent.ConcurrentHashMap

class Memoizer<T, U> private constructor() {
    private val cache = ConcurrentHashMap<T, U>()

    private fun deMemoize(f: (T) -> U): (T) -> U = { input ->
        cache.computeIfAbsent(input) { f(it) }
    }

    companion object {
        fun <T, U> memoize(f: (T) -> U): (T) -> U =
                Memoizer<T, U>().deMemoize(f)
    }
}

fun longComputation(n: Int): Int {
    Thread.sleep(1000)
    return n + 1
}

fun h(x: Int, y: Int, z: Int) =
        longComputation(x) + longComputation(y) - longComputation(z)

val f3 = { x: Int ->
    { y: Int ->
        { z: Int ->
            h(x, y, z)
        }
    }
}

val f3m = Memoizer.memoize { x: Int ->
    Memoizer.memoize { y: Int ->
        Memoizer.memoize { z: Int ->
            h(x, y, z)
        }
    }
}

data class Tuple4<T, U, V, W>(val first: T,
                              val second: U,
                              val third: V,
                              val forth: W)

val fTuple = { (a, b, c, d): Tuple4<Int, Int, Int, Int> ->
    longComputation(a) + longComputation(b)
    -longComputation(c) * longComputation(d)
}

val fTupleMem = Memoizer.memoize(fTuple)
