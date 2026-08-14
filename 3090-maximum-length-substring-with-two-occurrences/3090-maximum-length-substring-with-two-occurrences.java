class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            int currChar = s.charAt(right) - 'a';
            freq[currChar]++;
            
            while (freq[currChar] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
