class Solution {
    public long countCommas(long n) {
        long ans = 0;

        for (int commas = 1; ; commas++) {
            long start = power10(3 * commas);

            if (start > n)
                break;

            long end;
            if (3 * commas + 3 >= 18) {
                end = Long.MAX_VALUE;
            } else {
                end = power10(3 * commas + 3) - 1;
            }

            long count = Math.min(n, end) - start + 1;
            ans += count * commas;
        }

        return ans;
    }

    private long power10(int exp) {
        long res = 1;
        for (int i = 0; i < exp; i++) {
            res *= 10;
        }
        return res;
    }
}