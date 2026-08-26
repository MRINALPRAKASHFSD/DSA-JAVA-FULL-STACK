class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int count = 0;

        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            // Add current character to the window
            if (s.charAt(right) == '1') {
                count++;
            }

            // Too many 1s, move left
            while (count > k) {
                if (s.charAt(left) == '1') {
                    count--;
                }
                left++;
            }

            // Remove unnecessary leading zeros
            while (left < right && s.charAt(left) == '0') {
                left++;
            }

            // We have exactly k ones
            if (count == k) {

                String current = s.substring(left, right + 1);

                // Better length
                if (ans.equals("") || current.length() < ans.length()) {
                    ans = current;
                }

                // Same length → lexicographically smaller
                else if (current.length() == ans.length()
                        && current.compareTo(ans) < 0) {
                    ans = current;
                }
            }
        }

        return ans;
    }
}