package org.example.motivation.controller;

import org.example.Container;
import org.example.motivation.entity.Motivation;

import java.util.*;

public class MotivationController {

    int lastId = 0; // 몇 번까지 썼더라?
    List<Motivation> motivations = new ArrayList<>(); // motivation 저장// 소

    public void add() {

        int id = lastId + 1;
        System.out.print("body : ");
        String body = Container.getScanner().nextLine();
        System.out.print("source : ");
        String source = Container.getScanner().nextLine();

        Motivation motivation = new Motivation(id, body, source);

        motivations.add(motivation);

        System.out.printf("%d번 motivation이 등록됨\n", id);
        lastId++;
    }

    public void list() {
        if (motivations.size() == 0) {
            System.out.println("등록된 moti 없어");
            return;
        }

        System.out.println("=".repeat(40));
        System.out.printf("   id    /     source      /      body        \n");

        for (int i = motivations.size() - 1; i >= 0; i--) {
            Motivation motivation = motivations.get(i);

            if (motivation.getSource().length() > 7) {
                System.out.printf("   %d    /     %s     /      %s        \n", motivation.getId(), motivation.getSource().substring(0, 5) + "...", motivation.getBody());
                continue;
            }
            System.out.printf("   %d    /     %s        /      %s        \n", motivation.getId(), motivation.getSource(), motivation.getBody());
        }

        System.out.println("=".repeat(40));
    }

    public void delete(String cmd) {
        int id = Integer.parseInt(cmd.split(" ")[1]);

        Motivation foundMotivation = null;
        int foundIndex = -1;

        for (int i = 0; i < motivations.size(); i++) {
            Motivation motivation = motivations.get(i);
            if (motivation.getId() == id) {
                foundMotivation = motivation;
                foundIndex = i;
                break;
            }
        }

        if (foundMotivation == null) {
            System.out.println("해당 moti는 없던데????");
            return;
        }

        motivations.remove(foundIndex);
        System.out.println(id + "번 moti 삭제됨");
    }


    public void newDelete(String cmd) {
        Rq rq = new Rq(cmd);

        System.out.println("rq.getParams(\"id\") : " + rq.getParams("id"));

        int id = Integer.parseInt(rq.getParams("id"));

        Motivation foundMotivation = null;

        for (Motivation motivation : motivations) {
            if (motivation.getId() == id) {
                foundMotivation = motivation;
                break;
            }
        }

        if (foundMotivation == null) {
            System.out.println("해당 moti는 없던데????");
            return;
        }

        motivations.remove(foundMotivation);
        System.out.println(id + "번 moti 삭제됨");


    }

    public void edit(String cmd) {
        int id;
        try {
            id = Integer.parseInt(cmd.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("명령어 확인해라");
            return;
        }

        Motivation foundMotivation = findById(id);

        if (foundMotivation == null) {
            System.out.println("해당 moti는 없던데????");
            return;
        }

        // 찾은 motivation의 인스턴스 변수에 접근
        System.out.println("body(기존) : " + foundMotivation.getBody());
        System.out.println("source(기존) : " + foundMotivation.getSource());

        String newBody;
        String newSource;
        // 수정사항 입력받기
        while (true) {
            System.out.print("new body : ");
            newBody = Container.getScanner().nextLine().trim();

            if (newBody.length() != 0) {
                break;
            }
            System.out.println("수정사항(body) 입력해");
        }

        while (true) {
            System.out.print("new source : ");
            newSource = Container.getScanner().nextLine();

            if (newSource.length() != 0) {
                break;
            }
            System.out.println("수정사항(source) 입력해");
        }

        // 찾은 motivation의 인스턴스 변수 값 수정
        foundMotivation.setBody(newBody);
        foundMotivation.setSource(newSource);

        System.out.println(id + "번 moti 수정됨");

    }

    // 명령어의 id 와 일치하는 motivation 찾기
    private Motivation findById(int id) {
        for (Motivation motivation : motivations) {
            if (motivation.getId() == id) {
                return motivation;
            }
        }
        return null;
    }
}