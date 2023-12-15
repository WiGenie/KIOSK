package com.example.KIOSK

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.NumberFormatException
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun main() {

    var menubtn: Int = 0

//인스턴스 소문자, bugermn은 Burgermn()의 객체이자 인스턴스
    // 객체가 좀 더 넓은 의미를 가지며 인스턴스가 객체 안에 포함됨.
    // 객체는 클래스 기반으로 생성되는 썸띵을 포괄적으로 표현, 인스턴스는 거기서 각각의 이름이 명명된 것
    // 즉 여기서 burgermn은 인스턴스이자 객체지만 가장 확실한 표현은 인스턴스이다.
    // 왜냐? burgermn이라는 이름까지 부여받았으므로.
    // 클래스(사람의 설계도)를 이용해
    // 보통 인스턴스(홍길동)를 만들어 사용하는거지
    // 이름을 부여받지 못한 객체(사람이란 개념) 자체를 쓰지는 않음
    val burgermn = Burgermn()
    val chickenmn = Chickenmn()
    val sidemn = Sidemn()
    val beveragemn = Beveragemn()
    println("맘스터치 키오스크입니다.")
    try {
        while (true) {


            println("<[1]버거> <[2]치킨> <[3]사이드> <[4]음료> <[5]주문 및 계산하기> <[6]종료>")
            menubtn = readLine()!!.toInt()
            when (menubtn) {
                1 -> burgermn.makeBgOrderList()
                2 -> chickenmn.makeCkOrderList()
                3 -> sidemn.makeSdOrderList()
                4 -> beveragemn.makeBvOrderList()
                5 -> {
                    val orderResult= letOrder(burgermn, chickenmn, sidemn, beveragemn)
                    if (orderResult == "OrderComplete"){
                        val random= Random
                        val randomCustomer=random.nextInt(1, 4)
                        var reverserandomCustomor=randomCustomer
                        println("주문 대기열을 확인 중입니다...")
                        for (i in 0 .. randomCustomer){
                            if(reverserandomCustomor != 0){
                                runBlocking {
                                    launch {
                                        delay(5000)
                                        println("현재 주문 대기인원은 ${reverserandomCustomor}명 입니다.")
                                    }
                                }
                                --reverserandomCustomor
                            }
                            else if (i == randomCustomer){
                                println("고객님의 순서가 되었습니다! 매대 앞에서 잠시 기다려주세요.")
                                return
                            }
                        }
                    }
                }
                6 -> {
                    println("프로그램을 종료합니다.")
                    break
                }
            }
        }
    } catch (e: java.lang.NumberFormatException) {
        println("숫자로 입력해주세요.")
    }

}

