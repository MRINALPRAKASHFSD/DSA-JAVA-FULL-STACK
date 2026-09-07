class Solution {
    public int distinctSubseqII(String s) {

        long[] end = new long[26];
        long total = 0;

        final long MOD = 1_000_000_007;

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            long oldTotal = total;

            // New subsequences ending with ch
            long newEnding = oldTotal + 1;

            // Remove duplicates that already ended with ch
            total = (2 * oldTotal + 1 - end[index] + MOD) % MOD;

            // Update the subsequences ending with ch
            end[index] = newEnding % MOD;
        }

        return (int) total;
    }
}