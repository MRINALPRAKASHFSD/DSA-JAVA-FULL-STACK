class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best_till = new int[n];
        int sum = 0;
        int left = 0;
        int min_so_far = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink the window from the left if the sum exceeds the target
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }
            
            if (sum == target) {
                int current_len = right - left + 1;
                
                // Check if we have found a valid sub-array before the current one
                if (left > 0 && best_till[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, current_len + best_till[left - 1]);
                }
                
                // Update the minimum length found so far
                min_so_far = Math.min(min_so_far, current_len);
            }
            
            // Store the minimum length found up to the current index
            best_till[right] = min_so_far;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
