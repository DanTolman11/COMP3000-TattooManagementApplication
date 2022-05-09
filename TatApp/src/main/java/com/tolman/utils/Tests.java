package com.tolman.utils;

public class Tests {
    public static void main(String[] args) {
        ConfigurationManager configman = ConfigurationManager.getInstance();
        statictest A = new statictest(5, 3);
        statictest B = new statictest(10,6);
        System.out.println(A);
        System.out.println(B);
    }
    public static class statictest {
        static int x;
        int y;

        public statictest(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "statictest{" +
                    "y=" + y + "\n x=" + x +
                    '}';
        }
    }
}
