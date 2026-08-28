class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char middleChar = 0;
        int[] freq = new int[26];
        
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                middleChar = (char) (i + 'a');
            }
            freq[i] = count[i] / 2;
        }

        // A palindrome can have at most one character with an odd frequency
        if (oddCount > 1) return "";

        int m = n / 2;
        String targetL = target.substring(0, m);

        // 1. Check if an exact match of the target's left half is possible
        int[] tempFreq = freq.clone();
        boolean exactPossible = true;
        for (int i = 0; i < m; i++) {
            int c = targetL.charAt(i) - 'a';
            if (tempFreq[c] == 0) {
                exactPossible = false;
                break;
            }
            tempFreq[c]--;
        }

        if (exactPossible) {
            StringBuilder pExact = new StringBuilder(targetL);
            if (n % 2 != 0) pExact.append(middleChar);
            for (int i = m - 1; i >= 0; i--) {
                pExact.append(targetL.charAt(i));
            }

            // If matching the left half exactly makes the full palindrome strictly greater, return it
            if (pExact.toString().compareTo(target) > 0) {
                return pExact.toString();
            }
        }

        // 2. Find the maximum prefix length of targetL that we can form
        int max_i = 0;
        int[] rem_freq_max = freq.clone();
        while (max_i < m) {
            int c = targetL.charAt(max_i) - 'a';
            if (rem_freq_max[c] > 0) {
                rem_freq_max[c]--;
                max_i++;
            } else {
                break;
            }
        }

        int start_i = Math.min(m - 1, max_i);
        if (start_i < 0) return ""; // Handles edge case where length n = 1

        // Initialize available characters after forming prefix of length start_i
        int[] rem_freq = freq.clone();
        for (int j = 0; j < start_i; j++) {
            rem_freq[targetL.charAt(j) - 'a']--;
        }

        // 3. Iterate backwards to branch off with the smallest character strictly greater than targetL[i]
        for (int i = start_i; i >= 0; i--) {
            char targetChar = targetL.charAt(i);
            int pick = -1;
            
            // Look for the smallest available character strictly greater than targetChar
            for (int c = targetChar - 'a' + 1; c < 26; c++) {
                if (rem_freq[c] > 0) {
                    pick = c;
                    break;
                }
            }

            if (pick != -1) {
                StringBuilder L = new StringBuilder(targetL.substring(0, i));
                L.append((char) (pick + 'a'));
                rem_freq[pick]--;

                // Append the remaining available characters in sorted ascending order
                for (int c = 0; c < 26; c++) {
                    while (rem_freq[c] > 0) {
                        L.append((char) (c + 'a'));
                        rem_freq[c]--;
                    }
                }

                // Construct and return the final palindrome
                StringBuilder P = new StringBuilder(L.toString());
                if (n % 2 != 0) P.append(middleChar);
                for (int j = L.length() - 1; j >= 0; j--) {
                    P.append(L.charAt(j));
                }

                return P.toString();
            }

            // Backtrack: restore the character at i - 1 to available frequencies for the next iteration
            if (i > 0) {
                rem_freq[targetL.charAt(i - 1) - 'a']++;
            }
        }

        return "";
    }
}
