@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var c = n
    var res = 0
    while (c != 0) {
        c /= 10
        res += 1
    }
    return if (n != 0) res else 1
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a = 1
    var b = 1
    for (i in 1..((n - 1) / 2)) {
        a = a + b
        b = b + a
    }
    return if (n % 2 == 0) b else a
}

fun lowDiv(m: Int, n: Int): Int {
    var a = m
    var b = n
    while (a != b) {
        if (max(a, b) == a) {
            a = a - b
        } else {
            b = b - a
        }
    }
    return a
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = m * n / lowDiv(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var d = 2
    while (n % d != 0) {
        d = if (n / 2 <= d) n else d + 1
    }
    return d
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var d = n / 2
    while (n % d != 0) {
        d -= 1
    }
    return d
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = (lowDiv(m, n) == 1)

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = (ceil(sqrt(n.toDouble() + 0.5)) - ceil(sqrt(m.toDouble())) > 0)

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var c = x
    var res = 0
    while (c != 1) {
        if (c % 2 == 0) {
            c /= 2
        } else {
            c = c * 3 + 1
        }
        res += 1
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun neededFun(angle: Double, init: Double, eps: Double, sign: Int): Double {
    var i = init
    var s = sign
    var result = 0.0
    while (abs((Math.pow(angle, i)) / factorial(i.toInt())) >= eps) {
        result += s * Math.pow(angle, i) / factorial(i.toInt())
        s *= -1
        i += 2
    }
    return result
}

fun sin(x: Double, eps: Double): Double = neededFun(x % (2 * PI), 1.0, eps, 1)

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = 1 + neededFun(x % (2 * PI), 2.0, eps, -1)

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var c = n
    var revN = 0
    while (c > 0) {
        revN = 10 * revN + c % 10
        c /= 10
    }
    return revN
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = (revert(n) == n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var c = n
    if (c >= 10) {
        while (c >= 10) {
            if ((c % 10) != (c / 10 % 10)) {
                return true
            }
            c /= 10
        }
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var k = 1L
    var i = 0L
    var с = n.toLong()
    while (с > 0) {
        i += 1
        с += i * floor(sqrt((k - 1).toDouble())).toInt() //Прибавляем все цифры чисел, которые не надо было отнимать чуть ниже
        k *= 10
        с -= i * floor(sqrt((k - 1).toDouble())).toInt() //Вычитаем все цифры чисел, где знаков <= i (да-да, i-1, i-2 и прочие тут же)
    } //Теперь i показывает, сколько знаков в числе,одна из цифр которого стоит под номером N}
    var t = floor(sqrt((k - 1).toDouble())) + (с / i)
    /*
    Из наибольшего из i-значных чисел вычтем расстояние до N в числах
    (N отрицательное, см. цикл), получим корень из числа, где присутствует искомая цифра
    */
    var el = i + (с % i)
    с = sqr(t).toLong()
    return (с / Math.pow(10.0, (i - el).toDouble()).toInt() % 10).toInt()
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var c = 1
    var i = 1
    var k = 1
    var a = 1
    var b = 1
    while (c < n) {
        b += a
        a = b - a
        if (a / k >= 10) {
            i += 1
            k *= 10
        }
        c += i
    }
    return (a / Math.pow(10.0, (c - n).toDouble()) % 10).toInt()
}