class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            int mod = num % k;
            long[] newDp = new long[k];

            newDp[mod] = 1;

            for (int r = 0; r < k; r++) {
                int newRemainder = (int) ((long) r * mod % k);
                newDp[newRemainder] += dp[r];
            }

            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }

            dp = newDp;
        }

        return result;
    }
}