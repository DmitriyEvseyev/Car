package controller;

public class IDGenerator {
    private static IDGenerator instance;

    private int count;

    public static IDGenerator getInstance(int initValue) {
        if(instance == null) {
            instance = new IDGenerator(initValue);
        }

        return instance;
    }

    private IDGenerator(int count) {
        this.count = count;
    }

    public int getId() {
        return count++;
    }
}

