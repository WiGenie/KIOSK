package com.example.KIOSK

class burgermn {
    var choicebg:Int=0
    fun burgermn(){


    val setmn=2300
    var burgerlist= listOf(
        Thighbg("싸이버거", 4600),
        bulgobg("불고기버거", 3900)
    )

        while (true) {
            var linejumper = 0
            println("<0.[돌아가기]>")

            for (i in burgerlist) {
                print("<${linejumper + 1}. ${i.bgname} 단품 ${i.bgprice}원, 세트 ${i.bgprice + setmn}원>")
                if (++linejumper % 4 == 0) {
                    println()
                } else {
                    print(" ")
                }
            }
            try {
                choicebg= readLine()!!.toInt()
                when (choicebg){
                    0 -> {
                        println("홈 화면으로 돌아갑니다 \n")
                        break
                    }
                    1 -> {
                        burgerlist[0]
                    }
                }
            }
catch (e: java.lang.NumberFormatException){
    println("숫자로 입력해주세요.")
}
        }
}
}

open class BURGER(val bgname:String, val bgprice:Int)

class Thighbg(bgname:String, bgprice:Int) : BURGER(bgname, bgprice)

class bulgobg(bgname:String, bgprice:Int) : BURGER(bgname, bgprice)