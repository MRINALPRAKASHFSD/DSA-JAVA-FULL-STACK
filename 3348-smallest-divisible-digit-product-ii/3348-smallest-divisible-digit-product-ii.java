import java.util.Arrays;

class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        
        // Extract required prime factors of t
        while (temp % 2 == 0) { c2++; temp /= 2; }
        while (temp % 3 == 0) { c3++; temp /= 3; }
        while (temp % 5 == 0) { c5++; temp /= 5; }
        while (temp % 7 == 0) { c7++; temp /= 7; }
        
        // If t has prime factors other than 2, 3, 5, 7, it's impossible.
        if (temp != 1) {
            return "-1";
        }
        
        // dp[i][j] caches the min digits required to get at least i factors of 2 and j factors of 3
        int[][] dp = new int[60][40];
        for (int i = 0; i < 60; i++) {
            Arrays.fill(dp[i], 1000); 
        }
        dp[0][0] = 0;
        
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 40; j++) {
                if (i == 0 && j == 0) continue;
                int min = 1000;
                min = Math.min(min, 1 + dp[Math.max(0, i - 1)][j]);                      // using digit 2
                min = Math.min(min, 1 + dp[i][Math.max(0, j - 1)]);                      // using digit 3
                min = Math.min(min, 1 + dp[Math.max(0, i - 2)][j]);                      // using digit 4
                min = Math.min(min, 1 + dp[Math.max(0, i - 1)][Math.max(0, j - 1)]);     // using digit 6
                min = Math.min(min, 1 + dp[Math.max(0, i - 3)][j]);                      // using digit 8
                min = Math.min(min, 1 + dp[i][Math.max(0, j - 2)]);                      // using digit 9
                dp[i][j] = min;
            }
        }
        
        // Prime factors mappings contributed by each digit from 0 to 9
        int[] f2 = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
        int[] f3 = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
        int[] f5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
        int[] f7 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        
        int n = num.length();
        int[] req2 = new int[n + 1], req3 = new int[n + 1];
        int[] req5 = new int[n + 1], req7 = new int[n + 1];
        
        req2[0] = c2; req3[0] = c3; req5[0] = c5; req7[0] = c7;
        
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            if (d == 0) {
                firstZero = i;
                break;
            }
            // Precompute how much factors are left after picking the strict `num` prefix
            req2[i + 1] = Math.max(0, req2[i] - f2[d]);
            req3[i + 1] = Math.max(0, req3[i] - f3[d]);
            req5[i + 1] = Math.max(0, req5[i] - f5[d]);
            req7[i + 1] = Math.max(0, req7[i] - f7[d]);
        }
        
        // Attempt to substitute the first different, larger digit keeping length `n` 
        for (int i = firstZero; i >= 0; i--) {
            // Case if zero-free and completely divisible unmodified
            if (i == n) {
                if (req2[n] == 0 && req3[n] == 0 && req5[n] == 0 && req7[n] == 0) {
                    return num;
                }
                continue;
            }
            
            int startD = (num.charAt(i) - '0') + 1;
            for (int d = startD; d <= 9; d++) {
                int nc2 = Math.max(0, req2[i] - f2[d]);
                int nc3 = Math.max(0, req3[i] - f3[d]);
                int nc5 = Math.max(0, req5[i] - f5[d]);
                int nc7 = Math.max(0, req7[i] - f7[d]);
                
                int remLen = n - 1 - i;
                if (nc5 + nc7 + dp[nc2][nc3] <= remLen) {
                    StringBuilder sb = new StringBuilder(n);
                    sb.append(num.substring(0, i)).append(d);
                    
                    // Greedily append smallest allowable digits to form lexicographically smallest outcome
                    for (int j = 0; j < remLen; j++) {
                        for (int v = 1; v <= 9; v++) {
                            int nnc2 = Math.max(0, nc2 - f2[v]);
                            int nnc3 = Math.max(0, nc3 - f3[v]);
                            int nnc5 = Math.max(0, nc5 - f5[v]);
                            int nnc7 = Math.max(0, nc7 - f7[v]);
                            
                            if (nnc5 + nnc7 + dp[nnc2][nnc3] <= remLen - 1 - j) {
                                sb.append(v);
                                nc2 = nnc2; nc3 = nnc3; nc5 = nnc5; nc7 = nnc7;
                                break;
                            }
                        }
                    }
                    return sb.toString();
                }
            }
        }
        
        // If impossible to form string of length n, formulate shortest possible larger answer
        int reqMin = c5 + c7 + dp[c2][c3];
        int newLen = Math.max(n + 1, reqMin);
        
        StringBuilder sb = new StringBuilder(newLen);
        int nc2 = c2, nc3 = c3, nc5 = c5, nc7 = c7;
        
        for (int j = 0; j < newLen; j++) {
            for (int v = 1; v <= 9; v++) {
                int nnc2 = Math.max(0, nc2 - f2[v]);
                int nnc3 = Math.max(0, nc3 - f3[v]);
                int nnc5 = Math.max(0, nc5 - f5[v]);
                int nnc7 = Math.max(0, nc7 - f7[v]);
                
                if (nnc5 + nnc7 + dp[nnc2][nnc3] <= newLen - 1 - j) {
                    sb.append(v);
                    nc2 = nnc2; nc3 = nnc3; nc5 = nnc5; nc7 = nnc7;
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}
