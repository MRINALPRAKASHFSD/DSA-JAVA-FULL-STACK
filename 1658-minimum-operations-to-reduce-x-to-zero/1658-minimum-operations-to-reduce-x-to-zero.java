class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // We want to find the longest subarray that sums to (totalSum - x)
        int target = totalSum - x;
        
        // If the total sum is exactly x, we need to remove all elements
        if (target == 0) {
            return nums.length;
        }
        // If the total sum is less than x, it's impossible
        if (target < 0) {
            return -1;
        }
        
        int left = 0;
        int currentSum = 0;
        int maxLength = -1;
        
        // Sliding window to find the longest subarray summing to 'target'
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            // Shrink the window from the left if the sum exceeds the target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }
            
            // If we found a valid subarray, update the maximum length
            if (currentSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        
        // The minimum operations will be the total length minus the longest subarray length
        return maxLength != -1 ? nums.length - maxLength : -1;
    }
}
