package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==motivation 실행=="); //실행시작
        Scanner sc = new Scanner(System.in);
        APP app = new APP(); //APP클래스인 app생성
        //"종료"가 들어오기 전까지 무한반복
        while (true) {
            System.out.print("명령어를 입력하시오 :");//명령어 입력
            String word = sc.nextLine().trim(); // 명령어의쓸데 없는 공백 제거
            //명령어가 "종료"라면
            if (word.equals("종료"))
                break; //무한루프 탈출
            // 명령어가 "등록"이면
            else if (word.equals("등록")) {
                System.out.print("source :");
                String source = sc.nextLine();
                System.out.print("motivation :"); // motivation과 source 각각 입력
                String motivation = sc.nextLine();
                app.등록(motivation, source); //app의 등록 메서드 실행
            //명령어가 "목록"이면
            } else if (word.equals("목록")) {
                System.out.println("==================================================");
                System.out.println(" 번호 /         source         /      motivation ");
                //등록된 것이 하나라도 존재한다면 목록 리스팅
                if (app.내용들.size() > 0) {
                    // 목록은 최근것부터 나중것까지 네용들의 배열만큼
                    for (int i = app.내용들.size() - 1; i >= 0; i--)
                        app.목록(i); //목록 메서드 실행
                // 만약 등록된것이 없다면 실행
                } else
                    System.out.println("등록된 내용이 없습니다.");

                System.out.println("==================================================");
            //명령어가 "삭제"이면
            } else if (word.equals("삭제")) {
                //번호에 맞는 타입이 들어올때까지 반복
                while(true) {
                    System.out.print("삭제할 motivation의 번호를 입력하시오: "); //삭제할 번호 입력
                    try {
                        app.삭제(sc.nextInt()); //삭제 메서드 실행
                        sc.nextLine(); // 버퍼 비워줌
                        break; //반복문 탈출
                    //번호가 아닌 문자가 들어온다면
                    } catch (Exception e) {
                        sc.nextLine(); //버퍼 비워줌
                        System.out.println("올바른 번호가 아닙니다."); //다시 try로 돌아감
                    }
                }
             //명령어가 "수정"이라면
            } else if (word.equals("수정")) {
                int num;
                //번호가 들어올때까지 계속 반복
                while(true){
                    System.out.print("수정할 motivation의 번호를 입력하시오: ");
                    try{
                        num = sc.nextInt(); //번호이면 값대입
                        sc.nextLine(); //버퍼 비우기
                        break; //반복문 탈출
                    // 만약 번호가 아니라면?
                    }catch (Exception e){
                        sc.nextLine(); //버퍼 비우기
                        System.out.println("올바른 번호가 아닙니다."); //다시 try로 돌아감
                    }
                }
                //수정할 내용 각각 입력 받음
                System.out.print("수정할 source 내용을 입력하시오: ");
                String source2 = sc.next();
                System.out.print("수정할 motivation 내용을 입력하시오: ");
                String motivation2 = sc.next();
                app.수정(num, source2, motivation2); //수정 메서드 실행
                sc.nextLine();
             //명령어가 등록 된 것이 아니라면
            } else
                System.out.println("올바른 명령어가 아닙니다.");

        }
        System.out.println("==motivation 종료=="); //시스템 종료
    }
}

class APP {
    List<내용> 내용들 = new ArrayList<>(); //각 등록된 내용들이 저장된 arraylist
    int ID = 1; //각각의 내용들에 부여될 고유 번호

    void 등록(String motivation, String source) {
        내용 a내용 = new 내용(); //새로운 객체 생성
        a내용.motivation = motivation; //객체의 변수에 각각 대입
        a내용.source = source;
        a내용.ID = ID;

        내용들.add(a내용); //arraylist에 객체 추가
        System.out.printf("%d번 motivation이 등록되었습니다.\n", ID); //등록 완료
        ID++; //고유 번호 증가
    }

    void 목록(int id) {
        if(내용들.get(id).source.length() > 7 || 내용들.get(id).motivation.length() > 4)
            System.out.printf("  %d   /       %s       /      %s\n", 내용들.get(id).ID, 내용들.get(id).source.substring(0,4)+ "...", 내용들.get(id).motivation.substring(0,5)+ "...");
        else
            System.out.printf("  %d   /          %s          /      %s\n", 내용들.get(id).ID, 내용들.get(id).source, 내용들.get(id).motivation);
    }

    void 수정(int id, String source2, String motivation2) {
        for (int i = 0; i < 내용들.size(); i++) {//입력된  id가 일치하는 고유 ID를 가지는지 확인
            //만약 일치하는 아이디가 있는 경우 각각의 내용 수정
            if (id == 내용들.get(i).ID) {
                내용들.get(i).ID = id;
                내용들.get(i).motivation = motivation2;
                내용들.get(i).source = source2;
                System.out.printf("%d번 motivation이 수정되었습니다.\n", id);
                return; // 수정 메서드 실행 종료
            }
        }
        //반복문을 다 돌렸는데도 일치하는 ID가 없다면 출력
        System.out.printf("%d번 motivation은 존재하지 않습니다.\n", id);
    }

    void 삭제(int id) {
        for (int i = 0; i < 내용들.size(); i++) {
            // 만약 일치하는 아이디가 있는 경우 해당 motivation 삭제
            if (id == 내용들.get(i).ID) {
                내용들.remove(i);
                System.out.printf("%d번 motivation이 삭제되었습니다.\n", id);
                return;
            }
        }
        //반복문을 다 돌렸는데도 일치하는 ID가 없다면 출력
        System.out.printf("%d번 motivation은 존재하지 않습니다.\n", id);
    }
}

class 내용 {
    int ID = 0;
    String motivation = null;
    String source = null;
}