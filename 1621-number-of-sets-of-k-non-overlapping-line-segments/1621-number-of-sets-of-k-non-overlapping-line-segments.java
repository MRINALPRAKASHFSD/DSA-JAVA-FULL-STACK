class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        // Total points in our expanded pool
        int N = n + k - 1;
        // Total endpoints we need to pick
        int K = 2 * k;

        // If we need more endpoints than available points, it's impossible
        if (K > N) return 0;

        long numerator = 1;
        long denominator = 1;

        // Calculate nCr = N! / (K! * (N-K)!) 
        // We only loop K times to calculate the permutations and factorial
        for (int i = 1; i <= K; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }

        // Multiply numerator by the modular inverse of the denominator
        return (int) ((numerator * modInverse(denominator, MOD - 2)) % MOD);
    }

    // Helper method to calculate (base^exp) % MOD using binary exponentiation
    private long modInverse(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            // If exp is odd, multiply base with result
            if (exp % 2 == 1) {
                result = (result * base) % MOD;
            }
            // Square the base and halve the exponent
            base = (base * base) % MOD;
            exp /= 2;
        }
        return result;
    }
}
