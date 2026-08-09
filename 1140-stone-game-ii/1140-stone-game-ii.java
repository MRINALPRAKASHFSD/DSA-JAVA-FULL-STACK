class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n + 1];
        int[] suffixSum = new int[n];
        
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        return solve(0, 1, piles, dp, suffixSum);
    }
    
    private int solve(int i, int m, int[] piles, int[][] dp, int[] suffixSum) {
        if (i >= piles.length) {
            return 0;
        }
        if (i + 2 * m >= piles.length) {
            return suffixSum[i];
        }
        if (dp[i][m] != 0) {
            return dp[i][m];
        }
        
        int res = 0;
        for (int x = 1; x <= 2 * m; x++) {
            res = Math.max(res, suffixSum[i] - solve(i + x, Math.max(m, x), piles, dp, suffixSum));
        }
        
        dp[i][m] = res;
        return res;
    }
}
