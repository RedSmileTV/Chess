package main;

public class Main {
    public static void main(String[] args) {
        new GUI();
    }


    public static char intToChar(int x) {
        char[] mapping = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        if (x >= 0 && x <= 7) {
            return mapping[x];
        }
        else return 'i';
    }

    public static int updateX(int x) {
        return x--;
    }
    public static int updateY(int y) {
        return (y - 8) * -1;
    }
}