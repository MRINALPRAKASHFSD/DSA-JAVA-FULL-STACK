class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Calculate the 1-indexed position in the string
            int stringPosition = i + 1;
            
            // Calculate position in reversed alphabet ('a' -> 26, ..., 'z' -> 1)
            // 'a' is ASCII 97, so (s.charAt(i) - 'a') gives 0 for 'a', 25 for 'z'.
            // Subtracting that from 26 gives the desired reversed value.
            int reversedAlphabetPosition = 26 - (s.charAt(i) - 'a');
            
            totalDegree += stringPosition * reversedAlphabetPosition;
        }
        
        return totalDegree;
    }
}
