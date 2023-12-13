package com.example.KIOSK

class Beveragemn { //클래스는 대문자, 메인에서 클래스의 객체 생성할 때는 소문자
    var choicebv: Int = 0
    var bvcount: Int = 0
    var bvorder = mutableListOf<Orderlist>()
    fun makeBvOrderList() {


        val beveragelist = listOf(
            Coke("콜라",1600)
        )
        while (true) {
            var linejumper = 0
            println("<0.[처음으로]>")

            for (i in beveragelist) {
                print("<${linejumper + 1}. ${i.bvname} 단품 ${i.bvprice}원>")
                if (++linejumper % 3 == 0) {
                    println()
                } else {
                    print(" ")
                }
            }
            println("")
            try {
                choicebv = readLine()!!.toInt()
                if (choicebv == 0) {
                    println("홈 화면으로 돌아갑니다 \n")
                    break
                } else if (choicebv in 1..beveragelist.size) {
                    print("${beveragelist[choicebv - 1].bvname}을 선택하셨습니다. (0 입력시 돌아가기) 수량을 정해주세요: ")
                    bvcount = readLine()!!.toInt()
                    when (bvcount) {
                        0 -> {
                            println("메뉴 선택화면으로 돌아갑니다.")
                            continue
                        }

                        else -> {
                            println("${beveragelist[choicebv - 1].bvname} ${beveragelist[choicebv - 1].bvprice} / ${bvcount}개가 주문 목록에 추가되었습니다.")
                            bvorder.add(Orderlist(beveragelist[choicebv - 1].bvname, beveragelist[choicebv - 1].bvprice, bvcount))
                        }
                    }

                    var bvtotal: Int = 0
                    for (i in bvorder) {

                        println("${i.name}, ${i.count}개, 금액:${i.price * i.count}원")
                        bvtotal = bvtotal + i.count * i.price
                    }

                    println("음료 메뉴 총 가격: ${bvtotal}\n")
                }
            }
            catch(e: java. lang . NumberFormatException) {
                println("숫자로 입력해주세요.")
            }

        }
    }
}


open class BEVERAGE(val bvname: String, val bvprice: Int)

class Coke(bvname: String, bvprice: Int) : BEVERAGE(bvname, bvprice)
