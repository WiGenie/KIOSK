package com.example.KIOSK

import android.annotation.SuppressLint

fun main(){

    var menubtn:Int=0


    val burgermn=burgermn()
    val chickmn=chickmn()
    println("맘스터치 키오스크입니다.")
    while (true) {


        println("<[1]버거> <[2]치킨> <[3]사이드> <[4]음료> <[5]주문 및 계산하기> <[6]종료>")
        try {
            menubtn= readLine()!!.toInt()
            when (menubtn) {
                1 -> {
                    burgermn.burgermn()
                }
                2 -> {
                    chickmn.chickmn()
                }
//                3 -> {
//                    sidemn()
//                }
//                4 -> {
//                    bevemn()
//                }
                5 ->{
                    letorder(burgermn,chickmn)
                }
                6 -> {
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

@SuppressLint("SuspiciousIndentation")
fun letorder(burgermn: burgermn, chickmn: chickmn){

    var totalprice:Int=0

        println("<0.처음으로> <1.포장하기> <2.매장 내 식사>") //제공 방식을 먼저 물어보고 계산표 출력 및 계산하기
    var whereeat:Int=0
    while(true){
        whereeat= readLine()!!.toInt()
        when (whereeat) {
            0 -> {
                println("홈 화면으로 돌아갑니다 \n")
                break
            }
            1 -> {
                println("포장을 선택하셨습니다.")
            }

            2 -> {
                println("매장 내 식사를 선택하셨습니다.")
            }
            else -> {
                println("숫자로 입력해주세요.")
                continue
            }
        }

        var totallist=burgermn.bgorder + chickmn.ckorder //현재 잣됐음 작동을 안 함
        for (i in totallist){
            println("${i.name}, ${i.price}, ${i.count}")
            totalprice += i.price*i.count
        }
    }

}

data class orderlist(val name: String, val price: Int, val count: Int)