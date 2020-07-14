object Factorial {
    val factorial: (Int) -> Int by lazy {
        { n: Int ->
            if (n == 1) n
            else n * factorial(n - 1)
        }
    }
}

val factorial: (Int) -> Int by lazy {
    { n: Int ->
        if (n == 1) n
        else n * factorial(n - 1)
    }
}