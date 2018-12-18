@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import lesson4.task1.mean

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */

fun addPhone(mapA: Map<String, String>, mapB: Map<String, String>, needed: MutableMap<String, MutableList<String>>) {
    for ((nameA, numberA) in mapA) needed[nameA] = mutableListOf(numberA)
    for ((nameB, numberB) in mapB) {
        if (needed[nameB] != null) {
            if (numberB != mapA[nameB]) needed[nameB]!!.add(numberB)
        } else needed[nameB] = mutableListOf(numberB)
    }
}

fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val needed = mutableMapOf<String, MutableList<String>>()
    val res = mutableMapOf<String, String>()
    addPhone(mapA, mapB, needed)
    for ((name, number) in needed) {
        res[name] = number.joinToString(separator = ", ")
    }
    return res
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */

fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val st = mutableMapOf<Int, MutableList<String>>()
    for ((student, grade) in grades) {
        if (st[grade] == null) st[grade] = mutableListOf(student) else st[grade]!!.add(student)
    }
    return st
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((name, par) in a) {
        if (a[name] != b[name]) return false
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */

fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val prod = mutableMapOf<String, MutableList<Double>>()
    val aver = mutableMapOf<String, Double>()
    for ((name, cost) in stockPrices) {
        if (prod[name] == null) prod[name] = mutableListOf(cost) else prod[name]!!.add(cost)
    }
    for ((name, cost) in prod) {
        aver[name] = mean(prod[name]!!)
    }
    return aver
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var s = ""
    var cost = 0.0
    var found = false
    for ((name, pair) in stuff) {
        if (pair.first == kind) if ((pair.second < cost) || ((cost == 0.0) && !found)) {
            s = name
            found = true
            cost = pair.second
        }
    }
    return if (found) s else null
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */

fun whoAreNotInFirst(a: Set<String>?, b: Set<String>, name: String): Set<String> {
    var set = mutableSetOf<String>()
    for (man in b) {
        if (!(a!!.contains(man)) && (man != name)) {
            if (set.isEmpty()) set = mutableSetOf(man) else if (!set.contains(man)) set.add(man)
        }
    }
    return set
}

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val hSh = mutableMapOf<String, Set<String>>()
    for ((k, v) in friends) {
        val frs = v.toMutableSet()
        for ((k2, v2) in friends) {
            if (frs.contains(k2)) frs += whoAreNotInFirst(frs, v2, k)
        }
        hSh[k] = frs.toSortedSet()
    }
    for ((k, v) in friends)
        for (man in v) if (!friends.containsKey(man)) hSh[man] = emptySet()
    return hSh
}


/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */

fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    for ((k, el) in b)
        if (a[k] == el) a.remove(k)

}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */

fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    var list = mutableListOf<String>()
    for (man in a) {
        if (b.contains(man)) {
            if (list.isEmpty()) list = mutableListOf(man) else if (!list.contains(man)) list.add(man)
        }
    }
    return list
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */

fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    for (c in word) {
        if ((!chars.contains(c.toLowerCase())) && (!chars.contains(c.toUpperCase()))) return false
    }
    return true
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */

fun extractRepeats(list: List<String>): Map<String, Int> {
    val m = mutableMapOf<String, Int>()
    for (el in list)
        if (m[el] == null) m[el] = 1 else m[el] = m[el]!! + 1
    return m.filterValues { it >= 2 }
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    for (i in 0 until words.size)
        for (j in i + 1 until words.size) {
            val w1 = words[i].toSortedSet()
            val w2 = words[j].toSortedSet()
            if (w1.containsAll(w2) && w2.containsAll(w1)) return true
        }
    return false
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */

fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    for (i in 0 until list.size)
        for (j in i + 1 until list.size)
            if (list[i] + list[j] == number) return Pair(i, j)
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */

data class Package(val things: Set<String>,/* var freeSpace: Int,*/ val cost: Int)

fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val variants = mutableMapOf<Int, Package>()
    variants[0] = Package(emptySet(), 0)
    var res = emptySet<String>()
    var cost = 0
    for (i in 0..capacity) {
        variants[i] = Package(emptySet(), 0)
        for ((thing, info) in treasures)//Пара (вес - цена)
            if ((i - info.first) >= 0) if ((info.second + variants[i - info.first]!!.cost > cost) &&
                    !variants[i - info.first]!!.things.contains(thing)) {
                cost = info.second + variants[i - info.first]!!.cost
                variants[i] = Package(variants[i - info.first]!!.things + setOf(thing), cost)
                res = variants[i]!!.things
            }
        //println("Вместимость $i: ${variants[i]!!.things}")
    }
    return res
}
