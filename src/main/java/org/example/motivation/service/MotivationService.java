package org.example.motivation.service;

import org.example.Container;
import org.example.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;

public class MotivationService {

    List<Motivation> motivations;
    int lastId;

    public MotivationService() {
        motivations = new ArrayList<>();// motivation 저장소
        lastId = 0; // 몇 번까지 썼더라?
    }

    public int add(String body, String source) {
        int id = lastId + 1;
        Motivation motivation = new Motivation(id, body, source);
        motivations.add(motivation);

        lastId = id; // 방금 만들어진 새 moti의 id로 갱신

        return id;
    }

    public void showList() {
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

    public int getIndexById(int id) {
        int foundIndex = -1;
        for (int i = 0; i < motivations.size(); i++) {
            Motivation motivation = motivations.get(i);
            if (motivation.getId() == id) {
                foundIndex = i;
                break;
            }
        }

        return foundIndex;
    }

    public void doDelete(int id) {
        int foundIndex = getIndexById(id);
        motivations.remove(foundIndex);
    }

    // 명령어의 id 와 일치하는 motivation 찾기
    public Motivation findById(int id) {
        for (Motivation motivation : motivations) {
            if (motivation.getId() == id) {
                return motivation;
            }
        }
        return null;
    }

    public void doEdit(Motivation foundMotivation) {
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
    }
}