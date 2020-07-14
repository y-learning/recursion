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