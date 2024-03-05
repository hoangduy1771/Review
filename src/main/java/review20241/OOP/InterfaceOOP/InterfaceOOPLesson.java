package review20241.OOP.InterfaceOOP;

public class InterfaceOOPLesson {
    /**
     *  Interface = template that can be applied to a class
     *  Similar to inheritance, but it specifies what a class must do
     *
     *  Difference between inheritance and interface
     *  Classes can apply more than one interface, inheritance is limited to 1 super class
     */

    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        rabbit.flee();

        Hawk hawk = new Hawk();
        hawk.hunt();

        Fish fish = new Fish();
        fish.flee();
        fish.hunt();


    }



}
