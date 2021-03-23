package tictactoe

fun main() {

    var s = "_________"
    var x: Int
    var o: Int
    var y = 'X'
    val g = arrayOf(0, 3, 6)
    val v = arrayOf(0, 1, 2)
    val play = 1

    playField(s, g, v)

    while (play == 1) {
        s = step(s, y)
        x = 0
        o = 0
        val zn = fullLine(s, y, g, v)
        for (i in 0..8) {
            if (s[i] == 'X') {
                x++
            } else if (s[i] == 'O') {
                o++
            }
        }

        when {
            zn[0].length + zn[1].length + zn[2].length == 8 -> {
                playField(s, g, v)
                println("11 ${zn[0]}${zn[1]}${zn[2]}")
                break
            }
            zn[0].length + zn[1].length + zn[2].length == 13 -> {
                if (zn[0] == " ") {
                    println(zn[1])
                    break
                } else {
                    println(zn[0])
                    break
                }
            }
            x + o == 9 -> {
                playField(s, g, v)
                println("Draw")
                break
            }
            else -> {
                playField(s, g, v)
                when (y) {
                    'X' -> y = 'O'
                    'O' -> y = 'X'
                }
            }
        }
    }
}

fun fullLine(s: String, y: Char, g: Array<Int>, v: Array<Int>): Array<String> {
    var zn = emptyArray<String>()

    zn += if (s[g[0] + v[0]] == y && s[g[0] + v[1]] == y && s[g[0] + v[2]] == y || s[g[1] + v[0]] == y && s[g[1] + v[1]] == y && s[g[1] + v[2]] == y || s[g[2] + v[0]] == y && s[g[2] + v[1]] == y && s[g[2] + v[2]] == y) {
        "$y wins"
    } else " "

    zn += if (s[v[0] + g[0]] == y && s[v[0] + g[1]] == y && s[v[0] + g[2]] == y || s[v[1] + g[0]] == y && s[v[1] + g[1]] == y && s[v[1] + g[2]] == y || s[v[2] + g[0]] == y && s[v[2] + g[1]] == y && s[v[2] + g[2]] == y) {
        "$y wins"
    } else " "

    zn += if (s[0] == y && s[4] == y && s[8] == y || s[6] == y && s[4] == y && s[2] == y) {
        "$y wins"
    } else " "

    return zn
}

fun step(s: String, y: Char): String {
    // ждём ввод хода от игрока
    var answer = ""
    var s1 = ""
    while (answer != "true") {
        print("Enter the coordinates: ")
        val sh = readLine()!!
        var sh1: Char
        var sh2: Char

        // проверяем, что введено два подходящих числа
        if (sh == "" || sh.length == 1) {
            sh1 = ' '
            sh2 = ' '
        } else {
            sh1 = sh.first()
            sh2 = sh.last()
        }

        if (!sh1.isDigit() || !sh2.isDigit() || sh1 == null || sh2 == null) {
            println("You should enter numbers!")
        } else if (sh1.toString().toInt() !in 1..3 || sh2.toString().toInt() !in 1..3) {
            println("Coordinates should be from 1 to 3!")
        } else if (s[3 * (sh1.toString().toInt() - 1) + (sh2.toString().toInt() - 1)] == 'X' || s[3 * (sh1.toString().toInt() - 1) + (sh2.toString().toInt() - 1)] == 'O') {
            println("This cell is occupied! Choose another one!")
        } else {
            answer = "true"
            for (i in 0..8) {
                s1 += if (i != 3 * (sh1.toString().toInt() - 1) + (sh2.toString().toInt() - 1)) s[i] else y
            }
        }
    }
    return s1
}

fun playField(s: String, g: Array<Int>, v: Array<Int>) {
//    отрисовать игровое поле по строке s
    println("---------")
    for (i in g) {
        println("| ${s[i + v[0]]} ${s[i + v[1]]} ${s[i + v[2]]} |")
    }
    println("---------")
}

