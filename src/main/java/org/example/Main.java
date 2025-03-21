package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        System.out.println("==motivation 실행==");
        Scanner sc = new Scanner(System.in);
        APP app = new APP();
        while (true) {
            System.out.print("명령어를 입력하시오 :");
            String word = sc.nextLine().trim();
            if (word.equals("등록")) {
                System.out.print("motivation :");
                String motivation = sc.nextLine();
                System.out.print("source :");
                String source = sc.nextLine();
                app.등록(motivation, source);
            } else if (word.equals("목록")) {
                System.out.println("===============================");
                System.out.println(" 번호  /      source      /      motivation      ");
                if (app.내용들.size() > 0) {
                    for (int i = app.내용들.size() - 1; i >= 0; i--)
                        app.목록(i);
                } else
                    System.out.println("등록된 내용이 없습니다.");
                System.out.println("===============================");
            } else if (word.equals("삭제")) {
                while(true){
                    System.out.print("삭제할 motivation의 번호를 입력하시오: ");
                    try{
                        app.삭제(sc.nextInt());
                        sc.nextLine();
                        break;
                    }catch (Exception e){
                        sc.nextLine();
                        System.out.println("올바른 번호가 아닙니다.");
                    }
                }
            } else if (word.equals("수정")) {
                int num;
                while(true){
                    System.out.print("수정할 motivation의 번호를 입력하시오: ");
                    try{
                        num = sc.nextInt();
                        sc.nextLine();
                        break;
                    }catch (Exception e){
                        sc.nextLine();
                        System.out.println("올바른 번호가 아닙니다.");
                    }
                }
                System.out.print("수정할 source 내용을 입력하시오: ");
                String source2 = sc.next();
                System.out.print("수정할 motivation 내용을 입력하시오: ");
                String motivation2 = sc.next();
                app.수정(num, source2, motivation2);
                sc.nextLine();

            } else if (word.equals("종료"))
                break;
            else
                System.out.println("올바른 명령어가 아닙니다.");

        }
        System.out.println("==motivation 종료==");
    }
}

class APP {
    List<내용> 내용들 = new ArrayList<>();
    int ID = 1;

    void 등록(String motivation, String source) {
        내용 a내용 = new 내용();
        a내용.motivation = motivation;
        a내용.source = source;
        a내용.ID = ID;
        내용들.add(a내용);
        System.out.printf("%d번 motivation이 등록되었습니다.\n", ID);
        ID++;
    }

    void 목록(int id) {
        System.out.printf(" %d  /     %s     /      %s     \n", 내용들.get(id).ID, 내용들.get(id).source, 내용들.get(id).motivation);
    }

    void 수정(int id, String source2, String motivation2) {
        for (int i = 0; i < 내용들.size(); i++) {
            if (id == 내용들.get(i).ID) {
                내용들.get(i).ID = id;
                내용들.get(i).motivation = motivation2;
                내용들.get(i).source = source2;
                System.out.printf("%d번 motivation이 수정되었습니다.\n", id);
                return;
            }
        }
        System.out.printf("%d번 motivation은 존재하지 않습니다.\n", id);
    }

    void 삭제(int id) {
        for (int i = 0; i < 내용들.size(); i++) {
            if (id == 내용들.get(i).ID) {
                내용들.remove(i);
                System.out.printf("%d번 motivation이 삭제되었습니다.\n", id);
                return;
            }
        }
        System.out.printf("%d번 motivation은 존재하지 않습니다.\n", id);
    }
}

class 내용 {
    int ID = 0;
    String motivation = null;
    String source = null;
}