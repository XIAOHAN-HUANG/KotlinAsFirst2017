@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val a = age%10
    return when {
        age == 1 -> "$age год"
        age == 2 -> "$age года"
        age in 11..19  -> "$age лет"
        a == 1 && age != 11 && age != 111-> "$age год"
        a == 2 -> "$age года"
        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val a = t1 * v1
    val b = t2 * v2
    val c = t3 * v3
    val s = (a + b + c)/2
    return when {
        s < a -> s/v1
        s in a..a+b -> (s - a)/v2 + t1
        s in a+b..a+b+c -> (s - (a + b))/v3 + (t1 + t2)
        else -> throw IllegalArgumentException("Время не существует")
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int = when {
                           kingX == rookX1 && kingY != rookY2 -> 1
                           kingY == rookY1 && kingX != rookX2 -> 1
                           kingX == rookX2 && kingY != rookY1 -> 2
                           kingY == rookY2 && kingX != rookX1 -> 2
                           kingX == rookX1 && kingY == rookY2 -> 3
                           kingX == rookX2 && kingY == rookY1 -> 3
                           kingX != rookX1 && kingX != rookX2 && kingY != rookY1 && kingY != rookY2 -> 0
                           else -> throw IllegalArgumentException("Место не существует")
                       }

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun sqr(x: Int) = x * x
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val x = sqr (bishopX - kingX)
    val y = sqr (bishopY - kingY)
    return when {
        (kingX == rookX || kingY == rookY) && x != y  -> 1
        x == y && kingX != rookX && kingY != rookY    -> 2
        x == y && (kingX == rookX || kingY == rookY ) -> 3
        x != y && kingX != rookX && kingY != rookY  -> 0
        else -> throw IllegalArgumentException("Место не существует")
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun sqr(x: Double ) = x * x
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val cosA = (sqr(b)+sqr(c)-sqr(a))/(2*b*c)
    val cosB = (sqr(a)+sqr(c)-sqr(b))/(2*a*c)
    val cosC = (sqr(a)+sqr(b)-sqr(c))/(2*a*b)
    return when {
        (cosA>0&&cosB>0&&cosC>0)&&(a+b>c&&a+c>b&&b+c>a)          -> 0
        (cosA==0.0||cosB==0.0||cosC==0.0)&&(a+b>c&&a+c>b&&b+c>a) -> 1
        (cosA<0||cosB<0||cosC<0)&&(a+b>c&&a+c>b&&b+c>a)          -> 2
        else                                                     -> -1
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val l1 = b - c
    val l2 = d - c
    val l3 = d - a
    val l4 = b - a
    return when {
        a <= c && b <= d && c <= b -> l1
        a <= c && d <= b && c <= d -> l2
        c <= a && d <= b && a <= d -> l3
        c <= a && b <= d && a <= b -> l4
        else -> -1
    }
}
