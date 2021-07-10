package com.example.dieseloneassignment

import java.util.*
import kotlin.collections.HashMap

fun main(){
    pattern(5)

    val leaderboard = intArrayOf(200, 100, 100, 50, 10)
    val player = intArrayOf(20, 80, 80, 10, 10, 300)

    sortedPlayer(leaderboard, player)
    println("")

    question3(17, 8)
}

fun findC(r: Int): Long{
    val c = Math.sqrt(
        Math.pow(r.toDouble(), 2.0) + Math.pow(r.toDouble(), 2.0)
    )


   return Math.round(c)
}
fun pattern(r: Int){
    val totalIndex = (r*2) + 1
    val array = Array(totalIndex){ CharArray(totalIndex) }

    val lastIndexFirst = findC(r+1)

    for (i in 0..r){
        for (j in 0..array.lastIndex){
            val b = lastIndexFirst - r
            array[i][j] = '-'
            array[r+i][j] = '-'
            val oppositeIndexUpper = r-i
            val oppositeIndexBelow = i + oppositeIndexUpper
            if (i == 0){
                if (Math.abs(r-j) <= b){
                    array[i][j] = 'X'
                    array[array.lastIndex][j] = 'X'
                }
            }

            if(j==0){
                if (Math.abs(r-i) <= b){
                    array[i][j] = 'X'
                    array[oppositeIndexBelow][j] = 'X'
                }
            }

            if (j == r || i == r){
                array[i][j] = 'X'
                array[r+i][j] = 'X'
            }else{
                if (j - i >= 0){
                    array[j-i][j-i] = 'X'
                }

                if (r+i<= array.lastIndex){
                    array[r+i][r+i]='X'
                }

                val oppositeIndexUpper = r-i
                if (r - i >= 0 && r + oppositeIndexUpper <= array.lastIndex) {
                    array[r - oppositeIndexUpper][r+oppositeIndexUpper] = 'X'
                }

                if (r + i <= array.lastIndex && oppositeIndexUpper >=0) {
                    array[r + i][oppositeIndexUpper] = 'X'
                }

            }
        }
    }

    for (row in array) {
        println(row.contentToString())
    }
}



fun sortedPlayer(leaderboard: IntArray, player: IntArray){
    val setSort: HashSet<Int> = HashSet()
    val peringkat: HashMap<Int, Int> = HashMap()
    leaderboard.forEach {
        setSort.add(it)
    }
    player.forEach {
        setSort.add(it)
    }
    val sorted = setSort.sortedDescending()

    sorted.forEachIndexed { index, i ->
        peringkat.put(i, index+1)
    }
    print("Soal 2 : " )
    player.forEach { playerScore ->
       print(peringkat.get(playerScore).toString() + " ")
    }
}

fun question3(a: Int, b:Int){
    var listArray = arrayListOf<Char>()
    if (a >= b){
        for (i in 0..a-1){
            listArray.add('a')
        }
        for (i in 0..b-1){
            listArray.add('b')
        }

        recursifCheckAb(listArray.toCharArray(), 3)

    }else{
        for (i in 0..b-1){
            listArray.add('b')
        }
        for (i in 0..a-1){
            listArray.add('a')
        }
        recursifCheckAb(listArray.toCharArray(), 3)
    }
}

fun recursifCheckAb(listArray: CharArray, maxSide: Int){
    var array = listArray
    var defaultChar = array[0]
    var sideFound = 0;

    var isFinish = true

    for (i in 0..array.lastIndex){
        if (array[i] == defaultChar){
            sideFound += 1
        }else{
            defaultChar = array[i]
            sideFound = 1
        }

        if (sideFound > maxSide){
            for (j in i+1..array.lastIndex){
                isFinish = false
                if (array[j] != array[i]){
                    val temp = array[j]
                    array[j] = array[i]
                    array[i] = temp
                    sideFound = 1
                    isFinish = true
                    break
                }
            }
        }
    }
    if (isFinish.not()){
        val maxSideUpdate = maxSide-1
        if (maxSideUpdate > 0) {
            recursifCheckAb(array, maxSideUpdate)
        }else{
            println("cannot find any solution")
        }
    }

    println("Soal 3 : " + array.contentToString())
}