//@supressLint:"SuspiciousIndentation" 이런식으로 나온거 걍 줄이나 띄우기 구분
//하라는거니까 걍 ctrl+alt+L ㄱㄱ(비주얼 스튜디오의 ctrl shift f랑 같은 기능)
fun letOrder(
    burgermn: Burgermn, chickenmn: Chickenmn,
    sidemn: Sidemn, beveragemn: Beveragemn
):String {

    var myMoney= 100000

    //제공 방식을 먼저 물어보고 계산표 출력 및 계산하기

    while (true) {


        // 리스트들을 합칠 때 얘네들이 mutableListOf라고 해서
        // totallist도 변수 속성을 따라가는게 아님 (아마 Any같은걸로 지정될 듯)
        // 그래서 괄호로 각 메뉴 목록들을 묶고 toMutableList()로 변환해야 함
        var totallist = (burgermn.bgorder + chickenmn.ckorder +
                sidemn.sdorder + beveragemn.bvorder).toMutableList()

        var totalprice: Int = 0
        var orderlist: Int = 0


        for (i in totallist) {
            println("${++orderlist}.${i.name}${i.count}개 ${i.count * i.price}원　")
            totalprice += i.price * i.count
        }
        println("총 가격은 ${totalprice}원 입니다.\n")
        try {
            println("<[0]홈 화면으로> <[1]포장/매장 여부 및 계산하기> <[2]주문표 조정하기>")
            var lastOrder: Int = readLine()!!.toInt()
            when (lastOrder) {
                0 -> break
                1 -> {
                    var whereeat: Int = 0
                    val nowTime = LocalTime.now()
                    val nowYMDTime = LocalDateTime.now()
                    val howShowTime = DateTimeFormatter.ofPattern("HH:mm")
                    val realnowTime = nowTime.format(howShowTime)
                    val howYMDShowTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val realnowYMDTime = nowYMDTime.format(howYMDShowTime)
//                    왜 자꾸 나우, 오브패턴, 포맷에 밑줄이 뜨느냐?
//                    임포트로 안드로이드에 쓰는 Api(O) Annotation을 추가하란 소리다.
//                    하지만 이걸 앱으로 실행할 계획은 없으므로 무시해도 됨!

                    println("<0.처음으로> <1.포장하기> <2.매장 내 식사>")
                    whereeat = readLine()!!.toInt()
                    when (whereeat) {
                        0 -> {
                            println("홈 화면으로 돌아갑니다 \n")
                            break
                        }

                        1 -> {
                            println("포장을 선택하셨습니다.")
                            println("포장은 22시까지만 가능합니다.")
                            if (nowTime.isBefore(LocalTime.of(22, 0))) {
                                if (myMoney >= totalprice) {
                                    myMoney = myMoney - totalprice
                                    runBlocking {
                                        launch {
                                            println("\n결제 중입니다...")
                                            delay(3000)
                                            println("계산이 완료되었습니다. ${realnowYMDTime}")
                                            println("남은 잔액은 ${myMoney}원 입니다.")
                                        }
                                    }
                                    return "OrderComplete"
                                }
                                else {
                                    println("잔액이 ${totalprice-myMoney}원 모자랍니다.")
                                    break
                                }
                            } else {
                                println("포장이 가능한 시간이 지났습니다. 주문에 어려움을 드려 죄송합니다.")
                            }
                        }

                        2 -> {
                            println("매장 내 식사를 선택하셨습니다.")
                            println("식사는 21시까지만 가능합니다.")
                            if (nowTime.isBefore(LocalTime.of(21, 0))) {
                                if (myMoney >= totalprice) {
                                    myMoney = myMoney - totalprice
                                    runBlocking {
                                        launch {
                                            println("\n결제 중입니다...")
                                            delay(3000)
                                            println("계산이 완료되었습니다. ${realnowYMDTime}")
                                            println("남은 잔액은 ${myMoney}원 입니다.")
                                        }
                                    }
                                    return "OrderComplete"
                                }
                                else {
                                    println("잔액이 ${totalprice-myMoney}원 모자랍니다.")
                                    break
                                }
                            } else {
                                println("매장 식사가 가능한 시간이 지났습니다. 주문에 어려움을 드려 죄송합니다.")
                            }
                        }

                        else -> {
                            println("숫자로 입력해주세요.")
                            continue
                        }
                    }
                }
                2 -> {
                    while (true) {
                        print("삭제할 카테고리를 정해주세요. <0.돌아가기> <1.버거> <2.치킨> <3.사이드> <4.음료> : ")
                        var deleteMenu: Int = readLine()!!.toInt()
                        when (deleteMenu) {
                            0 -> {
                                println("[5]주문 및 계산하기로 돌아갑니다.\n")
                                break
                            }

                            1 -> {
                                burgermn.bgorder.clear()
                                println("버거 카테고리의 주문목록이 삭제되었습니다.\n")
                                break
                            }

                            2 -> {
                                chickenmn.ckorder.clear()
                                println("치킨 카테고리의 주문목록이 삭제되었습니다.\n")
                                break
                            }

                            3 -> {
                                sidemn.sdorder.clear()
                                println("사이드 카테고리의 주문목록이 삭제되었습니다.\n")
                                break
                            }

                            4 -> {
                                beveragemn.bvorder.clear()
                                println("음료 카테고리의 주문목록이 삭제되었습니다.\n")
                                break
                            }

                            else -> {
                                println("0~4까지의 숫자만 입력할 수 있어요")
                                continue
                            }
                        }
                    }
//                    else if (deleteMenu in 1 ..  totallist.size){
//                        totallist.removeAt(deleteMenu-1)
//                        println("${deleteMenu}번 메뉴가 삭제되었습니다.")
                    //작동은 되는데 이 작동 이후 다시
                    //최종주문표를 가져오는 과정에서 bg,ck 등의 세부
                    //리스트는 그대로라서 아무 변화가 없음 (변한게 덮어씌워짐)
                    //합쳐서 하지 말고 걍 따로 띄우고 세부 삭제 시키는게 낫나?
                    //그냥 세부 메뉴 삭제 대신 메뉴 카테고리를 날려버리기로 했다.
                    //자세히 파고들면 가능은 할 것 같은데 자신 없음
//                    }
                }
            }
        } catch (e: NumberFormatException) {
            println("숫자로 입력해주세요")
        }

    }
return "orderfail"
}

data class Orderlist(val name: String, val price: Int, val count: Int)

//fun removeFromBgOrderList(index: Int) {
//    if (index in 0 until bgorder.size) {
//        bgorder.removeAt(index)
// 이거는 주문 삭제를 위한 함수
//
//        var deleteIndex: Int = readLine()!!.toInt()
//
//        burgermn.removeFromBgOrderList(deleteIndex - 1)
// 이거는 주문 삭제 함수를 호출하는 경우. 필요시 사용해보자