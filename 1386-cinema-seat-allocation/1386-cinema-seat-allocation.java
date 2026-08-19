import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Group reservations by row using a bitmask
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // Set the 'col'th bit to 1 for this row
            rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
        }
        
        // Rows that have zero reservations can hold 2 families each
        int maxFamilies = (n - rowMasks.size()) * 2;
        
        // Masks representing the required empty seats for each valid block
        int leftMask = 60;  // 0000111100 in binary
        int rightMask = 960; // 1111000000 in binary
        int midMask = 240;   // 0011110000 in binary
        
        // Evaluate rows that have at least one reservation
        for (int mask : rowMasks.values()) {
            boolean canFitLeft = (mask & leftMask) == 0;
            boolean canFitRight = (mask & rightMask) == 0;
            boolean canFitMid = (mask & midMask) == 0;
            
            // If both left and right are free, we can seat 2 families
            if (canFitLeft && canFitRight) {
                maxFamilies += 2;
            } 
            // Otherwise, if any of the three blocks are free, we can seat 1 family
            else if (canFitLeft || canFitRight || canFitMid) {
                maxFamilies += 1;
            }
            // If none are free, we add 0
        }
        
        return maxFamilies;
    }
}
