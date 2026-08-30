class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        
        // If the array has 1 or 2 elements, all must be removed.
        if (n <= 2) {
            return n;
        }

        int minIndex = 0;
        int maxIndex = 0;

        // Find the indices of the minimum and maximum elements
        for (int k = 1; k < n; k++) {
            if (nums[k] < nums[minIndex]) {
                minIndex = k;
            }
            if (nums[k] > nums[maxIndex]) {
                maxIndex = k;
            }
        }

        // Determine which index comes first
        int i = Math.min(minIndex, maxIndex);
        int j = Math.max(minIndex, maxIndex);

        // Calculate the three possible deletion strategies
        int bothFromFront = j + 1;
        int bothFromBack = n - i;
        int frontAndBack = (i + 1) + (n - j);

        // Return the most optimal strategy
        return Math.min(Math.min(bothFromFront, bothFromBack), frontAndBack);
    }
}
