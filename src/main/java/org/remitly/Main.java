package org.remitly;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Provide file path of the JSON");
            return;
        }

        JSONParser parser = new JSONParser();

        boolean result = parser.validateFromPath(args[0]);
        System.out.println(result);
    }
}