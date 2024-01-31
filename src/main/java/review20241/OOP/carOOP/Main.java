package review20241.OOP.carOOP;

import review20241.OOP.carOOP.Car;

public class Main {
    public static void main(String[] args) {

        Car myCar = new Car();

        System.out.println(myCar.model);
        System.out.println(myCar.make);
        System.out.println(myCar.color);
        System.out.println(myCar.year);
        System.out.println(myCar.price);

        myCar.drive();
        myCar.brake();

//        ----------------------------------
        Car myCar2 = new Car();
        System.out.println(myCar2.model);
        System.out.println(myCar2.make);
        System.out.println(myCar2.color);
        System.out.println(myCar2.year);
        System.out.println(myCar2.price);

        myCar2.drive();
        myCar2.brake();



    }


}
