import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        
        boolean[] present = new boolean[101]; 
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        
        for (int num : nums) {
            present[num] = true;
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        List<Integer> missing = new ArrayList<>();
        
      
        for (int i = min + 1; i < max; i++) {
            if (!present[i]) {
                missing.add(i);
            }
        }
        
        return missing;
    }
}
