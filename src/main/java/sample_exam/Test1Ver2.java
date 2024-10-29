package sample_exam;

public class Test1Ver2 {
        public static void main(String[] args) {

//            System.out.println(smallestSum(16));
//            System.out.println(smallestSum(19));
//            System.out.println(smallestSum(7));
//            System.out.println(smallestSum(30));

            for (int i = 0; i <= 100; i++) {
                System.out.println(smallestSum(i));
            }

        }

        private static long smallestSum(long N) {

            if (N <= 9) {
                return N;
            }

            long remainder = N % 9;
            long numberofNine = N / 9;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberofNine; i++) {
                sb.append('9');
            }

            long finalNum = Long.parseLong(String.valueOf(sb.insert(0, remainder)).replaceFirst("^0+(?!$)", ""));

            return finalNum;
        }
}
