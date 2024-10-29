package sample_exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test3 {
    /**
     *  In order to finish a game, a player has to complete N missions. The missions
     *  are numbered from 0 to N-1. The K-th mission has an integer D[K] assigned,
     *  representing its difficulty level.
     *
     *  During a day, you can perform any number of missions given the two following rules:
     *      • missions should be performed in the specified order, in other words, a mission can be undertaken only if
     *      all of the missions preceding it have already been completed.
     *
     *      • the difference between the difficulty levels of any two missions
     *      performed on the same day should not be greater than an integer X
     *
     *  Write a function:
     *      class Solution {public int solution(int[] D, int X); }
     *
     *      that, given an array D if N integers and an integer X
     *      returns the minimum number of days required to complete all of the missions in the game
     *
     *      Examples:
     *      1. Given D = [5, 8, 2, 7] and X = 3, your function should return 3.
     *      the first 2 mission can be performed on the first day, the third mission on the second day
     *      and the last mission on the third day. It is not possible to complete all of the mission in fewer days
     *
     *      2. Given D = [2, 5, 9, 2, 1, 4] and X = 4, your function should return 3. the first two missions
     *      can be performed on the first day, the third mission on the second day and all of the remaining
     *      missions on the third day. Note that it is possible the first mission on the first day and the next two...
     *
     */



    public static void main(String[] args) {
//        int[] tasks1 = {5, 8, 2, 7}; // 3
//        int diff1 = 3;
//
//        int[] tasks2 = {2, 5, 9, 2, 1, 4}; // 3
//        int diff2 = 4;
//
//        int[] tasks3 = {5, 8, 2, 7, 6, 3, 5, 8, 4}; // 4
//        int diff3 = 3;

//        int[] tasks4 = {1,2,3,4,5,6}; // 2
//        int diff4 = 2;

        int[] tasks5 = {1,2,3,4,5,6,7,8,9}; //3
        int diff5 = 2;


//        System.out.println(solution(tasks1, diff1));
//        System.out.println(solution(tasks2, diff2));
//        System.out.println(solution(tasks3, diff3));
//        System.out.println(solution(tasks4, diff4));
        System.out.println(solution(tasks5, diff5));

    }

    private static int solution(int[] tasks, int hardDiff) {
        List<Integer> taskArrayList = Arrays.stream(tasks).boxed().toList();
        List<Integer> temp = new ArrayList<>();
        List<Integer> tempUpdate = new ArrayList<>();
        int days = 1;

        for (int i = 0; i < taskArrayList.size(); i++) {
            if (i == 0) {
                temp.add(taskArrayList.get(i));
                tempUpdate.add(taskArrayList.get(i));
            } else {
                for (int j : temp) {
                    int compare = Math.abs(taskArrayList.get(i) - j);
                    if (compare <= hardDiff) {
                        tempUpdate.add(taskArrayList.get(i));
                    } else {
                        days++;
                        temp.clear();
                        tempUpdate.clear();
                        break;
                    }
                }
                temp.clear();
                temp.addAll(tempUpdate);
            }
        }
        return days;
    }


}
























