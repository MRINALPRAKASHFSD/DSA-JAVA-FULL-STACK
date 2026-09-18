class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        
        // Step 1: Record the first and last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }
        
        List<String> res = new ArrayList<>();
        int last_right = -1;
        
        // Step 2: Greedily find valid substrings
        for (int i = 0; i < s.length(); i++) {
            // Only initiate a check if this is the absolute first occurrence of the character
            if (i == first[s.charAt(i) - 'a']) {
                int new_right = getRightBoundary(s, i, first, last);
                
                // If it forms a valid substring
                if (new_right != -1) {
                    if (i > last_right) {
                        // Independent substring found after the previous one
                        res.add(s.substring(i, new_right + 1));
                    } else {
                        // Found a valid substring entirely enclosed within the previous one.
                        // Overwrite to minimize the length and maximize potential subsequent cuts.
                        res.set(res.size() - 1, s.substring(i, new_right + 1));
                    }
                    last_right = new_right;
                }
            }
        }
        
        return res;
    }
    
    private int getRightBoundary(String s, int i, int[] first, int[] last) {
        int right = last[s.charAt(i) - 'a'];
        
        for (int j = i; j <= right; j++) {
            // If a character inside our bounds extends before our starting index 'i', 
            // this substring is invalid because it would require starting earlier.
            if (first[s.charAt(j) - 'a'] < i) {
                return -1;
            }
            // Expand the right boundary if a character demands it
            right = Math.max(right, last[s.charAt(j) - 'a']);
        }
        
        return right;
    }
}
