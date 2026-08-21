import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {

        // Sort coins
        Arrays.sort(coins);

        // Remove coins that are multiples of another smaller coin
        List<Integer> list = new ArrayList<>();

        for (int coin : coins) {
            boolean redundant = false;

            for (int x : list) {
                if (coin % x == 0) {
                    redundant = true;
                    break;
                }
            }

            if (!redundant) {
                list.add(coin);
            }
        }

        // Convert list to array
        int n = list.size();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = list.get(i);
        }

        // Binary search
        long low = 1;
        long high = a[0] * (long) k;

        while (low < high) {

            long mid = low + (high - low) / 2;

            long count = countNumbers(mid, a);

            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long countNumbers(long x, long[] coins) {

        int n = coins.length;
        long count = 0;

        // Inclusion-Exclusion
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    long g = gcd(lcm, coins[i]);

                    // Prevent overflow
                    long value = lcm / g;

                    if (value > x / coins[i]) {
                        valid = false;
                        break;
                    }

                    lcm = value * coins[i];
                }
            }

            if (!valid || lcm > x) {
                continue;
            }

            long multiples = x / lcm;

            if (bits % 2 == 1) {
                count += multiples;
            } else {
                count -= multiples;
            }
        }

        return count;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}