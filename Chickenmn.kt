package com.example.KIOSK


class Chickenmn {
    var choiceck: Int = 0
    var singleorset: Int = 0
    var ckcount: Int = 0
    var cktotal: Int = 0
    var ckorder = mutableListOf<Orderlist>()

    fun makeCkOrderList() {
//변수를 최초 선언하는 부분에서 변수에 마우스를 갖다 대고 Shift+F6을 누르면 리팩터를 통해 이후 사용된 모든
//        같은 변수의 이름을 바꿀 수 있다!! 노가다 멈춰

        val boneless = 1000
        val chickenlist = listOf(
            Friedck("후라이드치킨", 10900),
            KFCck("맘스양념치킨", 12900),
            SoyCK("간장마늘치킨", 12900),
            CheeseCK("치즈뿌린치킨", 12900),
        )
        println("치킨을 순살로 변경 시 1000원이 추가됩니다.")
        while (true) {
            var linejumper = 0
            println("<0.[처음으로]>")

            for (i in chickenlist) {
                print("<${linejumper + 1}. ${i.ckname} 뼈 ${i.ckprice}원>")
                if (++linejumper % 3 == 0) {
                    println()
                } else {
                    print(" ")
                }
            }
            println("")
            try {
                choiceck = readLine()!!.toInt()
                if (choiceck == 0) {
                    println("홈 화면으로 돌아갑니다 \n")
                    break
                } else if (choiceck in 1..chickenlist.size) {
                    println("${chickenlist[choiceck - 1].ckname}를 선택하셨습니다. <0.돌아가기> <1.뼈 ${chickenlist[choiceck - 1].ckprice}원> <2.순살 ${chickenlist[choiceck - 1].ckprice + boneless}원> 중 선택해주세요.")
                    singleorset = readLine()!!.toInt()
                    when (singleorset) {
                        0 -> continue
                        1 -> {
                            print("뼈를 선택하셨습니다. (0 입력시 돌아가기)수량을 정해주세요: ")
                            ckcount = readLine()!!.toInt()
                            when (ckcount) {
                                0 -> {
                                    println("메뉴 선택화면으로 돌아갑니다.")
                                    continue
                                }

                                else -> {
                                    println("${chickenlist[choiceck - 1].ckname} 뼈 ${chickenlist[choiceck - 1].ckprice} / ${ckcount}개가 주문 목록에 추가되었습니다.")
                                    ckorder.add(
                                        Orderlist(
                                            "${chickenlist[choiceck - 1].ckname} 뼈",
                                            chickenlist[choiceck - 1].ckprice,
                                            ckcount
                                        )
                                    )
                                }
                            }
                        }
                        2 -> {
                            print("순살을 선택하셨습니다. (0 입력시 돌아가기)수량을 정해주세요: ")
                            ckcount = readLine()!!.toInt()
                            when (ckcount) {
                                0 -> {
                                    println("메뉴 선택화면으로 돌아갑니다.")
                                    continue
                                }

                                else -> {
                                    println("${chickenlist[choiceck - 1].ckname} 순살 ${chickenlist[choiceck - 1].ckprice + boneless} / ${ckcount}개가 주문 목록에 추가되었습니다.")
                                    ckorder.add(
                                        Orderlist(
                                            "${chickenlist[choiceck - 1].ckname} 순살",
                                            chickenlist[choiceck - 1].ckprice + boneless,
                                            ckcount
                                        )
                                    )
                                }
                            }
                        }
                    }
                    for (i in ckorder) {

                        println("${i.name}, ${i.count}개, 금액:${i.price * i.count}원")
                        cktotal = cktotal + i.count * i.price
                    }
                    println("치킨 메뉴 총 가격: ${cktotal}\n")
//                    총 가격 bgtotal을 추후 메인의 totalprice에 합산
//                    bgorder 함수 또한 메인의 letorder에 합산.
//                    근데 bgorderlist를 juiceorderlist같이 여러개 만들지 말고
//                    그냥 통합 주문 시스템으로 letorder대신 운용하면 되지 않을까?
//                    좀 생각해봤는데 어려울 듯 하다. 메인 함수에서 이 리스트를 어떻게 굴릴지 모르겠다.
//                    위의 print부분과 밑의 총 가격 프린트는 지우던가 break를 걸어서 홈으로 보내자.
                } else {
                    println("메뉴의 숫자 안에서 선택해주세요. \n")
                }
            } catch (e: java.lang.NumberFormatException) {
                println("숫자로 입력해주세요.")
            }
        }
    }
}


open class CHICKEN(val ckname: String, val ckprice: Int)

class Friedck(ckname: String, ckprice: Int) : CHICKEN(ckname, ckprice)

class KFCck(ckname: String, ckprice: Int) : CHICKEN(ckname, ckprice)

class SoyCK(ckname: String, ckprice: Int) : CHICKEN(ckname, ckprice)

class CheeseCK(ckname: String, ckprice: Int) : CHICKEN(ckname, ckprice)
