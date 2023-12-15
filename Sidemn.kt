package com.example.KIOSK


class Sidemn {
    var choicesd: Int = 0
    var sdcount: Int = 0
    var sdtotal: Int = 0
    var sdorder = mutableListOf<Orderlist>()
    fun makeSdOrderList() {


        val sidelist = listOf(
            Frenchfries("감자튀김", 2000),
            Conesalad("콘샐러드", 1800),
            Cheeseball("치즈볼", 2100),
            Coleslaw("코울슬로", 1800),
            CheeseStick("치즈스틱", 2000)
        )
        while (true) {
            var linejumper = 0
            println("<0.[처음으로]>")

            for (i in sidelist) {
                print("<${linejumper + 1}. ${i.sdname} ${i.sdprice}원>")
                if (++linejumper % 3 == 0) {
                    println()
                } else {
                    print(" ")
                }
            }
            println("")
            try {
                choicesd = readLine()!!.toInt()
                if (choicesd == 0) {
                    println("홈 화면으로 돌아갑니다 \n")
                    break
                } else if (choicesd in 1..sidelist.size) {
                    print("${sidelist[choicesd - 1].sdname}을 선택하셨습니다. (0 입력시 돌아가기) 수량을 정해주세요: ")
                    sdcount = readLine()!!.toInt()
                    when (sdcount) {
                        0 -> {
                            println("메뉴 선택화면으로 돌아갑니다.")
                            continue
                        }

                        else -> {
                            println("${sidelist[choicesd - 1].sdname} ${sidelist[choicesd - 1].sdprice} / ${sdcount}개가 주문 목록에 추가되었습니다.\n")
                            sdorder.add(Orderlist(sidelist[choicesd - 1].sdname, sidelist[choicesd - 1].sdprice, sdcount))


                        }
                    }

//                    for (i in sdorder) {
//
//                        println("${i.name}, ${i.count}개, 금액:${i.price * i.count}원")
//                        sdtotal = sdtotal + i.count * i.price
//                    }
//
//
//                    println("사이드 메뉴 총 가격: ${sdtotal}\n")
                }
            } catch (e: java.lang.NumberFormatException) {
                println("숫자로 입력해주세요.")
            }

        }
    }
}

//클래스 첫글자만 대문자로
open class SIDE(val sdname: String, val sdprice: Int)

class Frenchfries(sdname: String, sdprice: Int) : SIDE(sdname, sdprice)

class Conesalad(sdname: String, sdprice: Int) : SIDE(sdname, sdprice)

class Cheeseball(sdname: String, sdprice: Int) : SIDE(sdname, sdprice)

class Coleslaw(sdname: String, sdprice: Int) : SIDE(sdname, sdprice)

class CheeseStick(sdname: String, sdprice: Int) : SIDE(sdname, sdprice)
