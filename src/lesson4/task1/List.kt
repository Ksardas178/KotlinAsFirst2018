@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import java.lang.StringBuilder
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    v.forEach {
        result += sqr(it)
    }
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>) = if (list.isEmpty()) {
    0.0
} else {
    list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */

fun center(list: MutableList<Double>): MutableList<Double> {
    val m = mean(list)
    for (i in 0 until list.size) {
        list[i] -= m
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var r = 0.0
    for (i in 0 until a.size) {
        r += a[i] * b[i]
    }
    return r
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var r = 0.0
    for (i in 0..(p.size - 1)) {
        r += p[i] * x.pow(i.toDouble())
    }
    return r
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var c = n
    val l = mutableListOf<Int>()
    for (i in 2..(n / 2)) {
        while (c % i == 0) {
            c /= i
            l.add(i)
        }
    }
    if (l.isEmpty()) l.add(n)
    return l
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val l = mutableListOf<Int>()
    var c = n
    while (c != 0) {
        l.add(c % base)
        c /= base
    }
    if (l.isEmpty()) l.add(0)
    return l.asReversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val sb = StringBuilder()
    val l = convert(n, base)
    for (i in 0 until l.size) {
        sb.append(if (l[i] < 10) {
            l[i]
        } else (l[i] + 'a'.toInt() - 10).toChar())
    }
    return sb.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var r = 0
    for (i in 0 until digits.size) {
        r *= base
        r += digits[i]
    }
    return r
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var r = 0
    for (i in 0 until str.length) {
        r *= base
        r += if (str[i] <= '9') {
            str[i].toInt() - '0'.toInt()
        } else {
            str[i].toInt() - 'a'.toInt() + 10
        }
    }
    return r
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun rF(n: Int) = when (n) {
    0 -> null
    1 -> ""
    2 -> ""
    3 -> ""
    4 -> ""
    else -> ""
}

fun roman(n: Int): String {
    var i = 0
    val sb = StringBuilder()
    val s10 = listOf("I", "X", "C", "M")
    val s5 = listOf("V", "L", "D")
    //val so = listOf("IV", "IX", "XL", "XC", "CD", "CM")
    val num = convert(n, 10).reversed()
    for (base in num) {
        when (base) {
            in 0..3 -> for (j in 1..base) sb.append(s10[i])
            4 -> sb.append(s5[i] + s10[i])
            in 5..8 -> {
                for (j in 6..base) sb.append(s10[i]); sb.append(s5[i])
            }
            9 -> sb.append(s10[i + 1] + s10[i])
        }
        i += 1
    }
    return sb.toString().reversed()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun rF3(n: Int) = when (n) {
    0 -> null
    1 -> "одна"
    2 -> "две"
    3 -> "три"
    4 -> "четыре"
    5 -> "пять"
    6 -> "шесть"
    7 -> "семь"
    8 -> "восемь"
    else -> "девять"
}

fun rL3(n: Int) = when (n) {
    1 -> "один"
    2 -> "два"
    else -> rF3(n)
}

fun rF2(n: Int) = when (n) {
    0 -> null
    1 -> "десять"
    2 -> "двадцать"
    3 -> "тридцать"
    4 -> "сорок"
    9 -> "девяносто"
    else -> rF3(n) + "десят"
}

fun rF1(n: Int) = when (n) {
    0 -> null
    1 -> "сто"
    2 -> "двести"
    3 -> "триста"
    4 -> "четыреста"
    else -> rF3(n) + "сот"
}

fun rExc(n: Int) = when (n) {
    12 -> "две"
    14 -> "четыр"
    15 -> "пят"
    16 -> "шест"
    17 -> "сем"
    18 -> "восем"
    19 -> "девят"
    else -> rL3(n)
} + "надцать"

fun russLast3(n: Int): List<String> {
    val num = mutableListOf<String>()
    if (rF1(n / 100) != null) num.add(rF1(n / 100)!!)
    if ((n % 100 > 19) || (n % 100 < 11)) {
        if (rF2(n / 10 % 10) != null) num.add(rF2(n / 10 % 10)!!)
        if (rF3(n % 10) != null) num.add(rL3(n % 10)!!)
    } else num.add(rExc(n % 100))
    return num
}

fun russFirst3(n: Int): List<String> {
    val num = mutableListOf<String>()
    if (rF1(n / 100) != null) num.add(rF1(n / 100)!!)
    if ((n % 100 > 19) || (n % 100 < 11)) {
        if (rF2(n / 10 % 10) != null) num.add(rF2(n / 10 % 10)!!)
        if (rF3(n % 10) != null) num.add(rF3(n % 10)!!)
    } else num.add(rExc(n % 100))
    return num
}

fun russian(n: Int): String {
    val num = mutableListOf<String>()
    num += russFirst3(n / 1000)
    if (n / 1000 != 0) num += when (n / 1000 % 10) {
        1 -> "тысяча"
        in 2..4 -> "тысячи"
        else -> "тысяч"
    }
    num += russLast3(n % 1000)
    //println(num.joinToString(separator = " "))
    return num.joinToString(separator = " ")
}