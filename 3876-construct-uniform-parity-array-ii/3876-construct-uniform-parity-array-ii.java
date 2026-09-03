class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
        }
        
        if (minVal % 2 != 0) {
            return true;
        }
        
        for (int num : nums1) {
            if (num % 2 != 0) {
                return false;
            }
        }
        
        return true;
    }
}
