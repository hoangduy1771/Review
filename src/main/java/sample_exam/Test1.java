package sample_exam;

public class Test1 {

    /**
     * write a function "solution" that, given the integer N, returns the smallest non-negative integer whose individual digits sum to N
     *
     *  Examples:
     *  1. given N = 16, the function should return 79. There are many numbers whose digits
     *  sum to 16 (for example: 79, 97, 808, 5551, 22822, etc.). The smallest such number is 79.
     *
     *  2. Given N = 19, the function should return 199 (the sum of digits is 1+9+9 = 19)
     *  3. Given N = 7, the function should return 7.
     *
     */

    public static void main(String[] args) {

        System.out.println(solutionSmallestSum(16));
        System.out.println(solutionSmallestSum(19));
        System.out.println(solutionSmallestSum(7));
        System.out.println(solutionSmallestSum(51));
        System.out.println(solutionSmallestSum(100));

    }

    public static int solutionSmallestSum(int sumNum) {

        if (sumNum <= 9) {
            return sumNum;
        }

        for (int num = 1; num <= Integer.MAX_VALUE; num++) {
            int sum = sumOfDigit(num);

            if (sum == sumNum) {
                return num;
            }

            if (sum > sumNum) {
                break;
            }
        }
        return -1; // this should not happen unless sumNum is negative
    }

    private static int sumOfDigit(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

}

















