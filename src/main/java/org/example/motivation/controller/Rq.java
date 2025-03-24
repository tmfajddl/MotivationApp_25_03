package org.example.motivation.controller;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getParams(String paramName) {
        return params.get(paramName);
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    String cmd;
    private Map<String, String> params;

    // request
    public Rq(String cmd) {
        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);

        String actionMethod = cmdBits[0]; // delete

        params = new HashMap<>();

        String[] paramBits;

        try {
            paramBits = cmdBits[1].split("=");
        } catch (Exception e) {
            System.out.println("명령어 예외 상황 발생");
            return;
        }

        System.out.println(actionMethod);
        System.out.println(paramBits[0]);
        System.out.println(paramBits[1]);

        String key = paramBits[0];
        String value = paramBits[1];

        System.out.println(" key : " + key + " value : " + value);
        params.put(key, value);
    }
}