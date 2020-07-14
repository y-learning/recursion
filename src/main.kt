import java.math.BigInteger

fun append(s: String, c: Char): String = "$s $c"

fun preAppend(c: Char, s: String): String = "$c $s"

fun <T> rest(collection: List<T>): List<T> = collection.drop(1)

fun toStringCoRecursion(list: List<Char>): String {
    tailrec fun toString(list: List<Char>, s: String): String {
        if (list.isEmpty()) return s

        return toString(rest(list), append(s, list.first()))
    }

    return toString(list, "")
}

fun toStringRecursion(list: List<Char>): String =
    if (list.isEmpty()) ""
    else preAppend(list[0], toStringRecursion(list.subList(1, list.size)))

fun sum(n: Int): Int = if (n == 0) 0 else n + sum(n - 1)

fun sumCo(n: Int): Int {
    tailrec fun sumCo(n: Int, s: Int): Int =
        if (n == 0) s else sumCo(n - 1, s + n)

    return sumCo(n, 0)
}

fun inc(n: Int) = n + 1

fun dec(n: Int) = n - 1

tailrec fun add(a: Int, b: Int): Int {
    if (a == 0) return b

    return add(dec(a), inc(b))
}

val factorial: (Int) -> Int by lazy {
    { n: Int ->
        if (n == 1) n
        else n * factorial(n - 1)
    }
}

object Factorial {
    val factorial: (Int) -> Int by lazy {
        { n: Int ->
            if (n == 1) n
            else n * factorial(n - 1)
        }
    }
}

fun fibonacci(n: Int): BigInteger {
    tailrec fun fibonacci(n1: BigInteger, n: Int, fn: BigInteger): BigInteger {
        if (n == 0) return fn

        return fibonacci(fn, n - 1, fn + n1)
    }

    return fibonacci(BigInteger.ONE, n, BigInteger.ZERO)
}

fun <T> makeString(list: List<T>, delim: String): String =
    when {
        list.isEmpty() -> ""
        list.drop(1).isEmpty() -> "${list.first()} ${makeString(list.drop(1), delim)}"
        else -> "${list.first()} $delim ${makeString(list.drop(1), delim)}"
    }

fun <T> makeStringTail(list: List<T>, delim: String): String {

    tailrec fun makeStringTailIter(list: List<T>, acc: String): String =
        when {
            list.isEmpty() -> acc
            acc.isEmpty() -> makeStringTailIter(list.drop(1), "${list.first()}")
            else -> makeStringTailIter(list.drop(1), "$acc $delim ${list.first()}")
        }

    return makeStringTailIter(list, "")
}

fun main() {

}