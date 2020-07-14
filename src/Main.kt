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
            rest(list).isEmpty() ->
                "${list.first()} ${makeString(rest(list), delim)}"
            else ->
                "${list.first()} $delim ${makeString(rest(list), delim)}"
        }

fun <T> makeStringTail(list: List<T>, delim: String): String {
    tailrec fun makeStringTailIter(list: List<T>, acc: String): String {
        if (list.isEmpty()) return acc

        val rest = rest(list)
        val first = list.first()

        return if (acc.isEmpty()) makeStringTailIter(rest, "$first")
        else makeStringTailIter(rest, "$acc $delim $first")
    }

    return makeStringTailIter(list, "")
}

fun <T, U> foldLeft(list: List<T>, acc: U, f: (T, U) -> U): U {
    tailrec fun foldLeftIter(list: List<T>, acc: U): U =
            if (list.isEmpty()) acc
            else foldLeftIter(rest(list), f(list.first(), acc))

    return foldLeftIter(list, acc)
}

fun sumFoldLeft(n: Int): Int = foldLeft((0..n).toList(), 0, Int::plus)

fun toStringFoldLeft(list: List<Char>): String =
        foldLeft(list, "", { c, acc -> append(acc, c) })

fun <T> f(delim: String): (T, String) -> String =
        { item: T, acc: String ->
            if (acc.isEmpty()) "$acc $item"
            else "$acc $delim $item"
        }

fun <T> makeStringFoldLeft(list: List<T>, delim: String): String =
        foldLeft(list, "", { item: T, acc: String ->
            if (acc.isEmpty()) "$item"
            else "$acc $delim $item"
        })

fun string(list: List<Char>): String =
        if (list.isEmpty()) ""
        else preAppend(list.first(), string(rest(list)))

fun <T, U> foldRight(list: List<T>, stop: U, f: (T, U) -> U): U {
    fun foldRight(list: List<T>, stop: U) =
            if (list.isEmpty()) stop
            else f(list.first(), foldRight(rest(list), stop, f))

    return foldRight(list, stop)
}

fun stringFoldRight(list: List<Char>) =
        foldRight(list, "", ::preAppend)