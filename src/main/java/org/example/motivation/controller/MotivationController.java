package org.example.motivation.controller;

import org.example.Container;
import org.example.motivation.entity.Motivation;
import org.example.motivation.service.MotivationService;

import java.util.*;

public class MotivationController {

    private MotivationService motivationService;


    public MotivationController() {
        motivationService = new MotivationService();
    }

    public void add() {

//        int id = lastId + 1;
        System.out.print("body : ");
        String body = Container.getScanner().nextLine();
        System.out.print("source : ");
        String source = Container.getScanner().nextLine();

//        Motivation motivation = new Motivation(id, body, source);
//        motivations.add(motivation);
        int id = motivationService.add(body, source);

        System.out.printf("%d번 motivation이 등록됨\n", id);

    }

    public void list() {
        motivationService.showList();
    }

    public void delete(String cmd) {
        int id = Integer.parseInt(cmd.split(" ")[1]);

        int foundIndex = motivationService.getIndexById(id);

        if (foundIndex == -1) {
            System.out.println("해당 moti는 없던데????");
            return;
        }

        motivationService.doDelete(id);

        System.out.println(id + "번 moti 삭제됨");
    }


    public void newDelete(String cmd) {
//        Rq rq = new Rq(cmd);
//
//        System.out.println("rq.getParams(\"id\") : " + rq.getParams("id"));
//
//        int id = Integer.parseInt(rq.getParams("id"));
//
//        Motivation foundMotivation = null;
//
//        for (Motivation motivation : motivations) {
//            if (motivation.getId() == id) {
//                foundMotivation = motivation;
//                break;
//            }
//        }
//
//        if (foundMotivation == null) {
//            System.out.println("해당 moti는 없던데????");
//            return;
//        }
//
//        motivations.remove(foundMotivation);
//        System.out.println(id + "번 moti 삭제됨");
    }

    public void edit(String cmd) {
        int id;
        try {
            id = Integer.parseInt(cmd.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("명령어 확인해라");
            return;
        }

        Motivation foundMotivation = motivationService.findById(id);

        if (foundMotivation == null) {
            System.out.println("해당 moti는 없던데????");
            return;
        }

        motivationService.doEdit(foundMotivation);

        System.out.println(id + "번 moti 수정됨");
    }
}