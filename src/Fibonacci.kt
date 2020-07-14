import java.math.BigInteger

fun fibonacci(n: Int): BigInteger {
    tailrec fun fibonacci(n1: BigInteger, n: Int, fn: BigInteger): BigInteger =
        if (n == 0) fn
        else fibonacci(fn, n - 1, fn + n1)

    return fibonacci(BigInteger.ONE, n, BigInteger.ZERO)
}