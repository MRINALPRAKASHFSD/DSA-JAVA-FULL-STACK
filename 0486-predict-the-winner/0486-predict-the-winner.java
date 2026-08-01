class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        
        // dp[i][j] stores the max score difference a player can get from subarray nums[i...j]
        int[][] dp = new int[n][n];
        
        // Base case: If there is only one element, the current player takes it
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        
        // Fill the DP table diagonally (increasing length of the subarray)
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                // Maximize the score difference by choosing either the left or right element
                int chooseLeft = nums[i] - dp[i + 1][j];
                int chooseRight = nums[j] - dp[i][j - 1];
                
                dp[i][j] = Math.max(chooseLeft, chooseRight);
            }
        }
        
        // Player 1 wins or ties if the max score difference from the whole array is >= 0
        return dp[0][n - 1] >= 0;
    }
}
