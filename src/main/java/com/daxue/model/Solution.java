package com.daxue.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Admin
 * @date 2020/03/10
 **/
public class Solution {
    public static void main(String[] args) {
//        int[] nums = {1,15,23,16};
//        int[] ints = new Solution().twoSum(nums, 16);
//        System.out.println(ints[0] + " " + ints[1]);

    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        lists.add(list);
                        break;
                    }
                }
            }
        }
        Iterator<List<Integer>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            List<Integer> list = iterator.next();
            /**
             * [
             *   {0, -1, 1}
             *   {0, -1, 1}
             *   {2, -2, 0}
             *   {2, -2, 0}
             *   {3, -3, 0}
             *
             * ]
             */




        }

        for (int i=0; i<lists.size();i++) {

            for (int j=i+1; i<lists.size();j++) {
                lists.get(i);
                lists.get(j);
                if (lists.get(i).get(0) == lists.get(j).get(0) && lists.get(i).get(1) == lists.get(j).get(1) && lists.get(i).get(2) == lists.get(j).get(2)) {
                    System.out.println();
                }


            }


        }
        return lists;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = {0, 0};
        System.out.println(result.length);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
