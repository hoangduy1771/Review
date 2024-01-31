package review20241;

import java.util.Random;

public class RandomValues {

    public static void main(String[] args) {
        Random random = new Random();
//        int x = random.nextInt();

//        bound between 0-5
        int x = random.nextInt(6);
        System.out.println(x);

//        bound between 0-1
        double y = random.nextDouble();
        System.out.println(y);

        boolean z = random.nextBoolean();
        




    }



}
