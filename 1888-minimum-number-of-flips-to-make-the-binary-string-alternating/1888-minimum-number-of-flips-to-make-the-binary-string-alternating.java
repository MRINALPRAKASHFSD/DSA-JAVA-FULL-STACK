class Solution {
    public int minFlips(String s) {
        int n = s.length();
        
        
        int diff1 = 0; 
        int diff2 = 0; 
        int minFlips = Integer.MAX_VALUE;
        
        for (int i = 0; i < 2 * n; i++) {
            
            char c = s.charAt(i % n);
            
            
            char target1 = (i % 2 == 0) ? '0' : '1';
            char target2 = (i % 2 == 0) ? '1' : '0';
            
            
            if (c != target1) diff1++;
            if (c != target2) diff2++;
            
            
            if (i >= n) {
                int leftIndex = i - n;
                char leftChar = s.charAt(leftIndex % n);
                char leftTarget1 = (leftIndex % 2 == 0) ? '0' : '1';
                char leftTarget2 = (leftIndex % 2 == 0) ? '1' : '0';
                
                if (leftChar != leftTarget1) diff1--;
                if (leftChar != leftTarget2) diff2--;
            }
            
            // Once our window reaches exactly size 'n', record the minimum flips
            if (i >= n - 1) {
                minFlips = Math.min(minFlips, Math.min(diff1, diff2));
            }
        }
        
        return minFlips;
    }
}
