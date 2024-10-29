package sample_exam;

public class Test2Ver2 {
    public static void main(String[] args) {
//        System.out.println(solution("221")); // should output 199
        System.out.println(solution("899")); // should output 898
        System.out.println(solution("10")); // should output 9
        System.out.println(solution("98")); // should output 89
        System.out.println(solution("100000")); // should output 99999
//        System.out.println(solution("-899")); // should prompt error
//        System.out.println(solution("088")); // should prompt error
        System.out.println(solution("19")); // should output 18


    }

    private static String solution(String S) {
        int length = S.length(); // 898
        int first = Integer.parseInt(S.substring(0, 1)) - 1; //
        String result = "9".repeat(length - 1);
        if (first != 0) {
            result = first + result;
        }
        return result;
    }
}
