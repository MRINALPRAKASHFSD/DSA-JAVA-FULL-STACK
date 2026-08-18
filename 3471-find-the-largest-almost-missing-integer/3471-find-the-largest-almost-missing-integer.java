import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Case 1: k equals the length of the array
        // Every element appears in exactly one subarray (the whole array)
        if (k == n) {
            int max = -1;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }

        // Count the frequency of each element in the array
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int max = -1;

        // Case 2: k is 1
        // An element must be globally unique to appear in exactly one size-1 subarray
        if (k == 1) {
            for (int num : nums) {
                if (counts.get(num) == 1) {
                    max = Math.max(max, num);
                }
            }
            return max;
        }

        // Case 3: 1 < k < n
        // Only the first and last elements can appear in exactly one subarray of size k
        if (counts.get(nums[0]) == 1) {
            max = Math.max(max, nums[0]);
        }
        if (counts.get(nums[n - 1]) == 1) {
            max = Math.max(max, nums[n - 1]);
        }

        return max;
    }
}
