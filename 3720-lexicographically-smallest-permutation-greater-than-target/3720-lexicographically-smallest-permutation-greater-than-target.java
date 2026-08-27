class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Count characters in s
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Characters already used to match target's prefix
        int[] used = new int[26];

        String ans = "";

        for (int i = 0; i < n; i++) {

            int current = target.charAt(i) - 'a';

            // Try to make the string greater at this position
            for (int c = current + 1; c < 26; c++) {

                int available = freq[c] - used[c];

                if (available > 0) {

                    StringBuilder candidate = new StringBuilder();

                    // 1. Keep prefix same as target
                    candidate.append(target.substring(0, i));

                    // 2. Put the smallest character greater than target[i]
                    candidate.append((char) ('a' + c));

                    // 3. Count remaining characters
                    int[] remaining = freq.clone();

                    for (int j = 0; j < i; j++) {
                        remaining[target.charAt(j) - 'a']--;
                    }

                    remaining[c]--;

                    // 4. Put remaining characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        while (remaining[j] > 0) {
                            candidate.append((char) ('a' + j));
                            remaining[j]--;
                        }
                    }

                    // Don't return immediately!
                    // We want to see if we can make the difference later.
                    ans = candidate.toString();

                    break;
                }
            }

            // We cannot match target[i], so no later prefix is possible
            if (freq[current] - used[current] == 0) {
                break;
            }

            // Match target[i]
            used[current]++;
        }

        return ans;
    }
}