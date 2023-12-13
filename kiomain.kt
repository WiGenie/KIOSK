package com.example.KIOSK

import java.lang.NumberFormatException

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
    while (true) {


        println("<[1]버거> <[2]치킨> <[3]사이드> <[4]음료> <[5]주문 및 계산하기> <[6]종료>")
        try {
            menubtn = readLine()!!.toInt()
            when (menubtn) {
                1 -> {
                    burgermn.makeBgOrderList()
                }

                2 -> {
                    chickenmn.makeCkOrderList()
                }
                3 -> {
                    sidemn.makeSdOrderList()
                }
                4 -> {
                    beveragemn.makeBvOrderList()
                }
                5 -> {
                    letOrder(burgermn,chickenmn,sidemn,beveragemn)
                }

                6 -> {
                    println("프로그램을 종료합니다.")
                    break
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            println("숫자로 입력해주세요.")
        }

    }

}
//@supressLint:"SuspiciousIndentation" 이런식으로 나온거 걍 줄이나 띄우기 구분
//하라는거니까 걍 ctrl+alt+L ㄱㄱ(비주얼 스튜디오의 ctrl alt f랑 같은 기능)
fun letOrder(burgermn: Burgermn, chickenmn: Chickenmn,
             sidemn: Sidemn, beveragemn: Beveragemn) {



     //제공 방식을 먼저 물어보고 계산표 출력 및 계산하기
    var whereeat: Int = 0
    while (true) {
//        println("<0.처음으로> <1.포장하기> <2.매장 내 식사>")
//        whereeat = readLine()!!.toInt()
//        when (whereeat) {
//            0 -> {
//                println("홈 화면으로 돌아갑니다 \n")
//                break
//            }
//
//            1 -> {
//                println("포장을 선택하셨습니다.")
//            }
//
//            2 -> {
//                println("매장 내 식사를 선택하셨습니다.")
//            }
//
//            else -> {
//                println("숫자로 입력해주세요.")
//                continue
//            }
//        }

        // 리스트들을 합칠 때 얘네들이 mutableListOf라고 해서
        // totallist도 변수 속성을 따라가는게 아님 (아마 Any같은걸로 지정될 듯)
        // 그래서 괄호로 각 메뉴 목록들을 묶고 toMutableList()로 변환해야 함
        var totallist= (burgermn.bgorder + chickenmn.ckorder +
                sidemn.sdorder + beveragemn.bvorder).toMutableList()

        var totalprice: Int = 0
        var orderlist:Int=0

        for (i in totallist) {
            println("${++orderlist}.${i.name}　${i.price}원　${i.count}개")
            totalprice += i.price * i.count
        }
        println("총 가격은 ${totalprice}원 입니다.\n")
        try {
            println("<[1]포장/매장 여부 및 계산하기> <[2]메뉴 삭제하기>")
            var lastOrder:Int= readLine()!!.toInt()
            when(lastOrder) {
                1 -> break //포장 매장 여부 물어보고 포장은 22시 매장은 21시에
                // 마감하기로 하자, 메인 함수 최초 실행 때 얼마 있는지 정하고
                // 요구하는 대기손님이랑 소지금이랑 이케저케 어쩌구저쩌구
                2 -> {
                    print("(0 입력시 돌아가기)주문 목록을 확인하시고 삭제할 메뉴의 번호를 입력해주세요:")
                    var deleteMenu:Int= readLine()!!.toInt()
                    if (deleteMenu == 0){
                        println("<[5]주문 및 계산하기> 로 돌아갑니다.")
                        continue
                    }
                    else if (deleteMenu in 1 ..  totallist.size){
                        totallist.removeAt(deleteMenu-1)
                        println("${deleteMenu}번 메뉴가 삭제되었습니다.")
                        //작동은 되는데 이 작동 이후 다시
                        //최종주문표를 가져오는 과정에서 bg,ck 등의 세부
                        //리스트는 그대로라서 아무 변화가 없음 (변한게 덮어씌워짐)
                        //합쳐서 하지 말고 걍 따로 띄우고 세부 삭제 시키는게 낫나?
                    }
                }
            }
        }
        catch (e: NumberFormatException)
        {
            println("숫자로 입력해주세요")
        }

    }

}

data class Orderlist(val name: String, val price: Int, val count: Int)

//fun removeFromBgOrderList(index: Int) {
//    if (index in 0 until bgorder.size) {
//        bgorder.removeAt(index)
// 이거는 주문 삭제를 위한 함수
//
//        var deleteIndex: Int = readLine()!!.toInt()
//
//        // 사용자로부터 입력 받은 인덱스를 사용하여 주문 삭제 함수 호출
//        burgermn.removeFromBgOrderList(deleteIndex - 1)
// 이거는 주문 삭제 함수를 호출하는 경우. 다듬어보자