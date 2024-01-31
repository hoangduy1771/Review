package review20241;

public class SwapVariable {

    public static void main(String[] args) {
        String a = "water";
        String b = "pepsi";

        String temp;
        temp = a;
        a = b;
        b = temp;

        System.out.println("a: " + a);
        System.out.println("b: " + b);

    }

}
