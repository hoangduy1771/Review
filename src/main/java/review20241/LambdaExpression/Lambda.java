package review20241.LambdaExpression;

public class Lambda {
    /**
     * Lambda expression = anonymous method
     * Shorter way to write anonymous classes with only one method
     * <p>
     * In order to use a lambda expression, need to use a
     * functional interface or pre-defined functional interface (which is interface that contain only one abstract method)
     * ex. ActionListener, Runable, (user-defined)
     * <p>
     * Lambda expression can be used in any place where functional interface is required
     * How to use lambda expression:
     * (arguments) -> {statement/s or code} (with "->" is lambda operator)
     */


    public static void main(String[] args) {
        String name = "Sponge";
        char symbol = '!';
        MyInterface myInterface = (x, y) -> {
            System.out.println("Hello " + x);
            System.out.println("It is a nice day" + y);
        };

        MyInterface myInterface2 = (x, y) -> {
            System.out.println("This is another interface functioning, " + x + y);
        };

        myInterface.message(name, symbol);
        myInterface2.message(name, symbol);



    }
}



