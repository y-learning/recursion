fun append(s: String, c: Char): String = "$s $c"

fun preAppend(c: Char, s: String): String = "$c $s"

fun <T> rest(collection: List<T>): List<T> = collection.drop(1)

fun toString(list: List<Char>): String =
    if (list.isEmpty()) ""
    else preAppend(list[0], toString(list.subList(1, list.size)))

fun toStringTail(list: List<Char>): String {
    tailrec fun toStringTailIter(list: List<Char>, s: String): String =
        if (list.isEmpty()) s
        else toStringTailIter(rest(list), append(s, list.first()))

    return toStringTailIter(list, "")
}

fun sum(n: Int): Int = if (n == 0) 0 else n + sum(n - 1)

fun sumTail(n: Int): Int {
    tailrec fun sumTailIter(n: Int, s: Int): Int =
        if (n == 0) s else sumTailIter(n - 1, s + n)

    return sumTailIter(n, 0)
}

fun inc(n: Int) = n + 1

fun dec(n: Int) = n - 1

tailrec fun add(a: Int, b: Int): Int {
    if (a == 0) return b

    return add(dec(a), inc(b))
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