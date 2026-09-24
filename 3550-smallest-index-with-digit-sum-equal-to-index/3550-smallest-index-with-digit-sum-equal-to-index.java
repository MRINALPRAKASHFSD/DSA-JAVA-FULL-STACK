class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int temp = nums[i];
            
            // Calculate the sum of the digits
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            
            // If the digit sum matches the index, return it immediately
            if (sum == i) {
                return i;
            }
        }
        
        // Return -1 if no such index is found
        return -1;
    }
}
