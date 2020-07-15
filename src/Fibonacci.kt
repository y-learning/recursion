import java.math.BigInteger

fun fibonacci(n: Int): BigInteger {
    tailrec fun fibonacci(n1: BigInteger, n: Int, fn: BigInteger): BigInteger =
            if (n == 0) fn
            else fibonacci(fn, n - 1, fn + n1)

    return fibonacci(BigInteger.ONE, n, BigInteger.ZERO)
}

fun fiboMemo(n: Int): String {
    tailrec fun fibonacci(n1: BigInteger,
                          n: Int,
                          fn: List<BigInteger>): List<BigInteger> {
        return if (n == 0) fn
        else {
            val last = fn.last()
            fibonacci(last, n - 1, fn + listOf(last + n1))
        }
    }

    return makeStringFoldLeft(
            fibonacci(BigInteger.ONE, n, listOf(BigInteger.ZERO)),
            ", ")
}

typealias FiboPair = Pair<BigInteger, BigInteger>

val f = { (a, b): FiboPair -> FiboPair(b, a + b) }

fun <T> iterate(seed: T, f: (T) -> T, n: Int): List<T> {
    tailrec fun unfoldCoRecurIter(seed: T, acc: List<T>): List<T> =
            if (acc.size == n) acc
            else unfoldCoRecurIter(f(seed), acc + seed)

    return unfoldCoRecurIter(seed, listOf())
}

fun <T, U> map(f: (T) -> U, list: List<T>): List<U> =
        list.fold(listOf(), { acc, item -> acc + f(item) })

fun fiboCoRecur(n: Int): String {
    val f0f1 = FiboPair(BigInteger.ONE, BigInteger.ONE)
    val pairs = iterate(f0f1, f, n)
    val pairToFirst = map({ (a): FiboPair -> a }, pairs)

    return makeStringFoldLeft(pairToFirst, ", ")
}