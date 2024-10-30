package Hw5;

/*
Complete the following Leetcode Easy question: https://leetcode.com/problems/missing-number/
If necessary, I can use the Arrays.copyOf method which runs in linear time.
(Assume the entire java.util package has been imported.)

My solution should run in O(n log n) time.
Optionally, make your solution run in O(n) time, using only a constant amount of memory (aside from the memory
needed for the input array, of course). Hint: remember the formula 1 + 2 + 3 + ... + n = (n*(n+1))/2.

Given an array called nums and a range [0, n]. Find the missing number in the array.
Note:
n == nums.length
1 <= n <= 10,000
All the numbers of nums are unique.
 */

import java.util.Arrays;

public class MissingNumberLC {
    public static void main(String[] args) {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1}; // [0, 1, 2, 3, 4, 5, 6, 7, 9] Missing: 8
        // nums = {9, 6, 4, 2, 3, 5, 7, 0, 1}; // [0, 1, 2, 3, 4, 5, 6, 7, 9] Missing: 8
        // nums = {3, 0, 1}; // [0, 1, 3] Missing: 2
        // nums = {0, 1}; // [0, 1, 2] Missing: 2
        // nums = {1,2}; // [0, 1, 2] Missing: 0
        // nums = {1}; // [0, 1] Missing: 0
        // nums = {0}; // [0, 1] Missing: 1
        System.out.println("Length: " + nums.length);
        System.out.println(missingNumber(nums));
    }

    // Codelab Accepted Answer (Doesn't allow modification of nums, sorting not allowed here, use CopyOf):
    // O(n) + O(n log n) + O(n) = O(n log n)
    public static int missingNumber(int[] nums) {
        int endRange = nums.length;
        int missingNum = endRange;
        int[] arrCopy = Arrays.copyOf(nums, nums.length); // O(n)

        /*for(int e : arrCopy){
            System.out.println("copy " + e + " ");
        }*/

        // Sort the array using Arrays.sort() -> O(n log n)
        Arrays.sort(arrCopy); // O(n log n)
        /*for(int e : arrCopy){
            System.out.println("SortedCopy " + e + " ");
        }*/

        //  0  1 -> Length 2
        // [0, 1] -> Missing 2

        // Search for missing number by comparing the indices (accurate set of numbers) and the elements in the array (missing a number)

        for(int i = 0; i < endRange; i++){ // O(n)
            // Checks if elements at the front and middle of array matches up with index.
            if(arrCopy[i] != i){ // if element and index don't match, return the correct number that is supposed to be there (index)
                missingNum = i;
                return missingNum;
            }
            // Checks if elements at the end of array matches up with index.
            if(arrCopy.length != arrCopy[arrCopy.length - 1]){ // check if the last element of array is equal to the array length.
                missingNum = arrCopy.length; // if they aren't equal to one another than that means the last element is missing.
                // Look at [0, 1] testcase. It is missing a 2.
            }
        }

        return missingNum;
    }

    // Leetcode Accepted Answer (allows modification of nums, sorting allowed here):
    // 0(n log n) + O(n) = O(n log n)
    /*
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int missingNum = n;

        // Sort the array using Arrays.sort() -> O(n log n)
        Arrays.sort(nums); // O(n log n)

        //  0  1 -> Length 2
        // [0, 1] -> Missing 2

        // Search for missing number by comparing the indices (accurate set of numbers) and the elements in the array (missing a number)

        for(int i = 0; i < n; i++){ // O(n)
            // Checks if elements at the front and middle of array matches up with index.
            if(nums[i] != i){ // if element and index don't match, return the correct number that is supposed to be there (index)
                missingNum = i;
                return missingNum;
            }
            // Checks if elements at the end of array matches up with index.
            if(nums.length != nums[nums.length - 1]){ // check if the last element of array is equal to the array length.
                missingNum = nums.length; // if they aren't equal to one another than that means the last element is missing.
                // Look at [0, 1] testcase. It is missing a 2.
            }
        }

        return missingNum;
    }*/
}

