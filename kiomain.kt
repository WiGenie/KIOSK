package com.example.KIOSK

fun main(){
    var whereeat:Int=0
    var menubtn:Int=0
    println("맘스터치 키오스크입니다.")
    while (true) {
        println("<1.포장> <2.매장>")
        whereeat= readLine()!!.toInt()
        when (whereeat) {
            1 -> {
                println("포장을 선택하셨습니다.")
            }

            2 -> {
                print("매장 내 식사를 선택하셨습니다.")
            }
            else -> {
                println("숫자로 입력해주세요.")
                continue
            }
        }
        println("<[1]버거> <[2]치킨> <[3]사이드> <[4]음료> <[5]종료>")
        try {
            menubtn= readLine()!!.toInt()
            when (menubtn) {
                1 -> {
//                    burgermn()
//                }
//
//                2 -> {
//                    chickmn()
//                }
//
//                3 -> {
//                    sidemn()
//                }
//                4 -> {
//                    bevemn()
                }
                5 -> {
                    println("프로그램을 종료합니다.")
                    break
                }
            }
        }
        catch (e: java.lang.NumberFormatException){
            println("숫자로 입력해주세요.")
        }

    }
}