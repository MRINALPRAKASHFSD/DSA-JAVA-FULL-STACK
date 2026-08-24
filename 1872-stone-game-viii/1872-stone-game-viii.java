class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Calculate prefix sums in-place to save memory
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Step 2: Evaluate the max difference starting from the last possible move
        // The last player is forced to take all remaining stones, 
        // yielding the total sum of the array (stones[n-1]).
        int res = stones[n - 1];
        
        // Iterate backwards. We stop at i = 1 because x > 1 (must pick at least 2 stones)
        for (int i = n - 2; i >= 1; i--) {
            res = Math.max(res, stones[i] - res);
        }
        
        return res;
    }
}
