class Solution {
    public boolean sumGame(String num) {
        int diffSum = 0;
        int diffQ = 0;
        int totalQ = 0;
        int n = num.length();
        
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            boolean isLeft = i < n / 2;
            
            if (c == '?') {
                totalQ++;
                diffQ += isLeft ? 1 : -1;
            } else {
                int val = c - '0';
                diffSum += isLeft ? val : -val;
            }
        }
        
        // If total '?' is odd, Alice gets the last move and can always force a win
        if (totalQ % 2 != 0) {
            return true;
        }
        
        // For Bob to win, the sum difference must perfectly offset the '?' difference
        return (diffSum * 2 + diffQ * 9) != 0;
    }
}
