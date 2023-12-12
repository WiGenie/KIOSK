package com.example.KIOSK

class burgermn {
    var choicebg:Int=0
    var singleorset:Int=0
    var bgcount:Int=0
    var bgorder= mutableListOf<orderlist>()
    fun burgermn(){


    val setmn=2300
    val burgerlist= listOf(
        Thighbg("싸이버거", 4600),
        bulgobg("불고기버거", 3900),
        whiteThighbg("화이트갈릭싸이버거", 5200),
        Thiflexbg("싸이플렉스버거", 7700),
        shrpThiflexbg("쉬림프싸이플렉스버거", 7000)
    )
        println("버거 단품을 세트로 변경 시 2300원이 추가됩니다.")
        while (true) {
            var linejumper = 0
            println("<0.[처음으로]>")

            for (i in burgerlist) {
                print("<${linejumper + 1}. ${i.bgname} 단품 ${i.bgprice}원>")
                if (++linejumper % 3 == 0) {
                    println()
                } else {
                    print(" ")
                }
            }
            println("")
            try {
                choicebg= readLine()!!.toInt()
                if (choicebg==0) {
                    println("홈 화면으로 돌아갑니다 \n")
                    break
                }
                else if (choicebg in 1.. burgerlist.size){
                    println("${burgerlist[choicebg-1].bgname}를 선택하셨습니다. <0.돌아가기> <1.단품 ${burgerlist[choicebg-1].bgprice}원> <2.세트 ${burgerlist[choicebg-1].bgprice+setmn}원> 중 선택해주세요.")
                    singleorset= readLine()!!.toInt()
                    when (singleorset){
                        0 -> continue
                        1 -> {print("단품을 선택하셨습니다. 수량을 정해주세요: ")
                            bgcount= readLine()!!.toInt()
                            println("${burgerlist[choicebg-1].bgname} 단품 ${burgerlist[choicebg-1].bgprice} / ${bgcount}개가 주문 목록에 추가되었습니다.")
                            bgorder.add(orderlist("${burgerlist[choicebg-1].bgname} 단품", burgerlist[choicebg-1].bgprice, bgcount))
                        }
                        2 -> {print("세트를 선택하셨습니다. 수량을 정해주세요: ")
                            bgcount= readLine()!!.toInt()
                            println("${burgerlist[choicebg-1].bgname} 세트 ${burgerlist[choicebg-1].bgprice+setmn} / ${bgcount}개가 주문 목록에 추가되었습니다.")
                            bgorder.add(orderlist("${burgerlist[choicebg-1].bgname} 세트", burgerlist[choicebg-1].bgprice+setmn, bgcount))
                        }
                    }
                    var bgtotal:Int=0
                    for (i in bgorder) {

                        println("${i.name}, ${i.count}개, 금액:${i.price * i.count}원")
                        bgtotal = bgtotal + i.count*i.price
                    }


                    println("버거 메뉴 총 가격: ${bgtotal}\n\n")

//                    총 가격 bgtotal을 추후 메인의 totalprice에 합산
//                    bgorder 함수 또한 메인의 letorder에 합산.
//                    근데 bgorderlist를 juiceorderlist같이 여러개 만들지 말고
//                    그냥 통합 주문 시스템으로 letorder대신 운용하면 되지 않을까?
//                    좀 생각해봤는데 어려울 듯 하다. 메인 함수에서 이 리스트를 어떻게 굴릴지 모르겠다.
//                    위의 print부분과 밑의 총 가격 프린트는 지우던가 break를 걸어서 홈으로 보내자.
                }

                else{
                    println("메뉴의 숫자 안에서 선택해주세요. \n")
                }
                }
            catch (e: java.lang.NumberFormatException) {
                println("숫자로 입력해주세요.")
            }
        }
}
}


open class BURGER(val bgname:String, val bgprice:Int)

class Thighbg(bgname:String, bgprice:Int) : BURGER(bgname, bgprice)

class bulgobg(bgname:String, bgprice:Int) : BURGER(bgname, bgprice)

class whiteThighbg(bgname: String, bgprice: Int) : BURGER(bgname, bgprice)

class Thiflexbg(bgname: String, bgprice: Int) : BURGER(bgname, bgprice)

class shrpThiflexbg(bgname: String, bgprice: Int) : BURGER(bgname, bgprice)
