package sample_exam;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Test2 {
    /**
     *  You are given a string S made of N digits that represents a positive integer
     *  Among all positive integer smaller than S, find the one with the maximum possible sum of digits
     *
     *  write a function:
     *      public func solution(_ S: inout String) -> String
     *  that, given a string S, returns a string representing a positive integer smaller than S with the maximum possible sum of digits.
     *  If there are more than one such integer, return any of them. The returned string can only consist of digits and may not contain leading zeros.
     *
     *  Examples:
     *  1. Given S = "899" one of the possible correct answers is "898"
     *  2. Given S = "10", the only possible correct answer is "9"
     *  3. Given S = "98, the only possible correct answer is "89"
     *
     *  Write and efficient algorithm for the following assumptions:
     *     • N is an integer within the range [2..100,000]
     *     • string S is made of only digits (0-9)
     *     • S does not contain leading zero
     *
     */

    public static void main(String[] args) {

        System.out.println(solution("221")); // should output 199
        System.out.println(solution("899")); // should output 898
        System.out.println(solution("10")); // should output 9
        System.out.println(solution("98")); // should output 89
        System.out.println(solution("100000")); // should output 99999
        System.out.println(solution("-899")); // should prompt error
        System.out.println(solution("088")); // should prompt error
        System.out.println(solution("19")); // should output 18

    }

    public static String solution(String numberString) {
        int numberInt = parseInt(numberString);
        if (numberInt <= 0 || numberString.startsWith("-0") || numberString.startsWith("0")) {
            return "Invalid number";
        }

//        map of sum of every number counting down
        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int i = numberInt - 1; i > 0; i--) {
            int sumNow = sumInt(i);
            sumMap.put(i, sumNow);
        }
//        find max value in the map
        int maxValue = Collections.max(sumMap.values());

//        list of keys of found max value
        List<Integer> keysOfMaxValue = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumMap.entrySet()) {
            if (entry.getValue() == maxValue) {
                keysOfMaxValue.add(entry.getKey());
            }
        }

//        get max key in list of key
        return String.valueOf(Collections.max(keysOfMaxValue));
    }

    public static int sumInt(int numberCountdown) {
        int sum = 0;
        while (numberCountdown != 0) {
            int digit = numberCountdown % 10;
            sum += digit;
            numberCountdown /= 10;
        }
        return sum;
    }

}
